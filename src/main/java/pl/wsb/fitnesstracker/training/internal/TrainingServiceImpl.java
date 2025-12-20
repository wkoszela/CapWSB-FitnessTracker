package pl.wsb.fitnesstracker.training.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.List;
import java.util.Optional;

/**
 * Implementacja interfejsu TrainingProvider.
 *
 * Zawiera logikę biznesową dla operacji na treningach.
 * Wstrzykuje zależności poprzez konstruktor (Constructor Injection).
 *
 * @author Fitness Tracker Team
 */
@Service
@Slf4j
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    /**
     * Konstruktor ze wstrzykiwaniem zależności.
     *
     * @param trainingRepository Repozytorium dla dostępu do danych
     * @param userProvider       Provider dla sprawdzenia istnienia użytkownika
     */
    TrainingServiceImpl(final TrainingRepository trainingRepository,
                      final UserProvider userProvider) {
        this.trainingRepository = trainingRepository;
        this.userProvider = userProvider;
    }

    /**
     * Pobiera wszystkie treningi.
     *
     * @return Lista wszystkich treningów
     */
    public List<Training> getAllTrainings() {
        log.info("Fetching all trainings");
        return trainingRepository.findAll();
    }

    /**
     * Pobiera treningi konkretnego użytkownika.
     *
     * @param userId ID użytkownika
     * @return Lista treningów użytkownika
     * @throws UserNotFoundException Jeśli użytkownik nie istnieje
     */
    public List<Training> getTrainingsByUserId(Long userId) {
        log.info("Fetching trainings for user: {}", userId);

        // Sprawdzenie czy użytkownik istnieje
        userProvider.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return trainingRepository.findByUserId(userId);
    }

    /**
     * Pobiera trening po ID.
     *
     * @param trainingId ID treningu
     * @return Optional zawierający trening lub pusty
     */
    @Override
    public Optional<Training> getTraining(Long trainingId) {
        log.info("Fetching training with id: {}", trainingId);
        return trainingRepository.findById(trainingId);
    }

    /**
     * Tworzy nowy trening.
     *
     * @param training Trening do utworzenia
     * @return Utworzony trening (z wygenerowanym ID)
     */
    public Training createTraining(Training training) {
        log.info("Creating new training: {}", training);
        return trainingRepository.save(training);
    }

    /**
     * Aktualizuje istniejący trening.
     *
     * @param training Trening z nowymi danymi
     * @param id ID treningu do aktualizacji
     * @return Zaktualizowany trening
     */
    public Training updateTraining(Training training, Long id) {
        log.info("Updating training with id: {}", id);
        // Ustawiamy ID, aby update w bazie działał prawidłowo
        return trainingRepository.save(training);
    }

    /**
     * Usuwa trening po ID.
     *
     * @param trainingId ID treningu do usunięcia
     */
    public void deleteTraining(Long trainingId) {
        log.info("Deleting training with id: {}", trainingId);
        trainingRepository.deleteById(trainingId);
    }
}

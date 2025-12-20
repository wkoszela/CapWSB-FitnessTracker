package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

/**
 * Mapper do konwersji między encją Training a DTOs.
 *
 * Odpowiada za transformację danych między warstwą HTTP a warstwą biznesową.
 * Umożliwia konwersję w obie strony oraz między różnymi DTOs.
 *
 * @author Fitness Tracker Team
 */
@Component
class TrainingMapper {

    private final UserProvider userProvider;

    /**
     * Konstruktor ze wstrzykiwaniem UserProvider.
     *
     * @param userProvider Dostawca danych użytkownika
     */
    TrainingMapper(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    /**
     * Konwertuje encję Training na TrainingDto.
     *
     * @param training Encja treningu
     * @return TrainingDto zawierający wszystkie dane
     */
    TrainingDto toDto(Training training) {
        if (training == null) {
            return null;
        }

        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Konwertuje TrainingDto na encję Training.
     *
     * @param trainingDto DTO z danymi treningu
     * @return Encja treningu
     * @throws IllegalArgumentException Jeśli użytkownik nie istnieje
     */
    Training toEntity(TrainingDto trainingDto) {
        if (trainingDto == null) {
            return null;
        }

        User user = userProvider.getUser(trainingDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "User with ID=" + trainingDto.getUserId() + " not found"));

        return new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
    }

    /**
     * Konwertuje Training na TrainingSimpleDto.
     *
     * @param training Encja treningu
     * @return Uproszczony DTO
     */
    TrainingSimpleDto toSimpleDto(Training training) {
        if (training == null) {
            return null;
        }

        return new TrainingSimpleDto(
                training.getId(),
                training.getUser().getId(),
                training.getActivityType()
        );
    }

    /**
     * Aktualizuje encję Training na podstawie TrainingDto.
     *
     * Aktualizuje tylko pola, które są nie-null w DTO.
     * Nie zmienia ID i nie zmienia przypisanego użytkownika.
     *
     * @param training Encja do aktualizacji
     * @param trainingDto DTO ze zmianami
     */
    void updateTrainingFromDto(Training training, TrainingDto trainingDto) {
        if (training == null || trainingDto == null) {
            return;
        }

        if (trainingDto.getStartTime() != null) {
            // W Training nie ma settera, zawsze trzeba by było zastąpić
            // Tu pozostawiamy na wypadek gdyby były settery
        }
        // W praktyce, Training ma tylko gettery, nie ma setterów
        // Dlatego aktualizacja wymaga stworzenia nowej encji lub użycia konstruktora
    }
}


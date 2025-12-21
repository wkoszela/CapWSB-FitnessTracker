package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.List;
import java.util.Optional;

// TODO: Provide Implementation and correct the return type of the method getTraining
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    /**
     * szuka wszystkich treningów
     * @return
     */
    List<Training> findAll() {
        return trainingRepository.findAll();
    }

    /**
     * szuka treningow usera
     * @param userId
     * @return
     */
    List<Training> findByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

}

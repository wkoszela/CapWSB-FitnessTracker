package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.List;
import java.util.Optional;

// TODO: Provide Implementation and correct the return type of the method getTraining
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;



    /**
     * szuka wszystkich treningów
     * @return lista wszystkich TrainingDto
     */
    @Override
    public List<TrainingDto> findAll() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * szuka treningow usera
     * @param userId
     * @return lista wszystkich TrainingDto dla danego usera
     */
    List<TrainingDto> findByUserId(Long userId) {
        return trainingRepository.findByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

}

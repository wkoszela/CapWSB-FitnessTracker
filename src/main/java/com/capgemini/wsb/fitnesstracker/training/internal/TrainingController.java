package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingServiceImpl trainingService;
    private final UserService userService;
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;
    //need for tests: /v1/trainings,  /v1/trainings/{userId}, /v1/trainings/finished/{afterTime}, /v1/trainings/activityType, /v1/trainings/{trainingId}

    @GetMapping
    public ResponseEntity<List<TrainingDtoWithUserDto>> getAllTrainings() {
        List<Training> allTrainings = trainingRepository.findAll();
        List<TrainingDtoWithUserDto> userDtos = new ArrayList<>();
        if (!allTrainings.isEmpty()) {
            for (Training training : allTrainings) {
                userDtos.add(trainingMapper.toDtoWithUserDto(training));
            }
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TrainingDtoWithUserDto> addNewTraining(@RequestBody TrainingDtoWithUserId training) {
        if(training.userId() == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepository.findById(training.userId());
        if(user.isPresent()) {
            System.out.println("training " + training + "added!");
            Training newTraining = trainingService.createTraining(trainingMapper.toEntity(training, user.get()));
            return new ResponseEntity<>(trainingMapper.toDtoWithUserDto(newTraining), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/activityType") //imo should be {activityType} and @PathVariable, but TrainingApiIntegrationTest need "/activityType"
    public ResponseEntity<List<Training>> getTrainings(@RequestParam("activityType") String activityType) {
        try {
            ActivityType activity = ActivityType.valueOf(activityType.toUpperCase());
            List<Training> trainings = trainingRepository.getTrainingsByType(activity);
            if (trainings.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
            return new ResponseEntity<>(trainings, HttpStatus.OK);
        } catch (IllegalArgumentException e) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TrainingDtoWithUserDto>> getTrainingsByUserId(@PathVariable("userId") Long userId) {
        Optional<User> userTraining = userRepository.findById(userId); //finding by user id
        if(userTraining.isPresent()) {
            User actualUser = userTraining.get();
            List<Training> trainings = trainingService.getTrainingsByUserId(actualUser.getId());
            List<TrainingDtoWithUserDto> mappedTrainings = trainings.stream().map(TrainingMapper::toDtoWithUserDto).toList();
            return new ResponseEntity<>(mappedTrainings, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{trainingId}")
    public ResponseEntity<TrainingDtoWithUserDto> updateTraining(@PathVariable("trainingId") Long id, @RequestBody TrainingDtoForPut training) {
        Optional<Training> presentTraining = trainingService.getTrainingById(id);
        if (presentTraining.isPresent()) {User actualUser = presentTraining.get().getUser();
            Training newTraining = trainingMapper.toEntity(id, training, presentTraining.get(), actualUser);
            trainingRepository.updateTraining(newTraining);
            return new ResponseEntity<>(TrainingMapper.toDtoWithUserDto(newTraining), HttpStatus.OK);}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //postman ex.: GET http://localhost:8080/v1/trainings/finished/2024-01-15
    @GetMapping("/finished/{afterTime}")
    public ResponseEntity<List<TrainingDtoWithUserDto>> getFinishedTrainingsTime(@PathVariable ("afterTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time) {
        List<TrainingDtoWithUserDto> finishedTrainings = trainingRepository.getFinishedAfterX(time)
                .stream()
                .map(TrainingMapper::toDtoWithUserDto)
                .toList();
        if(finishedTrainings.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(finishedTrainings, HttpStatus.OK);
    }
}
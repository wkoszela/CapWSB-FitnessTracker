package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TrainingMapper trainingMapper;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrainings() {
        List<Training> trainings = Arrays.asList(new Training(), new Training());
        when(trainingRepository.findAll()).thenReturn(trainings);

        List<Training> result = trainingService.getAllTrainings();

        assertEquals(trainings, result);
        verify(trainingRepository).findAll();
    }

    @Test
    void testGetTrainingsFinishedAfter() {
        Date date = new Date();
        List<Training> trainings = Arrays.asList(new Training(), new Training());
        when(trainingRepository.findByEndTimeAfter(date)).thenReturn(trainings);

        List<Training> result = trainingService.getTrainingsFinishedAfter(date);

        assertEquals(trainings, result);
        verify(trainingRepository).findByEndTimeAfter(date);
    }

    @Test
    void testGetTrainingsForUser_UserExists() {
        Long userId = 1L;
        User user = mock(User.class);
        Optional<User> optionalUser = Optional.of(user);
        List<Training> trainings = Arrays.asList(new Training(), new Training());
        when(userRepository.findById(userId)).thenReturn(optionalUser);
        when(trainingRepository.findByUser(user)).thenReturn(trainings);

        List<Training> result = trainingService.getTrainingsForUser(userId);

        assertEquals(trainings, result);
        verify(userRepository).findById(userId);
        verify(trainingRepository).findByUser(user);
    }

    @Test
    void testGetTrainingsForUser_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            trainingService.getTrainingsForUser(userId);
        });
        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(trainingRepository);
    }

    @Test
    void testGetByActivityType() {
        ActivityType activityType = ActivityType.RUNNING;
        List<Training> trainings = Arrays.asList(new Training(), new Training());
        when(trainingRepository.findByActivityType(activityType)).thenReturn(trainings);

        List<Training> result = trainingService.getByActivityType(activityType);

        assertEquals(trainings, result);
        verify(trainingRepository).findByActivityType(activityType);
    }

    @Test
    void testCreateTraining_Success() {
        Long userId = 1L;
        TrainingDataDto createTrainingDto = mock(TrainingDataDto.class);
        when(createTrainingDto.userId()).thenReturn(userId);

        User user = mock(User.class);
        Training training = new Training();
        Training savedTraining = new Training();

        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(trainingMapper.toEntity(createTrainingDto, user)).thenReturn(training);
        when(trainingRepository.save(training)).thenReturn(savedTraining);

        Training result = trainingService.createTraining(createTrainingDto);

        assertEquals(savedTraining, result);
        verify(userRepository).getReferenceById(userId);
        verify(trainingMapper).toEntity(createTrainingDto, user);
        verify(trainingRepository).save(training);
    }

    @Test
    void testCreateTraining_UserNotFound() {
        Long userId = 1L;
        TrainingDataDto createTrainingDto = mock(TrainingDataDto.class);
        when(createTrainingDto.userId()).thenReturn(userId);

        when(userRepository.getReferenceById(userId)).thenThrow(new EntityNotFoundException("User not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            trainingService.createTraining(createTrainingDto);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository).getReferenceById(userId);
        verifyNoMoreInteractions(trainingMapper, trainingRepository);
    }

    @Test
    void testUpdateTraining_Success() {
        Long userId = 1L;
        Long trainingId = 2L;

        TrainingDataDto trainingDto = mock(TrainingDataDto.class);
        when(trainingDto.userId()).thenReturn(userId);

        User user = mock(User.class);
        Training existingTraining = new Training();
        Training updatedTraining = new Training();
        Training savedTraining = new Training();

        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(trainingRepository.getReferenceById(trainingId)).thenReturn(existingTraining);
        when(trainingMapper.toUpdateEntity(trainingDto, existingTraining, user)).thenReturn(updatedTraining);
        when(trainingRepository.save(updatedTraining)).thenReturn(savedTraining);

        Training result = trainingService.updateTraining(trainingId, trainingDto);

        assertEquals(savedTraining, result);
        verify(userRepository).getReferenceById(userId);
        verify(trainingRepository).getReferenceById(trainingId);
        verify(trainingMapper).toUpdateEntity(trainingDto, existingTraining, user);
        verify(trainingRepository).save(updatedTraining);
    }

    @Test
    void testUpdateTraining_UserNotFound() {
        Long userId = 1L;
        Long trainingId = 2L;

        TrainingDataDto trainingDto = mock(TrainingDataDto.class);
        when(trainingDto.userId()).thenReturn(userId);

        when(userRepository.getReferenceById(userId)).thenThrow(new EntityNotFoundException("User not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            trainingService.updateTraining(trainingId, trainingDto);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository).getReferenceById(userId);
        verifyNoMoreInteractions(trainingRepository, trainingMapper);
    }

    @Test
    void testUpdateTraining_TrainingNotFound() {
        Long userId = 1L;
        Long trainingId = 2L;

        TrainingDataDto trainingDto = mock(TrainingDataDto.class);
        when(trainingDto.userId()).thenReturn(userId);

        User user = mock(User.class);

        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(trainingRepository.getReferenceById(trainingId)).thenThrow(new EntityNotFoundException("Training not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            trainingService.updateTraining(trainingId, trainingDto);
        });
        assertEquals("Training not found", exception.getMessage());
        verify(userRepository).getReferenceById(userId);
        verify(trainingRepository).getReferenceById(trainingId);
        verifyNoMoreInteractions(trainingMapper);
    }

    @Test
    void testGetTraining_NotImplemented() {
        Long trainingId = 1L;

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            trainingService.getTraining(trainingId);
        });
        assertEquals("Not finished yet", exception.getMessage());
    }
}

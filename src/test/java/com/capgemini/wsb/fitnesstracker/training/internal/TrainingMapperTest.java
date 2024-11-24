package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingMapperTest {

    @Mock
    private TrainingUserMapper trainingUserMapper;

    private TrainingMapper trainingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingMapper = new TrainingMapper(trainingUserMapper);
    }

    @Test
    void testToDto_Success() {
        User user = mock(User.class);
        TrainingUserDto trainingUserDto = new TrainingUserDto(1L, "John", "Doe", "john.doe@example.com");
        when(trainingUserMapper.toDto(user)).thenReturn(trainingUserDto);

        Training training = new Training();
        training.setId(1L);
        training.setUser(user);
        training.setStartTime(new Date());
        training.setEndTime(new Date());
        training.setActivityType(ActivityType.RUNNING);
        training.setDistance(10.0);
        training.setAverageSpeed(5.0);

        TrainingDto result = trainingMapper.toDto(training);

        assertNotNull(result);
        assertEquals(training.getId(), result.id());
        assertEquals(trainingUserDto, result.user());
        assertEquals(training.getStartTime(), result.startTime());
        assertEquals(training.getEndTime(), result.endTime());
        assertEquals(training.getActivityType(), result.activityType());
        assertEquals(training.getDistance(), result.distance());
        assertEquals(training.getAverageSpeed(), result.averageSpeed());

        verify(trainingUserMapper).toDto(user);
    }

    @Test
    void testToDto_NullTraining() {
        TrainingDto result = trainingMapper.toDto(null);

        assertNull(result);
    }

    @Test
    void testToDto_NullUser() {
        Training training = new Training();
        training.setId(1L);

        assertThrows(InvalidTrainingDataException.class, () -> trainingMapper.toDto(training));
    }

    @Test
    void testToEntity_TrainingDto() {
        User user = mock(User.class);

        Date startTime = new Date();
        Date endTime = new Date();
        ActivityType activityType = ActivityType.RUNNING;
        double distance = 10.0;
        double averageSpeed = 5.0;

        TrainingDto dto = new TrainingDto(
            1L,
            null,
            startTime,
            endTime,
            activityType,
            distance,
            averageSpeed
        );

        Training result = trainingMapper.toEntity(dto, user);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(startTime, result.getStartTime());
        assertEquals(endTime, result.getEndTime());
        assertEquals(activityType, result.getActivityType());
        assertEquals(distance, result.getDistance());
        assertEquals(averageSpeed, result.getAverageSpeed());
    }

    @Test
    void testToEntity_TrainingDto_NullDto() {
        User user = mock(User.class);

        Training result = trainingMapper.toEntity((TrainingDto) null, user);

        assertNull(result);
    }

    @Test
    void testToEntity_TrainingDataDto() {
        User user = mock(User.class);

        Date startTime = new Date();
        Date endTime = new Date();
        ActivityType activityType = ActivityType.RUNNING;
        double distance = 10.0;
        double averageSpeed = 5.0;

        TrainingDataDto dto = new TrainingDataDto(
            1L,
            startTime,
            endTime,
            activityType,
            distance,
            averageSpeed
        );

        Training result = trainingMapper.toEntity(dto, user);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(startTime, result.getStartTime());
        assertEquals(endTime, result.getEndTime());
        assertEquals(activityType, result.getActivityType());
        assertEquals(distance, result.getDistance());
        assertEquals(averageSpeed, result.getAverageSpeed());
    }

    @Test
    void testToEntity_TrainingDataDto_NullDto() {
        User user = mock(User.class);

        Training result = trainingMapper.toEntity((TrainingDataDto) null, user);

        assertNull(result);
    }

    @Test
    void testToUpdateEntity() {
        User user = mock(User.class);
        User newUser = mock(User.class);

        Training training = new Training();
        training.setId(1L);
        training.setUser(user);
        training.setStartTime(new Date());
        training.setEndTime(new Date());
        training.setActivityType(ActivityType.RUNNING);
        training.setDistance(10.0);
        training.setAverageSpeed(5.0);

        Date newStartTime = new Date();
        Date newEndTime = new Date();
        ActivityType newActivityType = ActivityType.CYCLING;
        double newDistance = 20.0;
        double newAverageSpeed = 10.0;

        TrainingDataDto trainingDataDto = new TrainingDataDto(
            2L,
            newStartTime,
            newEndTime,
            newActivityType,
            newDistance,
            newAverageSpeed
        );

        Training result = trainingMapper.toUpdateEntity(trainingDataDto, training, newUser);

        assertNotNull(result);
        assertEquals(newUser, result.getUser());
        assertEquals(newStartTime, result.getStartTime());
        assertEquals(newEndTime, result.getEndTime());
        assertEquals(newActivityType, result.getActivityType());
        assertEquals(newDistance, result.getDistance());
        assertEquals(newAverageSpeed, result.getAverageSpeed());
    }

    @Test
    void testToUpdateEntity_NullDto() {
        User user = mock(User.class);
        Training training = new Training();

        Training result = trainingMapper.toUpdateEntity(null, training, user);

        assertEquals(training, result);
    }

    @Test
    void testToUpdateEntity_NullTraining() {
        User user = mock(User.class);
        TrainingDataDto trainingDataDto = new TrainingDataDto(
            1L,
            new Date(),
            new Date(),
            ActivityType.RUNNING,
            10.0,
            5.0
        );

        Training result = trainingMapper.toUpdateEntity(trainingDataDto, null, user);

        assertNull(result);
    }
}

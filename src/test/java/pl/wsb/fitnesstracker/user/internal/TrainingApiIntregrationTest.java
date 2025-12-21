package pl.wsb.fitnesstracker.user.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.IntegrationTest;
import pl.wsb.fitnesstracker.IntegrationTestBase;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.time.LocalDate.now;
import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class TrainingApiIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllTrainings_whenGettingAllTrainings() throws Exception {

        User user1 = existingUser(generateClient());
        Training training1 = persistTraining(generateTraining(user1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        mockMvc.perform(get("/v1/trainings").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))

                .andExpect(jsonPath("$[0].startTime").value(sdf.format(training1.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(training1.getEndTime())))
                .andExpect(jsonPath("$[0].distance").value((training1.getDistance())))
                .andExpect(jsonPath("$[0].averageSpeed").value(training1.getAverageSpeed()))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldReturnAllTrainingsForDedicatedUser_whenGettingAllTrainingsForDedicatedUser() throws Exception {

        User user1 = existingUser(generateClient());
        Training training1 = persistTraining(generateTraining(user1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        mockMvc.perform(get("/v1/trainings/{userId}", user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(training1.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(training1.getEndTime())))
                .andExpect(jsonPath("$[0].distance").value((training1.getDistance())))
                .andExpect(jsonPath("$[0].averageSpeed").value(training1.getAverageSpeed()))

                .andExpect(jsonPath("$[1]").doesNotExist());
        ;
    }

    private static User generateClient() {
        return new User(randomUUID().toString(), randomUUID().toString(), now(), randomUUID().toString());
    }

    private static Training generateTraining(User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new Training(
                user,
                sdf.parse("2024-01-19 08:00:00"),
                sdf.parse("2024-01-19 09:30:00"),
                ActivityType.RUNNING,
                10.5,
                8.2);
    }

    private static Training generateTrainingWithActivityType(User user, ActivityType activityType)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new Training(
                user,
                sdf.parse("2024-01-19 08:00:00"),
                sdf.parse("2024-01-19 09:30:00"),
                activityType,
                0, 0);
    }

    private static Training generateTrainingWithDetails(User user, String startTime, String endTime,
            ActivityType activityType, double distance, double averageSpeed) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new Training(
                user,
                sdf.parse(startTime),
                sdf.parse(endTime),
                activityType,
                distance,
                averageSpeed);
    }

}

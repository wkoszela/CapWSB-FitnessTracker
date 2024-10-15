package com.capgemini.wsb.fitnesstracker.statistics.internal;

import org.junit.jupiter.api.Test;
import com.capgemini.wsb.fitnesstracker.IntegrationTest;
import com.capgemini.wsb.fitnesstracker.IntegrationTestBase;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class StatisticsApiIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnNewStatistics_whenCreatingNewStatistics() throws Exception {

        User user1 = existingUser(generateUser());
        Training training1 = persistTraining(generateTraining(user1));

        mockMvc.perform(put("/v1/statistics").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))

                .andExpect(jsonPath("$[0].totalTrainings").value(1))
                .andExpect(jsonPath("$[0].totalDistance").value(10.5))
                .andExpect(jsonPath("$[0].totalCaloriesBurned").value(600))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldUpdateStatisticForDedicatedUser_whenUpdatingStatisticsForDedicatedUser() throws Exception {

        User user1 = existingUser(generateUser());
        Training training1 = persistTraining(generateTraining(user1));
        Statistics statistics1 = persistStatistics(generateStatistics(user1));

        mockMvc.perform(put("/v1/statistics/{userID}", user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(user1.getId()))
                .andExpect(jsonPath("$.user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$.user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$.user.email").value(user1.getEmail()))

                .andExpect(jsonPath("$.totalTrainings").value(1))
                .andExpect(jsonPath("$.totalDistance").value(10.5))
                .andExpect(jsonPath("$.totalCaloriesBurned").value(600));
    }

    @Test
    void shouldReturnStatisticsForDedicatedUser_whenGettingStatisticsForDedicatedUser() throws Exception {

        User user1 = existingUser(generateUser());
        Statistics statistics1 = persistStatistics(generateStatistics(user1));

        mockMvc.perform(get("/v1/statistics/{userID}", user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(user1.getId()))
                .andExpect(jsonPath("$.user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$.user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$.user.email").value(user1.getEmail()))

                .andExpect(jsonPath("$.totalTrainings").value(statistics1.getTotalTrainings()))
                .andExpect(jsonPath("$.totalDistance").value(statistics1.getTotalDistance()))
                .andExpect(jsonPath("$.totalCaloriesBurned").value(statistics1.getTotalCaloriesBurned()));
    }

    @Test
    void shouldRemoveStatisticsFromRepository_whenDeletingStatistics() throws Exception {
        User user1 = existingUser(generateUser());
        Statistics statistics1 = persistStatistics(generateStatistics(user1));

        mockMvc.perform(delete("/v1/statistics").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNoContent());

        List<Statistics> allStatistics = getAllStatistics();
        assertThat(allStatistics).isEmpty();
    }

    @Test
    void shouldReturnStatisticsWithCaloriesGreaterThen_whenGettingAllStatisticsWithCaloriesGreaterThen() throws Exception {
        User user1 = existingUser(generateUser());
        User user2 = existingUser(generateUser());
        Statistics statistics1 = persistStatistics(generateStatistics(user1, 15));
        Statistics statistics2 = persistStatistics(generateStatistics(user2, 5));

        mockMvc.perform(get("/v1/statistics/caloriesGreaterThen/{caloriesFloor}", 10).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))

                .andExpect(jsonPath("$[0].totalTrainings").value(statistics1.getTotalTrainings()))
                .andExpect(jsonPath("$[0].totalDistance").value(statistics1.getTotalDistance()))
                .andExpect(jsonPath("$[0].totalCaloriesBurned").value(statistics1.getTotalCaloriesBurned()))
                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    public static User generateUser() {
        return new User(randomUUID().toString(), randomUUID().toString(), LocalDate.now(), randomUUID().toString());
    }

    private static Training generateTraining(User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new Training(
                user,
                sdf.parse("2024-01-19 08:00:00"),
                sdf.parse("2024-01-19 09:30:00"),
                ActivityType.RUNNING,
                10,
                8.2);
    }

    private static Statistics generateStatistics(User user) throws ParseException {

        return new Statistics(
                user,
                0,
                0,
                0);
    }

    private static Statistics generateStatistics(User user, int totalCalories) throws ParseException {

        return new Statistics(
                user,
                0,
                0,
                totalCalories);
    }

}

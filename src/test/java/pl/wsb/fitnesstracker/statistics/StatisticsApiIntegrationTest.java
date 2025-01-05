package pl.wsb.fitnesstracker.statistics;

import pl.wsb.fitnesstracker.IntegrationTest;
import pl.wsb.fitnesstracker.IntegrationTestBase;
import pl.wsb.fitnesstracker.statistics.api.Statistics;
import pl.wsb.fitnesstracker.user.api.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class StatisticsApiIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllStatistics_whenGettingAllStatistics() throws Exception {
        User user1 = existingUser(generateClient());
        Statistics stats1 = persistStatistic(generateStatistics(user1));

        mockMvc.perform(get("/v1/statistics").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))

                .andExpect(jsonPath("$[0].totalTrainings").value(stats1.getTotalTrainings()))
                .andExpect(jsonPath("$[0].totalDistance").value(stats1.getTotalDistance()))
                .andExpect(jsonPath("$[0].totalCaloriesBurned").value(stats1.getTotalCaloriesBurned()))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldReturnDetailsAboutStatistics_whenGettingByUserId() throws Exception {
        User user1 = existingUser(generateClient());
        Statistics stats1 = persistStatistic(generateStatistics(user1));

        mockMvc.perform(get("/v1/statistics/{userId}", user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))
                .andExpect(jsonPath("$[0].totalTrainings").value(stats1.getTotalTrainings()))
                .andExpect(jsonPath("$[0].totalDistance").value(stats1.getTotalDistance()))
                .andExpect(jsonPath("$[0].totalCaloriesBurned").value(stats1.getTotalCaloriesBurned()))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldReturnDetailsAboutStatistics_whenGettingAboveBurnedCalories() throws Exception {
        User user1 = existingUser(generateClient());
        Statistics stats1 = persistStatistic(generateBurnedStatistics(user1));

        mockMvc.perform(get("/v1/statistics/moreCaloriesBurned", user1.getId()).param("burnedCalories", "1111").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.id").value(user1.getId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))
                .andExpect(jsonPath("$[0].totalTrainings").value(stats1.getTotalTrainings()))
                .andExpect(jsonPath("$[0].totalDistance").value(stats1.getTotalDistance()))
                .andExpect(jsonPath("$[0].totalCaloriesBurned").value(stats1.getTotalCaloriesBurned()))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldPersistStatistics_whenCreatingNewStatistics() throws Exception {
        User user1 = existingUser(generateClient());

        Integer totalTrainings = 321;
        Double totalDistance = 500.1;
        Integer totalCaloriesBurned = 1111;

        String requestBody = """
                {
                    "userId": "%s",
                    "totalTrainings": %d,
                    "totalDistance": %s,
                    "totalCaloriesBurned": %d
                }
                """.formatted(user1.getId(), totalTrainings, totalDistance, totalCaloriesBurned);

        mockMvc.perform(post("/v1/statistics").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andDo(log())
                .andExpect(status().isCreated())
        ;

        List<Statistics> allStatistics = getAllStatistics();
        Statistics statistics = allStatistics.get(0);

        assertThat(statistics.getTotalTrainings()).isEqualTo(totalTrainings);
        assertThat(statistics.getTotalDistance()).isEqualTo(totalDistance);
        assertThat(statistics.getTotalCaloriesBurned()).isEqualTo(totalCaloriesBurned);
    }

    @Test
    void shouldPersistStatistics_whenUpdatingStatistics() throws Exception {
        User user1 = existingUser(generateClient());
        Statistics stats1 = persistStatistic(generateStatistics(user1));

        Integer totalTrainings = 321;
        Double totalDistance = 3221.5;
        Integer totalCaloriesBurned = 6666;

        String requestBody = """
                {
                    "totalTrainings": %d,
                    "totalDistance": %s,
                    "totalCaloriesBurned": %d
                }
                """.formatted(totalTrainings, totalDistance, totalCaloriesBurned);

        mockMvc.perform(put("/v1/statistics/{statisticsId}", stats1.getId()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stats1.getId()))
                .andExpect(jsonPath("$.user.id").value(user1.getId()))
                .andExpect(jsonPath("$.user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$.user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$.user.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.totalTrainings").value(totalTrainings))
                .andExpect(jsonPath("$.totalDistance").value(totalDistance))
                .andExpect(jsonPath("$.totalCaloriesBurned").value(totalCaloriesBurned))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldRemoveStatisticsFromRepository_whenDeletingStatistics() throws Exception {
        User user1 = existingUser(generateClient());
        Statistics stats1 = persistStatistic(generateStatistics(user1));

        mockMvc.perform(delete("/v1/statistics/{statisticsId}", stats1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNoContent());

        List<Statistics> allStatistics = getAllStatistics();
        assertThat(allStatistics).isEmpty();
    }

    private static User generateClient() {
        return new User(randomUUID().toString(), randomUUID().toString(), now(), randomUUID().toString());
    }

    private static Statistics generateStatistics(User user) {
        return new Statistics(
                user,
                42,
                58,
                111
        );
    }

    private static Statistics generateBurnedStatistics(User user) {
        return new Statistics(
                user,
                6550,
                16280,
                60004654
        );
    }
}
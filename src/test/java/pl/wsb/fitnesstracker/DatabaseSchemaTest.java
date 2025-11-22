package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class DatabaseSchemaTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void shouldHaveEventTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "event")).isTrue();
        }
    }

    @Test
    void shouldHaveHealthMetricsTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "health_metrics")).isTrue();
        }
    }

    @Test
    void shouldHaveStatisticsTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "statistics")).isTrue();
        }
    }

    @Test
    void shouldHaveTrainingsTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "trainings")).isTrue();
        }
    }

    @Test
    void shouldHaveUserEventTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "user_event")).isTrue();
        }
    }

    @Test
    void shouldHaveUsersTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "users")).isTrue();
        }
    }

    @Test
    void shouldHaveWorkoutSessionTable() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(tableExists(conn, "workout_session")).isTrue();
        }
    }

    @Test
    void eventTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "event");
            assertThat(cols).contains("id", "start_time", "end_time", "city", "country", "description", "name");
        }
    }


    @Test
    void healthMetricsTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "health_metrics");
            assertThat(cols).contains("id", "user_id", "date", "weight", "height", "heart_rate");
        }
    }

    @Test
    void statisticsTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "statistics");
            assertThat(cols).contains("id", "user_id", "total_distance", "total_calories_burned", "total_trainings");
        }
    }

    @Test
    void trainingsTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "trainings");
            assertThat(cols).contains("id", "user_id", "activity_type", "start_time", "end_time", "average_speed", "distance");
        }
    }

    @Test
    void userEventTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "user_event");
            assertThat(cols).contains("id", "user_id", "event_id", "status");
        }
    }

    @Test
    void usersTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "users");
            assertThat(cols).contains("id", "email", "first_name", "last_name");
        }
    }

    @Test
    void workoutSessionTableHasExpectedColumns() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Set<String> cols = tableColumns(conn, "workout_session");
            assertThat(cols).contains("id", "training_id", "altitude", "end_latitude", "end_longitude", "start_latitude", "start_longitude", "timestamp");
        }
    }

    private boolean tableExists(Connection conn, String expectedName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                String schema = rs.getString("TABLE_SCHEM");
                if (schema == null) continue;
                if (!"PUBLIC".equalsIgnoreCase(schema)) continue;
                String name = rs.getString("TABLE_NAME");
                if (expectedName.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set<String> tableColumns(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        Set<String> cols = new HashSet<>();
        try (ResultSet rs = meta.getColumns(conn.getCatalog(), null, "%", "%")) {
            while (rs.next()) {
                String schema = rs.getString("TABLE_SCHEM");
                if (schema == null) continue;
                if (!"PUBLIC".equalsIgnoreCase(schema)) continue;
                String tbl = rs.getString("TABLE_NAME");
                if (!tableName.equalsIgnoreCase(tbl)) continue;
                String col = rs.getString("COLUMN_NAME");
                if (col != null) {
                    cols.add(col.toLowerCase());
                }
            }
        }
        return cols;
    }
}

package pl.wsb.fitnesstracker.notification;

import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

public class WeekRaport {
    private final Long userId;
    private final String userEmail;
    private final int trainingsCount;
    private final double totalDistance;

    private double averageSpeed;
    private final List<Training> trainings;

    public WeekRaport(Long userId, String userEmail, int trainingsCount, double totalDistance, double averageSpeed, List<Training> trainings){
        this.userId = userId;
        this.userEmail = userEmail;
        this.trainingsCount = trainingsCount;
        this.totalDistance = totalDistance;
        this.averageSpeed = averageSpeed;
        this.trainings = trainings;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public int getTrainingsCount() {
        return trainingsCount;
    }

    /**
     * Tworzy string raportu tygodniowego danego Usera
     * @return String Raport
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
            User: %s
            Trainings: %d
            Total distance: %.2f km
            Average speed: %.2f km/h
            """.formatted(userEmail, trainingsCount, totalDistance, averageSpeed));

        sb.append("Training details:\n");
        int a = 0;
        for (Training t : trainings) {
            a += 1;
            sb.append(" - Traning %d\n".formatted(a)).append(t).append("\n");
        }

        return sb.toString();
    }
}

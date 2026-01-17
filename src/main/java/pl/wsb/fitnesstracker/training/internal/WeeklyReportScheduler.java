package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeeklyReportScheduler {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    /**
     * Zadanie uruchamiane raz w tygodniu (w poniedziałek o północy).
     * Używamy adnotacji @Scheduled zgodnie ze slajdem 91-92.
     */
    //@Scheduled(cron = "0 0 0 * * MON") //Co tydzień w poniedziałek o północy
    @Scheduled(initialDelay = 5000, fixedRate = 10000) //Co 10 sekund po 5 sekundowym opóźnieniu (do testów)
    // Alternatywnie można użyć makra: @Scheduled(cron = "@weekly")
    public void generateWeeklyReports() {
        log.info("Rozpoczynanie generowania cotygodniowych raportów treningowych...");

        // Obliczamy datę sprzed 7 dni
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = cal.getTime();

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            List<Training> weeklyTrainings = trainingRepository.findByUserIdAndStartTimeAfter(user.getId(), lastWeek);

            if (!weeklyTrainings.isEmpty()) {
                printReport(user, weeklyTrainings);
            } else {
                log.info("Użytkownik {} {} nie odbył żadnych treningów w tym tygodniu.",
                        user.getFirstName(), user.getLastName());
            }
        }
        log.info("Zakończono generowanie raportów.");
    }

    private void printReport(User user, List<Training> trainings) {
        double totalDistance = trainings.stream().mapToDouble(Training::getDistance).sum();
        long count = trainings.size();

        System.out.println("======= RAPORT TYGODNIOWY =======");
        System.out.println("Użytkownik: " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        System.out.println("Liczba treningów: " + count);
        System.out.println("Całkowity dystans: " + String.format("%.2f", totalDistance) + " km");
        System.out.println("---------------------------------");
        trainings.forEach(t -> System.out.println("- " + t.getActivityType() + ": " + t.getDistance() + " km"));
        System.out.println("=================================");
    }
}
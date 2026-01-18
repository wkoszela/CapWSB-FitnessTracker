package pl.wsb.fitnesstracker.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.internal.EmailService;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.TrainingRepository;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RaportService {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final EmailService emailService;


    /**
     * Generuje raport dla uzytkownikow
     * @param from od daty
     * @param to do daty
     * @return lista raportow dla uzytkownikow
     */
    List<WeekRaport> generateReport(LocalDate from, LocalDate to){
        return userRepository.findAll().stream()
                .map(user -> {
                    List<Training> trainings =
                            trainingRepository.findAll().stream()
                                    .filter(t -> t.getUser().getId().equals(user.getId()))
                                    .filter(t -> isInRange(t, from, to))
                                    .toList();

                    double totalDistance = trainings.stream()
                            .mapToDouble(Training::getDistance)
                            .sum();

                    double totalaverage = trainings.isEmpty()
                            ? 0.0
                            : trainings.stream()
                            .mapToDouble(Training::getAverageSpeed)
                            .average()
                            .orElse(0.0);


                    WeekRaport raport = new WeekRaport(
                            user.getId(),
                            user.getEmail(),
                            trainings.size(),
                            totalDistance,
                            totalaverage,
                            trainings
                    );

                    emailService.sendWeeklyReportMail(
                            user.getEmail(),
                            raport.toString()
                    );

                    return raport;
                })
                .toList();
    }

    /**
     * sprawdza czy trening jest w ciagu dat od do
     * @param training
     * @param from
     * @param to
     * @return boolean
     */
    private boolean isInRange(Training training, LocalDate from, LocalDate to) {
        LocalDate start = training.getStartTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return !start.isBefore(from) && !start.isAfter(to);

}
}

# WSB FitnessTracker (LAB05/LAB06)

## Wymagania

- Java 17
- Maven 3.8+

## Uruchomienie

```bash
mvn spring-boot:run
```

Domyślnie włączony jest profil `loadInitialData`, który ładuje dane startowe.

## Konfiguracja e-mail (Emailtrap)

W `src/main/resources/application.properties` uzupełnij dane SMTP:

```properties
mail.from=fitness-tracker@domain.com
spring.mail.host=smtp.emailtrap.io
spring.mail.port=587
spring.mail.username=twoj-username
spring.mail.password=twoj-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> **Uwaga:** Nie dodawaj prawdziwych haseł do repozytorium – używaj wartości testowych/placeholderów.

## Harmonogram raportów tygodniowych

Konfiguracja znajduje się w `application.properties`:

```properties
app.reports.weekly.cron=0 0 8 ? * MON
app.reports.weekly.email.enabled=true
app.reports.weekly.zone=Europe/Warsaw
```

- `cron` – kiedy wykonywać raport (domyślnie w każdy poniedziałek o 08:00).
- `email.enabled` – umożliwia łatwe wyłączenie wysyłki maili.
- `zone` – strefa czasowa raportu.

### Przykładowy log

```
Weekly training report: userId=1, email=user@domain.com, week=2024-01-08..2024-01-14, trainings=2, trainingIds=[5, 6]
```

## Postman

Kolekcja do testowania API znajduje się w katalogu `postman/`.

## LAB06 – wykonane wymagania

- [x] Zadanie cykliczne do raportów tygodniowych (scheduler + serwis)
- [x] Wysyłka raportów e-mail przez Spring Mail
- [x] Konfiguracja przez properties + profile
- [x] Testy dla raportu tygodniowego i wysyłki maili

## Pakowanie do ZIP

Aby przygotować paczkę do wysłania (GitLab/Moodle):

```bash
zip -r WSB-FitnessTracker.zip . -x "*/target/*" "*/.git/*"
```

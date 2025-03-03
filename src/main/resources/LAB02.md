# LABORATORIUM 02

Zadania proszę realizować zgodnie z własnym tempem. Zadania mają pomóc w przygotowaniu do Egzaminu oraz zrozumieniu
Frameworka Spring.
Termin upływa po 7 dniach od rozpoczęciu laboratorium. Po przesłaniu zadania proszę o zaznaczenie na Moodle wykonanie
zadania.

## ZADANIE 1. Sieciowe API do operacji typu CRUD na klientach

### Potrzeba biznesowa

Jako użytkownik systemu, chcę mieć możliwość zarządzania użytkownikami
serwisu FitnessTracker:

- móc ich wyszukiwać, pobierać
- móc wprowadzać nowych użytkowników do systemu
- móc usuwać użytkowników z systemu
- móc aktualizować użytkowników

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [x] wylistowanie podstawowych informacji o wszystkich użytkownikach zapisanych w systemie (tylko ID oraz nazwa
  użytkownika)
- [x] pobranie szczegółów dotyczących wybranego użytkownika (dowolny parametr: ID/imię & nazwisko/datę urodzenia/
  e-mail)
- [x] utworzenie nowego użytkownika
- [x] usunięcie użytkownika (konkretny, np. konkretny ID danego uzytkownika)
- [x] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [x] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany
- [x] aktualizowanie użytkowników w systemie (dowolnie wybrany atrybut)

### Wymagania techniczne

- [x] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [x] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [x] rozwiązanie powinno spełniać zasady SOLID
- [x] testy integracyjne powinny poprawnie się wykonywać UserApiIntegrationTest
- [x] (OPCJONALNIE) rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [x] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [x] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [x] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API (`src/main/resources/wsb-backend.postman_collection.json`)
- [x] rozwiązanie powinno wykorzystywać rekordy (Java 16+) do definicji obiektów transferu danych (DTO)

# LABORATORIUM 02

Zadania proszę realizować zgodnie z własnym tempem. Zadania mają pomóc w przygotowaniu do Egzaminu oraz zrozumieniu Frameworka Spring.
Termin upływa po 7 dniach od rozpoczęciu laboratorium.
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

- [ ] wylistowanie podstawowych informacji o wszystkich użytkownikach zapisanych w systemie (tylko ID oraz nazwa
  uzytkownika)
- [ ] pobranie szczegółów dotyczących wybranego użytkownika (dowolny parametr: ID/imię & nazwisko/datę urodzenia/ e-mail)
- [ ] utworzenie nowego użytkownika
- [ ] usunięcie użytkownika (konkretny, np. konkretny ID danego uzytkownika)
- [ ] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [ ] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany
- [ ] aktualizowanie użytkowników w systemie (dowolnie wybrany atrybut)

### Wymagania techniczne

- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] (OPCJONALNIE) rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [ ] rozwiązanie powinno wykorzystywać rekordy (Java 16+) do definicji obiektów transferu danych (DTO)

## ZADANIE 2: Zabezpieczenie API (Opcjonalnie)

### Potrzeba biznesowa:

Jako administrator systemu, chcę zabezpieczyć API, z którego mogą korzystać różne systemy

- API potrzebne do zbierania metryk powinno być dostępne dla narzędzi monitorujących
- API, które nie modyfikuje danych, powinno być dostępne dla znanych użytkowników
- API, które może modyfikować dane, powinno być zabezpieczone przed nieuprawnionym dostępem

### Wymagania funkcjonalne

Zabezpieczenia, powinny zagwarantować:

- [ ] API Spring Boot Actuator są dostępne bez zabezpieczenia, tj. nie wymagają uwierzytelnienia ani dodatkowych
  uprawnień
- [ ] API dla HTTP metody GET jest dostępne dla wszystkich uwierzytelnionych użytkowników
- [ ] API dla pozostałych metod jest dostępne dla użytkowników z rolą "ADMIN"
- [ ] lista użytkowników i ich ról jest statyczna (nie zmienia się)

### Wymagania techniczne

- [ ] zabezpieczenie powinno wykorzystywać bibliotekę Spring Security
- [ ] użytkownik może uwierzytelnić się jedynie za pomocą Basic Auth
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  rozwiązanie
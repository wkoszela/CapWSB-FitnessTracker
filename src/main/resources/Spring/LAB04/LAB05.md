# LABORATORIUM 05 - Spring Boot - kontynuacja sieciowego API CRUD

## Kontynuacja LABORATORIUM 04

Proszę o skończenie zadania LAB04 (wymagania funkcjonalne oraz techniczne) oraz rozpoczęcie pracy nad zadaniem LAB05.

## ZADANIE 1. Sieciowe API do operacji typu CRUD na Training (bez użycia rekordów)

### Potrzeba biznesowa

Jako użytkownik, chce mieć możliwość dostępu do panelu z treningami:

- wyświetlanie swoich treningów,

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wyszukiwanie wszystkich treningów
- [ ] wyszukiwanie treningów dla określonego Użytkownika:

### Wymagania techniczne

- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [ ] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

Ocena 3.0 - dokumentacja techniczna Encji/Interface/DTO/Mappery z poprzedniego LAB04
Ocena 4.0 - w pełni wykonane wymagania funkcjonalne oraz techniczne LAB04
Ocena 5.0 - w pełni wykonane wymagania funkcjonalne oraz techniczne LAB04 oraz LAB05

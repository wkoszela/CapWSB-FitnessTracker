# LABORATORIUM 02

## Kontynuacja LABORATORIUM 01 oraz stworzenie aspekt logującego wywołania metod serwisów

## ZADANIE 1. Sieciowe API do operacji typu CRUD na Training (bez użycia rekordów)

### Potrzeba biznesowa

Jako użytkownik, chce mieć możliwość dostępu do panelu z treningami:

- tworzenie nowych,
- wyświetlanie swoich treningów,
- aktualizacji trenigów

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wyszukiwanie wszystkich treningów
- [ ] wyszukiwanie treningów dla określonego Użytkownika:
- [ ] wyszukiwanie wszystkich treningów zakończonych (po konkretnej zdefiniowanej dacie)
- [ ] wyszukiwanie wszystkich treningów dla konkretnej aktywności (np. wszystkie treningi biegowe)
- [ ] utworzenie nowego treningu
- [ ] aktualizacja treningu (dowolnie wybrane pole np. dystans)

### Wymagania techniczne

- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [ ] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

## ZADANIE 2 (opcjonalne). Sieciowe API do operacji typu CRUD na Statistics (bez użycia rekordów)

### Potrzeba biznesowa

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wylistowanie podstawowych informacji o wszystkich statystykach zapisanych w systemie
- [ ] utworzenie nowych statystyk
- [ ] aktualizacja Statystyk Użytkownika implementacja funkcjonalności do aktualizacji istniejących statystyk dla
  użytkownika.
- [ ] pobranie szczegółów dotyczących statystyk dla danego użytkownika
- [ ] usunięcie statystyk
- [ ] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [ ] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany

### Wymagania techniczne

- [ ] przygotowanie danych wejściowych (uzupełnienie skryptu ładującego dane przy starcie aplikacji)
- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [ ] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

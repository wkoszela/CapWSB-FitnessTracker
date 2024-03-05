# LABORATORIUM 03

Zadania proszę realizować zgodnie z własnym tempem. Zadania mają pomóc w przygotowaniu do Egzaminu oraz zrozumieniu Frameworka Spring.

#### Wymagane zadania:

Zaleca się przed przystąpieniem do Laboratorium 03 studenci wykonanie zadań z Laboratorium 01 oraz 02.

## ZADANIE 1. API sieciowe do wysyłania wiadomości projektowych. Kontynuacja LABORATORIUM 02 (dla osób, którym nie udało się skończyć).

### Potrzeba biznesowa

Jako pracownik projektu, chcę mieć możliwość wysłania wiadomości do użytkownika o zakończonym treningu.

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wysłanie wiadomości email do użytkownika (i znajomych) o zakończonym treningu
- [ ] wysłana pracownikom wiadomość powinna zawierać informacje o: nadawcy(System), nazwie aktywnosci, długości trwania
  aktywności, dodatkowy tekst

### Wymagania techniczne

- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [ ] rozwiązanie powinno być pokryte za pomocą testów integracyjnych stworzonego API - powinny one sprawdzać, że
  wymagania funkcjonalne są spełnione
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] rozwiązanie powinno wykorzystywać mechanizm zdarzeń aplikacji (application events) do publikowania zdarzenia, że
  informacja o aktywności ma zostać wysłana. Odbiorca wiadomości oraz logikę związaną z jej wysyłaniem obsłuż kodem
  wywoływanym przez pojawienie się odpowiedniego zdarzenia.

## ZADANIE 2. API sieciowe do wysyłania wiadomości projektowych (Opcjonalne))

### Potrzeba biznesowa

Jako użytkownik systemu, chcę mieć możliwość otrzymywania powiadomień e-mailowych z podsumowaniem treningów w każdym
tygodniu.

### Wymagania techniczne

- [ ] w oparciu o ustalony zdefiniowany czas (co tydzień) generowany jest raport z treningami dla każdego użytkownika z
  danego tygodnia
- [ ] raport wysyłany jest za pomocą e-mail z podsumowaniem do każdego użytkownika
- [ ] wiadomość powinna zawierać kilka następujących informacji: ile czasu łącznie użytkownik spędził na aktywnościach,
  oraz ile aktywności dany użytkownik wykonał
- [ ] raport powinien być w formie jednego łańcucha znaków o formacie (z możliwościa zmiany na inny format)

### Wymagania techniczne

- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno być pokryte za pomocą testów integracyjnych stworzonego API - powinny one sprawdzać, że
  wymagania funkcjonalne są spełnione
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do ustalania częstotliwości generowania raportów wykorzystaj CRON, jego konfiguracja powinna być wczytywana z
  konfiguracji aplikacji (plik application.yml)


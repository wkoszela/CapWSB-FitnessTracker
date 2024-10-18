## ZADANIE 1. API sieciowe do generowania raportów treningowych

### Potrzeba biznesowa

Jako użytkownik systemu, chcę mieć możliwość otrzymywania wiadomości e-mailowych z podsumowaniem treningów w każdym
tygodniu.
Termin upływa po 7 dniach od rozpoczęciu laboratorium.
### Wymagania funkcjonalne

- [x] w oparciu o ustalony zdefiniowany czas (co tydzień) generowany jest raport z treningami dla każdego użytkownika z
  danego tygodnia
- [x] raport wysyłany jest za pomocą e-mail z podsumowaniem do każdego użytkownika, ile treningów ma zarejestrowanych łącznie


## ZADANIE 2. Programowanie aspektowe (opcjonalnie, proszę zerknąć w Lab branch (livecoding pakiet)

### Potrzeba biznesowa

Jako pracownik utrzymania, chcę mieć możliwość kontroli wykonania się kodu programu za pomocą logów aplikacji.

### Wymagania funkcjonalne

- [x] być uruchomiony podczas wywoływania metod publicznych serwisów (klas adnotowanych `@Service`)
- [x] przed wywołaniem metody logować o niej informację w
  formacie (`typ zwracany nazwaKlasy.nazwaMetody(typParametru1 nazwaParametru1, ...)`),
  np. `void MyService.myMethod(String param1, Boolean param2)`
- [x] po wywołaniu metody logować informację o metodzie (w tym samym formacie co przed wywołaniem) wraz z informacją na
  temat zwróconej wartości (wystarczy jej toString())

### Wymagania techniczne

- [x] aspekt powinien zostać zaimplementowany z użyciem biblioteki AspectJ
- [x] rozwiązanie powinno spełniać zasady SOLID
- [x] testy jednostkowe rozwiązania nie są wymagane
- [x] testy integracyjne rozwiązania nie są wymagane
- [x] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [x] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
## ZADANIE 1. API sieciowe do generowania raportów treningowych

### Potrzeba biznesowa

Jako użytkownik systemu, chcę mieć możliwość otrzymywania wiadomości e-mailowych z podsumowaniem treningów w każdym
tygodniu.
Termin upływa po 7 dniach od rozpoczęciu laboratorium.
### Wymagania funkcjonalne

- [ ] w oparciu o ustalony zdefiniowany czas (co tydzień) generowany jest raport z treningami dla każdego użytkownika z
  danego tygodnia
- [ ] raport wysyłany jest za pomocą e-mail z podsumowaniem do każdego użytkownika, ile treningów ma zarejestrowanych łącznie


## ZADANIE 2. Programowanie aspektowe (opcjonalnie, proszę zerknąć w Lab branch (livecoding pakiet)

### Potrzeba biznesowa

Jako pracownik utrzymania, chcę mieć możliwość kontroli wykonania się kodu programu za pomocą logów aplikacji.

### Wymagania funkcjonalne

- [ ] być uruchomiony podczas wywoływania metod publicznych serwisów (klas adnotowanych `@Service`)
- [ ] przed wywołaniem metody logować o niej informację w
  formacie (`typ zwracany nazwaKlasy.nazwaMetody(typParametru1 nazwaParametru1, ...)`),
  np. `void MyService.myMethod(String param1, Boolean param2)`
- [ ] po wywołaniu metody logować informację o metodzie (w tym samym formacie co przed wywołaniem) wraz z informacją na
  temat zwróconej wartości (wystarczy jej toString())

### Wymagania techniczne

- [ ] aspekt powinien zostać zaimplementowany z użyciem biblioteki AspectJ
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] testy jednostkowe rozwiązania nie są wymagane
- [ ] testy integracyjne rozwiązania nie są wymagane
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
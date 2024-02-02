## LABORATORIUM 02

## Aspekt logujący wywołania metod serwisów

### Potrzeba biznesowa

Jako pracownik utrzymania, chcę mieć możliwość kontroli wykonania się kodu programu za pomocą logów aplikacji.

### Wymagania funkcjonalne

Stworzony aspekt powinien:

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

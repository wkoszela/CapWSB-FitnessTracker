## LABORATORIUM 03

## API sieciowe do wysyłania wiadomości projektowych

### Potrzeba biznesowa

Jako pracownik projektu, chcę mieć możliwość wysłania wiadomości do użytkownika o zakończonym treningu.

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wysłanie wiadomości email do użytkwnika (i znajomych) o zakończonym treningu
- [ ] wysłana pracownikom wiadomość powinna zawierać informacje o: nadawcy(System), nazwie aktywnosci, długości trwania
  aktywności,
- dodatkowy tekst

### Wymagania techniczne

- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `EmployeeRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być pokryte testami jednostkowymi (>90%)
- [ ] rozwiązanie powinno być pokryte za pomocą testów integracyjnych stworzonego API - powinny one sprawdzać, że
  wymagania funkcjonalne są spełnione
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc

- [ ] rozwiązanie powinno wykorzystywać mechanizm zdarzeń aplikacji (application events) do publikowania zdarzenia, że
  informacja o aktywności ma zostać wysłana. Odbiorca wiadomości oraz logikę związaną z jej wysyłaniem obsłuż kodem
  wywoływanym przez pojawienie się odpowiedniego zdarzenia.

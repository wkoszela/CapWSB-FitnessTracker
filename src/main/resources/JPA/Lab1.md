Laboratorium I - Relacje w JPA

1. Zapoznaj sie ze strukturą projektu - nazwy pakietów, encje, enumy. Zwróć uwagę na application.yml w resources,
   oraz na data.sql. Zapoznaj sie z ustawieniami aplikacji (przede wszystkim JPA). 
2. Uruchom aktualna konfiguracje 'FitnessTracker' bądz kliknij prawym ma FitnessTracker i wybierz opcje RUN.
   Obserwuj logi konsoli, zwlaszcza logi Hibernate'a
3. Po starcie aplikacji (w logach widoczny ostatni wpis "Started FitnessTracker in XXXX seconds (JVM running for
   X.XX)") uruchom przegladarke i przejdz do adresu http://localhost:8080/h2/ . Pojawi sie poniższa strona logowania.
   Wpisz odpowiednie username i password jeśli nie zostało uzupelnione automatycznie (sprawdź ustawienia w pliku
   application.yml - url, login, hasło) i połącz sie z bazą H2
   ![](H2_console.png)
4. Po zalogowaniu sie do bazy H2 pojawi sie poniższy interfejs graficzny z listingiem wszystkich tabel (po lewej) oraz
   miejscem na wprowadzania komend sql
   ![](H2_console_tables.png)
5. Jesli zmieniasz nazwy tabel, pamietaj o wywolaniu komendy "drop all objects" - inaczej Hibernate utworzy na nowo
   tylko znane mu aktualnie tabele, zostawiajac poprzednie nietknięte, co może być mocno mylące.
6. Zapoznaj sie z poniższym schematem relacyjnym bazy danych. Korzystając z wiedzy przekazanej na wykładzie, literatury
   oraz internetu uzupelnij brakujace tabele, relacje w aktualnej definicji encji (zwróć uwagę także na nazwy tabel). Określ
   nullowalność kolumn i kaskady.
   Ustaw relacje tak, aby przynajmniej raz byla wykorzystana relacja jednostronna i dwustronna.
   Przy każdej relacji napisz jako komentarz czy jest to relacja jednostronna od strony rodzica (wlaściciela relacji),
   jednostronna od strony dziecka, czy dwustronna.
   ![](db_schema.png)
7. Uzupełnij plik data.sql danymi (komendy insert) w odpowiedniej kolejności potwierdzajac poprawność modelu

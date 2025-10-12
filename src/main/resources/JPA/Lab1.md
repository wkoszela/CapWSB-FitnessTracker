Laboratorium I - Organizacja | Relacje w JPA

1. Zapoznaj sie ze strukturą projektu - nazwy pakietów, encje, enumy. Zwróć uwagę na application.yml w resources. Zapoznaj sie z ustawieniami aplikacji (przede wszystkim JPA). 
2. Uruchom aktualną konfiguracje 'FitnessTracker' bądź kliknij prawym ma FitnessTracker i wybierz opcje RUN.
   Obserwuj logi konsoli, zwlaszcza logi Hibernate'a
3. Po starcie aplikacji (w logach widoczny ostatni wpis "Started FitnessTracker in XXXX seconds (JVM running for
   X.XX)") uruchom przeglądarke i przejdź do adresu http://localhost:8080/h2/ . Pojawi sie poniższa strona logowania.
   Wpisz odpowiednie username i password jeśli nie zostało uzupelnione automatycznie (sprawdź ustawienia w pliku
   application.yml - url, login, hasło) i połącz sie z bazą H2 ![](H2_console.png)
4. Po zalogowaniu sie do bazy H2 pojawi sie poniższy interfejs graficzny z listingiem wszystkich tabel (po lewej) oraz
   miejscem na wprowadzania komend sql ![](H2_console_tables.png)
5. Jeśli zmieniasz nazwy tabel, pamiętaj o wywołaniu komendy "drop all objects" - inaczej Hibernate utworzy na nowo
   tylko znane mu aktualnie tabele, zostawiając poprzednie nietknięte, co może być mocno mylące.

Laboratorium III - Relacje w JPA - stan na 22.11.2025 11:00

**Uwaga - Przed przystapieniem do zadania proszę o wykonanie zadania LAB01/LAB02 (poprzednie zajęcia).**

1. Zweryfikuj czy projekt poprawnie się buduje lokalnie (mvn clean install) oraz na Github (zakładka Actions).
   Pipeline przed przystąpieniem powinien być zielony (poprawny build). Otwórz projekt w Intelij. Zweryfikuj czy jesteś
   zalogowany do Github (z poziomu Intelij)
2. Zsynchronizuj swoje repozytorium z repozytorium prowadzącego (Sync Fork bądź Pull z repozytorium prowadzącego).
   Można wykonać to bezpośrednio w GitHubie lub lokalnie. Uwaga, zmiany dotyczą tylko : LAB03.md oraz klasy z testami.
   Proszę przekopiować plik DatabaseSchemaTest z src/main/resources/JPA/LAB03 do pakietu testowego w swoim projekcie (
   src/test/pl.wsb.fitnesstracker/)
   Można klasę nadpisać.
3. Dokończ schemat bazy danych (plik db_schema.png) zgodnie z poniższymi wytycznymi:
   ** Pracujemy nad następującymi Encjami : Trainings, Workout_Session, Event, UserEvent**.
   Uwaga 1: ActivityType proszę potraktować jako zwykły Enum Javowy (nie encję JPA).
   Uwaga 2: Tabela UserEvent ma być tabelą, która będzie przechowywać klucze obce do tabel User oraz Event (z
   dodatkowymi atrybutami).
   Sugeruję zrobić relację @ManyToOne z Userem, oraz @ManyToOne z Eventem.

![](db_schema.png)

4. Wprowadzone zmiany prześlij na swoje repozytorium. Testy powinny poprawnie się wykonać na platformie
   Github.
5. Dodaj przynajmniej jedno @Repository dla encji JPA (do ustelania w trakcje zajęć). Użyj adnotacji @Repository oraz
   skorzystaj z Entity Managera (również pamiętaj o adnoacji @ @PersistenceContext).
   Napisz przynajmniej jedno zapytanie JPQL lub w natywnym SQL w tym repozytorium (do ustalania w trakcie zajęć).
6. Prześlij zmiany na swoje repozytorium.
7. Zgłoś prowadzącemu wykonanie zadanie, celem uzyskania oceny.

Kryterium Akceptacji na ocenę 5:

- Poprawnie zdefiniowane cała struktura projektu
- Poprawnie działające testy jednostkowe (DatabaseSchemaTest) - również na Github Actions
- Napisane przynajmniej jedno repozytorium JPA z zapytaniem JPQL lub natywnym SQL
- GitHub Actions z poprawnym buildem projektu po kroku 4.
- Przesłanie zmian na na swoje repozytorium.

Kryterium Akceptacji na ocenę 4:

- Poprawnie zdefiniowane 2 encje JPA (tabele, kolumny, relacje, nullowalność)
- Poprawnie działające testy jednostkowe związane z encjami (DatabaseSchemaTest) - również na Github Actions
- Przesłanie zmian na na swoje repozytorium.

Kryterium Akceptacji na ocenę 3:

- Zsynchronizowane repozytorium z repozytorium prowadzącego
- Poprawnie zdefiniowana 1 encja JPA (tabele, kolumny, relacje, nullowalność)
- GitHub Actions z poprawnym buildem projektu po kroku 4 (dla tej encji).
- Przesłanie zmian na na swoje repozytorium.

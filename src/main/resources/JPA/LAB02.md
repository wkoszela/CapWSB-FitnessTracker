Laboratorium II - Relacje w JPA - stan na 8.11.2025

**Uwaga - Przed przystapieniem do zadania proszę o wykonanie zadania LAB01 (poprzednie zajęcia). **

1. Zsynchronizuj swoje repozytorium z repozytorium prowadzącego (Sync Fork bądź Pull z repozytorium prowadzącego). Można
   wykonać to bezpośrednio w GitHubie lub lokalnie.
2. Do projektu dodaj plik github-ci-cd.yml. Plik ten znajduje się w katalogu resources/general/github-ci-cd.yml.
   Plik powinien zostać umieszczony pod .github/workflows/github-ci-cd.yml
3. Scommituj zmiany i wyślij je do swojego repozytorium.
4. Zweryfikuj, czy projekt poprawnie się buduje i przechodzi build w CI (GitHub Actions). Zwróć uwagę na Build.
5. Zapoznaj się z poniższym schematem relacyjnym bazy danych. Korzystając z wiedzy przekazanej na wykładzie, literatury
   oraz internetu, uzupełnij brakujące tabele i relacje w aktualnej definicji encji (zwróć uwagę także na nazwy tabel).
   Określ nullowalność kolumn i kaskady. Ustaw relacje tak, aby przynajmniej raz była wykorzystana relacja jednostronna
   i
   dwustronna. **Pracujemy nad 3 Encjami Statistics, HealthMetrics, User.**

   ![](db_schema.png)

6. Wprowadzone zmiany prześlij na swoje repozytorium. Testy powinny poprawnie się wykonać lokalnie oraz platformie
   Github.
7. Zgłoś prowadzącemu wykonanie zadanie, celem uzyskania oceny.

8. (*) Opcjonalnie można przygotować plik data.sql z danymi (komendy INSERT) w odpowiedniej kolejności, potwierdzając
   poprawność modelu.
   Przydadzą się na kolejnych zajęciach.
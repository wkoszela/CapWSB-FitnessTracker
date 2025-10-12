Laboratorium II - Architektura warstwowa, EntityManager oraz testy

Uwaga! Do wykonania zadan konieczne jest zaimplementowanie modelu obiektowo-relacyjnego bazy z Laboratorium I !

1. Korzystajac z przykladowego kodu dla encji AddressEntity utworz warstwy dostepu do danych (Repository, Service,
   mappery, TOsy, opcjonalnie RESTy - dla chetnych) dla encji PatientEntity. Spelnione maja byc nasteoujace wymagania:
    - TO pacjenta ma miec liste wizyt ktore sie odbyly
    - kazda wizyta ma miec informacje o czasie (daty), imie i nazwisko lekarza oraz liste typow (z encji
      MedicalTreatment)
    - rozszerz encje PatientEntity o jedno dowolne pole innego typu niz String, odwzoruj je w TO.
2. Korzystajac z przykladowych insertow w pliku data.sql uzupelnij encje pacjenta, doktorow oraz wizyt danymi testowymi
3. Korzystajac z przykladowych testow dla encji Address, napisz testy do serwisu (uwaga! serwisu, nie DAO!) pacjenta:
    - test usuwajacy pacjenta sprawdza czy usuniete zostaly wszystkie wizyty (kaskada) i czy nie zostali usunieci
      doktorzy
    - pobranie pacjenta po ID powinno zwrocic strukture TO-sow odpowiadajaca wczesniejszym zalozeniom. W asercjach
      sprawdz poprawnosc odczytu dodanego przez Ciebie pola z punktu pierwszego
4. Dodaj metode w PatientDao, ktora na podstawie parametrow wejsciowych:

   ID pacjenta, ID doktora, data wizyty, opis wizyty

   utworzy nowa encje wizyty i doda ja do pacjenta w jednym wywolaniu - kaskadowy update pacjenta (merge).

   Npisz test do tej metody (Dao)


Laboratorium III - JPQL

Uwaga! Do wykonania zadan konieczne jest zaimplementowanie architektury warstwowej i testow z Laboratorium II !

Uzupelnij plik data.sql o dane niezbedne do realizacji nastepujacych zapytan:

1. Znajdz pacjentow po nazwisku
2. Znajdz wszystkie wizyty pacjenta po jego ID
3. znajdz pacjentow ktorzy mieli wiecej niz X wizyt (X jest parametrem wejsciowym)
4. Znajdz pacjentow po dodanym przez Ciebie polu - nie wyszukuj wprost po wartosci, uzyj zapytania typu
   wieksze/mniejsze/pozniej/wczesniej/zawiera, w zaleznosci od wybranego typu zmiennej.

Napisz testy do zapytan w nastepujacej formie:

1. do zapytania nr 1 - test DAO
2. do zapytania nr 2 - test serwisu
3. do zapytania nr 3 - test DAO
4. do zapytania nr 4 - test DAO

W PatientEntity, nad relacja do VisitEntity dodaj adnotacje

@Fetch(FetchMode.SELECT)

a fetchType zmien na EAGER
Uruchom test w ktorym pobierany jest Patient z wieloma wizytami. W logach zaobserwuj, jak wyglada pobieranie dodatkowych
encji (ile i jakie sqle).
Nastepnie zmien adnotacje na

@Fetch(FetchMode.JOIN)

i powtorz test i obserwacje. Wnioski zapisz na dole tego pliku i skomituj.

Do wybranej encji dodaj wersjonowanie, oraz napisz test (w DAO) sprawdzajacy rownolegla modyfikacje (OptimisticLock)
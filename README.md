# movie-app
Rating Service

:warning: :baby: young pre 1.0 project 

 - Readme.md jak uruchomić projekt (w języku angielskim)


1. Backend

   Do zamodelowania dane:

   - Lista filmów. Każdy film ma swój tytuł, datę produkcji i gatunek

   - Lista ocen przypisanych do danego filmu. Oceny w skali od 1-10

   

   Do stworzenia API Restowe z metodami:

   - Pobranie listy filmów oraz ocen dla filmów

   - Dodanie nowej oceny dla filmu


  Technologie:

   - Spring

   - Spring Data MongoDb

   - API Restowe dowolny moduł dla Springa

   - baza danych MongoDB


2. Frontend

   - Wyświetlenie listy filmów (Nazwa, data produkcji w formacie: DD-MM-RRRR, gatunek  wyświetlony jako ikonka, sama ikonka nie ma znaczenia), średnia ocen za film, zaokrąglona do 2 miejsc po przecinku. Jeśli film nie został oceniony to treść 'Brak ocen'

   - po kliknięciu w wybrany tytuł włącza się komponent do dodawania nowej oceny za film (może to być np modal, nowa strona, lub rozwija się wiersz i pojawia się możliwość dodania oceny)

   - Komponent dodawania nowej oceny. Tu dowolność, może to być np 10 gwiazdek i po kliknięciu konkretnej dodaje się ocena.

   - Button Save zapisuje odpowiedź i wraca do listy z odświeżoną średnią oceną


   Wybrane technologie:

   - React

   - Redux


(Mile widziane testy)

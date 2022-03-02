#Ticket booking app


###Business scenario (use case)
1. The user selects the day and the time when he/she would like to see the movie.
2. The system lists movies available in the given time interval - title and screening
   times.
3. The user chooses a particular screening.
4. The system gives information regarding screening room and available seats.
5. The user chooses seats, and gives the name of the person doing the reservation
   (name and surname).
6. The system gives back the total amount to pay and reservation expiration time.

###Assumptions
1. The system covers a single cinema with multiple rooms (multiplex).
2. Seats can be booked at latest 15 minutes before the screening begins.
3. Screenings given in point 2. of the scenario should be sorted by title and screening
   time.
4. There are three ticket types: adult (25 PLN), student (18 PLN), child (12.50 PLN).

###Business requirements
1. The data in the system should be valid, in particular:
   a. name and surname should each be at least three characters long, starting
   with a capital letter. The surname could consist of two parts separated with a
   single dash, in this case the second part should also start with a capital letter.
   b. reservation applies to at least one seat.
2. There cannot be a single place left over in a row between two already reserved places.
3. The system should properly handle Polish characters.

##Links

To see all movies in database, we have to visit 
>**/movies**

In order to check avaliable screenings, we have 3 options:
>**/screening** 

without any parameters to see all of the screenings, if we pass parameter day, we get movies for specific date.
Format of date parameter looks i.e. *2022-12-16T14:30:01.001%2B05:30*.

>**/screening/between**

gives us possibility to check screenings between specific days. Two required arguments are **from** and **to** with the same format as before.

>**/reservations**

is a link to make reservation. In RequestBody we pass tickets objects, screeningId and user object, like so:

```javascript
{
   "screeningId": 10,
           "tickets": [
      {
         "type": "Adult",
         "amount": 2
      },
      {
         "type": "Child",
         "amount": 3
      },
      {
         "type": "Student",
         "amount": 2
      }
   ],
           "user":
   {
      "name": "Gabriel",
           "surname": "Stachu",
           "email": "gabriel@jones.pl"
   }
}

```


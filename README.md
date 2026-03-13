#  Airline Reservation System

A Java-based Airline Reservation System that allows users to search for flights, book tickets, manage bookings, and generate flight tickets as PDF files.  
The system also includes an admin panel for managing flights and tracking revenue.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Features

### User Features
- User Registration & Login
- Search Flights by Destination
- Search Flights by Date
- Filter Flights by Price Range
- Book Flight Tickets
- Cancel Booking
- Edit Booking (change tickets or class)
- View Personal Bookings
- Generate Flight Ticket as PDF

### Admin Features
- Add New Flights
- View All Flights
- Edit Flight Details (seats, price, date)
- Delete Flights
- Cancel Passenger Bookings
- View All Bookings
- Calculate Total Revenue
- Revenue per Flight
- Show Most Booked Flights

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Technologies Used

- Java
- MySQL
- JDBC
- iText (PDF generation)

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 📂 Project Structure

```
src/
│
├── Admin_run.java
├── User_run.java
├── BookingService.java
├── DBManager.java
├── Login_System.java
├── TicketGenerator.java
├── Trip.java
├── Booking.java
└── Manger.java
```

---

##  Database

The system uses a MySQL database with tables such as:

- `flights`
- `booking_new`
- `login_system`

These tables store flight data, booking information, and user/admin accounts.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

##  PDF Ticket Generation

After confirming a booking, the system automatically generates a **flight ticket PDF** including:

- Passenger Name
- Flight Number
- Flight Date
- Seat & Class
- Barcode
- QR Code

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

##  How to Run

1. Clone the repository

```
git clone https://github.com/your-username/airline-reservation-system.git
```

2. Create a MySQL database

```
CREATE DATABASE airline;
```

3. Update database credentials in `DBManager.java`

```
String user = "root";
String password = "your_password";
```

4. Run the project from your IDE.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

##  Learning Outcomes

This project helped me practice:

- Object-Oriented Programming (OOP)
- Database Integration using JDBC
- Backend System Design
- Building a complete booking workflow

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Feedback

Feel free to share feedback or suggestions to improve the project!

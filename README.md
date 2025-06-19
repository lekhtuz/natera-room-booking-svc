# Meeting Room Booking API

## Overview
The Meeting Room Booking API is a RESTful service designed to manage the booking of meeting rooms within an office environment. This application allows users to create, retrieve, and delete meeting room bookings, ensuring efficient use of available resources.

## Features
- Manage meeting rooms (create, retrieve, delete)
- Book meeting rooms with user details
- Check for booking conflicts
- Data transfer objects (DTOs) for safe data handling

## Technologies Used
- Java
- Spring Boot
- PostgreSQL
- Maven

## Project Structure
```
meeting-room-booking-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── meetingroombooking
│   │   │               ├── MeetingRoomBookingApplication.java
│   │   │               ├── controller
│   │   │               │   └── MeetingRoomController.java
│   │   │               ├── entity
│   │   │               │   ├── Booking.java
│   │   │               │   ├── MeetingRoom.java
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   ├── BookingRepository.java
│   │   │               │   ├── MeetingRoomRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   ├── BookingService.java
│   │   │               │   ├── MeetingRoomService.java
│   │   │               │   └── UserService.java
│   │   │               └── dto
│   │   │                   ├── BookingDTO.java
│   │   │                   ├── MeetingRoomDTO.java
│   │   │                   └── UserDTO.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── data.sql
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── meetingroombooking
│                       └── MeetingRoomBookingApplicationTests.java
├── pom.xml
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd meeting-room-booking-api
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Configure the database connection in `src/main/resources/application.properties`.
5. Run the application:
   ```
   mvn spring-boot:run
   ```

## API Usage
- **Create Meeting Room**: POST `/api/meeting-rooms`
- **Get All Meeting Rooms**: GET `/api/meeting-rooms`
- **Delete Meeting Room**: DELETE `/api/meeting-rooms/{id}`
- **Book Meeting Room**: POST `/api/bookings`
- **Get All Bookings**: GET `/api/bookings`

## License
This project is licensed under the MIT License.
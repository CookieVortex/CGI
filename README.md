# Flight Logistics

**Flight Logistics** is a web application for flight planning and seat selection on airplanes. The application allows
users to search for available flights with filters for various parameters and select comfortable seats on board with the
option for payment. All data interacts with the server, which processes requests and returns the necessary information.

## Completed Tasks

1. **Flight Planning**:
    - A form is implemented to search for available flights, with filters for departure and arrival locations, departure
      date, and price range.
    - Available flights are displayed in a table with details like departure time, price, and destination.

2. **Seat Selection**:
    - A seat selection page is developed with options for selecting between Business, First, and Economy classes.
    - Information on recommended seats (e.g., extra legroom, near exits) is available for users to view.

3. **Technologies**:
    - **Backend**: Java, Spring Boot, JPA for working with the H2 database.
    - **Frontend**: HTML, CSS, JavaScript for displaying data and interacting with the user.
    - **Docker**: Containerization of the application using Docker Compose.

## How to Run the Application

1. Make sure you have [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/)
   installed.

2. Clone the repository:

bash
git clone https://github.com/CookieVortex/CGI or https://hub.docker.com/u/cookievortex
cd demo

3. Build and run the application using Docker Compose:
   bash
4. docker-compose up --build
5. docker pull cookievortex/demo-app:latest
6. docker run -p 8080:8080 cookievortex/demo-app:latest

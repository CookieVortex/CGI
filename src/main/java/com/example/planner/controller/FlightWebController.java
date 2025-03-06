package com.example.planner.controller;

import com.example.planner.model.Flight;
import com.example.planner.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class FlightWebController {

    private final FlightService flightService;

    /**
     * Displays the main flight page with all available flights.
     */
    @GetMapping
    public String getFlightsPage(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        formatFlights(flights);
        model.addAttribute("flights", flights);
        return "index";
    }

    /**
     * Filters flights based on user input.
     */
    @GetMapping("/flights/filter")
    public String filterFlights(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String departureDate,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            Model model) {

        List<Flight> filteredFlights = flightService.filterFlights(from, to, departureDate, minPrice, maxPrice);
        formatFlights(filteredFlights);

        model.addAttribute("flights", filteredFlights);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "index";
    }

    /**
     * Displays available seats for a selected flight.
     */
    @GetMapping("/seats")
    public String getSeats(@RequestParam("flightId") Long flightId, Model model) {
        model.addAttribute("flightId", flightId);
        return "seats";
    }

    /**
     * Confirms seat selection for a flight.
     */
    @GetMapping("/confirmSeat")
    public String confirmSeat(@RequestParam("flightId") Long flightId, @RequestParam("seat") String seat, Model model) {
        model.addAttribute("message", "Your seat " + seat + " on flight " + flightId + " is confirmed!");
        return "confirmation";
    }

    /**
     * Formats flight dates and times for display.
     */
    private void formatFlights(List<Flight> flights) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Flight flight : flights) {
            if (flight.getDepartureDate() != null) {
                flight.setFormattedDepartureDate(flight.getDepartureDate().format(dateFormatter));
            }
            if (flight.getDepartureTime() != null) {
                flight.setFormattedDepartureTime(flight.getDepartureTime().format(dateTimeFormatter));
            }
        }
    }
}

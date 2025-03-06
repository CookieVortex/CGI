package com.example.planner.service;

import com.example.planner.model.Flight;
import com.example.planner.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> filterFlights(String from, String to, String departureDate, Integer minPrice, Integer maxPrice) {
        List<Flight> flights = flightRepository.findAll();

        List<Flight> list = new ArrayList<>();
        for (Flight flight : flights) {
            if ((from == null || from.trim().isEmpty() || flight.getDepartureLocation().equalsIgnoreCase(from.trim()))) {
                if ((to == null || to.trim().isEmpty() || flight.getArrivalLocation().equalsIgnoreCase(to.trim()))) {
                    if ((departureDate == null || departureDate.trim().isEmpty() || flight.getDepartureDate().equals(departureDate))) {
                        if ((minPrice == null || flight.getPrice() >= minPrice)) {
                            if ((maxPrice == null || flight.getPrice() <= maxPrice)) {
                                list.add(flight);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
}

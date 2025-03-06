package com.example.planner;

import com.example.planner.model.Flight;
import com.example.planner.repository.FlightRepository;
import com.example.planner.service.FlightService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FlightServiceTest {

	@Mock
	private FlightRepository flightRepository;

	@InjectMocks
	private FlightService flightService;

	@Test
	void testGetAllFlights() {
		List<Flight> flights = List.of(new Flight(), new Flight());
		when(flightRepository.findAll()).thenReturn(flights);

		List<Flight> result = flightService.getAllFlights();

		assertEquals(2, result.size());
	}

	@Test
	void testFilterFlightsByFrom() {
		Flight flight1 = new Flight();
		flight1.setDepartureLocation("NYC");
		Flight flight2 = new Flight();
		flight2.setDepartureLocation("LA");

		when(flightRepository.findAll()).thenReturn(List.of(flight1, flight2));

		List<Flight> result = flightService.filterFlights("NYC", null, null, null, null);
		assertEquals(1, result.size());
		assertEquals("NYC", result.getFirst().getDepartureLocation());
	}
}


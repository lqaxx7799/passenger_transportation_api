package web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.repos.TripRepository;
import web.exception.TripNotFoundException;
import web.model.Trip;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {
	@Autowired
	private TripRepository tripRepository;
	
	@GetMapping("/trip")
	public List<Trip> getAll(){
		return tripRepository.findAllAvailable();
	}
	
	@GetMapping("/trip/status={status}")
	public List<Trip> getAllByStatus(@PathVariable int status){
		return tripRepository.findAllByStatus(status);
	}
	
	@GetMapping("/trip/{id}")
	public Trip getById(@PathVariable int id) {
		return tripRepository.findById(id).filter(trip -> !trip.getIsDeleted())
				.orElseThrow(() -> new TripNotFoundException(id));
	}
	
	@PostMapping("/trip")
	public Trip addNew(@Valid @RequestBody Trip trip) {
		trip.setArrivalTime(new Date());
		trip.setCreatedTime(new Date());
		trip.setIsDeleted(false);
		trip.setStatus(0);
		return tripRepository.save(trip);
	}
	
	@PutMapping("/trip/{id}")
	public Trip update(@Valid @RequestBody Trip newTrip, @PathVariable int id) {
		return tripRepository.findById(id).map(trip ->{
			trip.setCoach(newTrip.getCoach());
			trip.setDepartureTime(newTrip.getDepartureTime());
			trip.setEmployee1(newTrip.getEmployee1());
			trip.setEmployee2(newTrip.getEmployee2());
			trip.setNumberOfPassengers(newTrip.getNumberOfPassengers());
			trip.setRoute(newTrip.getRoute());
			trip.setTicketPrice(newTrip.getTicketPrice());
			return tripRepository.save(trip);
		}).orElseThrow(() -> new TripNotFoundException(id));
	}
	
	@DeleteMapping("/trip/{id}")
	public void delete(@PathVariable int id) {
		 tripRepository.findById(id).map(trip ->{
			trip.setIsDeleted(true);
			return tripRepository.save(trip);
		}).orElseThrow(() -> new TripNotFoundException(id));
	}
}

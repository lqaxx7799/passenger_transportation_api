package web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.exception.RouteNotFoundException;
import web.model.Route;
import web.repos.RouteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {
	@Autowired
	private RouteRepository routeRepository;
	
	@GetMapping("/route")
	public List<Route> getAll(){
		return routeRepository.findAllAvailable();
	}
	
	@GetMapping("/route/{id}")
	public Route getById(@PathVariable int id){
		return routeRepository.findById(id).filter(route -> !route.getIsDeleted())
				.orElseThrow(() -> new RouteNotFoundException(id));
	}
	
	@PostMapping("/route")
	public Route addNew(@RequestBody Route route) {
		route.setCreatedTime(new Date());
		route.setIsDeleted(false);
		return routeRepository.save(route);
	}
	
	@PutMapping("/route/{id}")
	public Route update(@RequestBody Route newRoute, @PathVariable int id){
		return routeRepository.findById(id).map(route -> {
			route.setStartingPoint(newRoute.getDestinationPoint());
			route.setDestinationPoint(newRoute.getDestinationPoint());
			route.setDistance(newRoute.getDistance());
			route.setRouteComplexity(newRoute.getRouteComplexity());
			route.setEstimatedHours(newRoute.getEstimatedHours());
			route.setTrips(newRoute.getTrips());
			return routeRepository.save(route);
		}).orElseThrow(() -> new RouteNotFoundException(id));
	}
	
	@DeleteMapping("/route/{id}")
	public void delete(@PathVariable int id) {
		routeRepository.findById(id).map(route ->{
			route.setIsDeleted(true);
			return routeRepository.save(route);
		}).orElseThrow(()-> new RouteNotFoundException(id));
	}
}

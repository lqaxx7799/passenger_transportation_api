package web.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.exception.CoachNotFoundException;
import web.model.Coach;
import web.repos.CoachRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CoachController {
	@Autowired
	private CoachRepository coachRepository;

	@GetMapping("/coach")
	public List<Coach> getAll() {
		return coachRepository.findAllAvailable();
	}

	@GetMapping("/coach/{id}")
	public Coach getById(@PathVariable int id) {
		return coachRepository.findById(id).filter(coach -> !coach.getIsDeleted())
				.orElseThrow(() -> new CoachNotFoundException(id));
	}

	@PostMapping("/coach")
	public Coach addNew(@Valid @RequestBody Coach coach) {
		// TODO: implement validation
		coach.setCreatedTime(new Date());
		coach.setIsDeleted(false);
		return coachRepository.save(coach);
	}

	@PutMapping("/coach/{id}")
	public Coach update(@Valid @RequestBody Coach newCoach, @PathVariable int id) {
		// if found, perform update, else throw error
		return coachRepository.findById(id).map(coach -> {
			// skip id, createdTime and isDeleted
			coach.setCoachModel(newCoach.getCoachModel());
			coach.setCoachVersion(newCoach.getCoachVersion());
			coach.setColor(newCoach.getColor());
			coach.setLastMaintainedDate(newCoach.getLastMaintainedDate());
			coach.setLicensePlate(newCoach.getLicensePlate());
			coach.setManufacturerName(newCoach.getManufacturerName());
			coach.setNumberOfSeats(newCoach.getNumberOfSeats());
			coach.setUsedYears(newCoach.getUsedYears());
			return coachRepository.save(coach);
		}).orElseThrow(() -> new CoachNotFoundException(id));
	}

	@DeleteMapping("/coach/{id}")
	public Coach delete(@PathVariable int id) {
		// if found, perform update, else throw error
		return coachRepository.findById(id).map(coach -> {
			coach.setIsDeleted(true);
			return coachRepository.save(coach);
		}).orElseThrow(() -> new CoachNotFoundException(id));
	}
	
	
}

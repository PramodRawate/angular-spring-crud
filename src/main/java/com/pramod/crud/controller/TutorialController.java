package com.pramod.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.crud.model.Tutorial;
import com.pramod.crud.repository.TutorialRepository;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required=false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial> ();
			if(title==null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
			
			if(tutorials.isEmpty())
				return new ResponseEntity<>(tutorials, HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/tutorial/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@RequestParam(required=true) long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		if(tutorialData.isPresent())
			return new ResponseEntity<Tutorial>(tutorialData.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Tutorial>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			tutorialRepository.save(entity)
		}
		return null;
	}
}

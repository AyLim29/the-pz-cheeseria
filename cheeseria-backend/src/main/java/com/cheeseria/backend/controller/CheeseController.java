package com.cheeseria.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheeseria.backend.repository.CheeseRepository;
import com.cheeseria.backend.model.Cheese;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class CheeseController {
    @Autowired
    private CheeseRepository cheeseRepository;

    @GetMapping("/getAllCheeses")
    public ResponseEntity<List<Cheese>> getAllCheeses() {
        try {
            List<Cheese> cheeseList = new ArrayList<>();
            cheeseRepository.findAll().forEach(cheeseList::add);

            if (cheeseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cheeseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCheeseById/{id}")
    public ResponseEntity<Cheese> getCheeseById(@PathVariable Long id) {
        Optional<Cheese> cheeseData = cheeseRepository.findById(id);
        try {
            if (cheeseData.isPresent()) {
                return new ResponseEntity<>(cheeseData.get(), HttpStatus.OK);
            }
    
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCheese")
    public ResponseEntity<Cheese> addCheese(@RequestBody Cheese cheese) {
        try {
            Cheese cheeseObj = cheeseRepository.save(cheese);
            return new ResponseEntity<>(cheeseObj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateCheeseById/{id}")
    public ResponseEntity<Cheese> updateCheeseById(@PathVariable Long id, @RequestBody Cheese newCheeseData) {
        try {
            Optional<Cheese> oldCheeseData = cheeseRepository.findById(id);

            if (oldCheeseData.isPresent()) {
                //TODO Make sure new cheese data is valid
                Cheese updatedCheeseData = oldCheeseData.get();
                updatedCheeseData.setName(newCheeseData.getName());
                updatedCheeseData.setColour(newCheeseData.getColour());
                updatedCheeseData.setPricePerKilo(newCheeseData.getPricePerKilo());

                Cheese cheeseObj = cheeseRepository.save(updatedCheeseData);
                return new ResponseEntity<>(cheeseObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCheeseById/{id}")
    public ResponseEntity<Cheese> deleteCheeseById(@PathVariable Long id) {
        try {
            //TODO Respond accordingly if no cheeses with that ID are recorded
            cheeseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

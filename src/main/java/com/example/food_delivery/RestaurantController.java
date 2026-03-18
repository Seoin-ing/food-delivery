package com.example.food_delivery;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List <Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
    
    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        //TODO: process POST request
        
        return restaurantRepository.save(restaurant);
    }
    
    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        //TODO: process PUT request
        Restaurant existing = restaurantRepository.findById(id).orElseThrow();
        existing.setName(restaurant.getName());
        existing.setCategory(restaurant.getCategory());
        existing.setAddress(restaurant.getAddress());
        return restaurantRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
        return "삭제 완료!";
    }
}

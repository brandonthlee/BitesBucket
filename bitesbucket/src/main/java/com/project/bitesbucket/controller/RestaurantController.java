package com.project.bitesbucket.controller;

import com.google.maps.errors.ApiException;
import com.project.bitesbucket.model.Restaurant;
import com.project.bitesbucket.repository.RestaurantRepository;
import com.project.bitesbucket.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    RestaurantRepository restaurantRepository;

    @GetMapping("/all")
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping
    public String add(@RequestBody Restaurant restaurant) throws InterruptedException, ApiException, IOException {
        restaurantService.addRestaurant(restaurant);
        return String.format("Restaurant: %s has been added.", restaurant.getName());
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Restaurant newRestaurant) throws InterruptedException, ApiException, IOException {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        restaurantService.updateRestaurant(restaurant, newRestaurant);
        return String.format("Restaurant: %s has been updated.", restaurant.getName());
    }
}

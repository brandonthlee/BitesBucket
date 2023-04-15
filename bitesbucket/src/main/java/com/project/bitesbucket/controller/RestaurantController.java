package com.project.bitesbucket.controller;

import com.project.bitesbucket.model.Restaurant;
import com.project.bitesbucket.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/add")
    public String add(@RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return String.format(">>> Restaurant with name: %s, address: %s has been added.", restaurant.getName(), restaurant.getAddress());
    }

    @GetMapping("/getAll")
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }
}

package com.project.bitesbucket.service;

import com.project.bitesbucket.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant);
    public List<Restaurant> getAllRestaurants();
}

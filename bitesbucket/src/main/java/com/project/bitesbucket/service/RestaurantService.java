package com.project.bitesbucket.service;

import com.google.maps.errors.ApiException;
import com.project.bitesbucket.model.Restaurant;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {
    public List<Restaurant> getAllRestaurants();
    public Restaurant getRestaurant(Long id);
    public Restaurant addRestaurant(Restaurant restaurant) throws InterruptedException, ApiException, IOException;
    public Restaurant updateRestaurant(Restaurant restaurant, Restaurant newRestaurant) throws InterruptedException, ApiException, IOException;
}

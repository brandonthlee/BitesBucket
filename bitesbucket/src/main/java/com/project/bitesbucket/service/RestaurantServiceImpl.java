package com.project.bitesbucket.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.project.bitesbucket.model.Restaurant;
import com.project.bitesbucket.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.orElse(null);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) throws InterruptedException, ApiException, IOException {
        getCoordinates(restaurant).ifPresent(coords -> {
            restaurant.setLatitude(coords[0]);
            restaurant.setLongitude(coords[1]);
        });
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, Restaurant newRestaurant) throws InterruptedException, ApiException, IOException {
        if (newRestaurant.getName() != null) {
            restaurant.setName(newRestaurant.getName());
        }
        if (newRestaurant.getAddress() != null) {
            getCoordinates(newRestaurant).ifPresent(coords -> {
                restaurant.setLatitude(coords[0]);
                restaurant.setLongitude(coords[1]);
            });
            restaurant.setAddress(newRestaurant.getAddress());
        }
        if (newRestaurant.getDescription() != null) {
            restaurant.setDescription(newRestaurant.getDescription());
        }
        if (newRestaurant.getRatings() > 0.0) {
            restaurant.setRatings(newRestaurant.getRatings());
        }

        return restaurantRepository.save(restaurant);
    }

    private Optional<double[]> getCoordinates(Restaurant restaurant) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(googleMapsApiKey)
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, restaurant.getAddress()).await();
        if (results.length > 0) {
            LatLng location = results[0].geometry.location;
            double latitude = location.lat;
            double longitude = location.lng;
            return Optional.of(new double[]{latitude, longitude});
        } else {
            return Optional.empty();
        }
    }
}

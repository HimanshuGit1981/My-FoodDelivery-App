package com.iris.eatfeat.restaurantlisting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iris.eatfeat.restaurantlisting.entity.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {

	List<Restaurant> findByNameLike(String name);
	
	List<Restaurant> findByAddressLike(String address);
	
	List<Restaurant> findByCityLike(String city);
}

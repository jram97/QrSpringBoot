package com.qr.code.generator.web.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qr.code.generator.web.app.domain.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query(nativeQuery = true, value = "SELECT cities.id, cities.city, cities.id_country FROM cities INNER JOIN scans ON cities.id = scans.id_city " +
            "WHERE scans.id_campaign = ?1")
    List<City> getCampaignCities(Long id);
}

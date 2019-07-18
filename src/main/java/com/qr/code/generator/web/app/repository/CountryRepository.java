package com.qr.code.generator.web.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.qr.code.generator.web.app.domain.Country;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query(nativeQuery = true, value = "SELECT countries.id, countries.country FROM countries INNER JOIN cities ON countries.id = cities.id_country INNER JOIN " +
            "scans ON cities.id = scans.id_city " +
            "WHERE scans.id_campaign = ?1")
    List<Country> getCampaignCountry(Long id);

}

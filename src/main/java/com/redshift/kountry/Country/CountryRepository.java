package com.redshift.kountry.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface  CountryRepository extends JpaRepository<Country,Long> {


    @Query("SELECT c FROM Country c JOIN c.states s JOIN s.cities city WHERE city.name = ?1")
    public Set<Country> findCountryByCity(String city);
}

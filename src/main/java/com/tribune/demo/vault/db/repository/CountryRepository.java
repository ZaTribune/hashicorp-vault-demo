package com.tribune.demo.vault.db.repository;

import com.tribune.demo.vault.db.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}

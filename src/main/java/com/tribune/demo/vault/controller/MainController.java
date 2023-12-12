package com.tribune.demo.vault.controller;


import com.tribune.demo.vault.db.entity.Country;
import com.tribune.demo.vault.db.entity.CountryInfo;
import com.tribune.demo.vault.db.repository.CountryRepository;
import com.tribune.demo.vault.domain.AddCountryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/countries")
@RestController
public class CountryController {


    private final CountryRepository repository;

    @GetMapping
    public List<Country> getCountries() {
        return repository.findAll();
    }

    @GetMapping("/names")
    public Collection<CountryInfo> getCountryNames() {
        return repository.findAllByNameStartingWith("");
    }


    @PostMapping("/country")
    public Country addCountry(@RequestBody @Valid AddCountryRequest request) {
        Country country = Country.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return repository.save(country);
    }

}

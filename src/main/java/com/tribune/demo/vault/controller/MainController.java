package com.tribune.demo.vault.controller;


import com.tribune.demo.vault.db.entity.Country;
import com.tribune.demo.vault.db.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class MainController {


    private final CountryRepository repository;

    @GetMapping
    public List<Country> getCountries() {
        return repository.findAll();
    }

}

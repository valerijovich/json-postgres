package com.valerijovich.jsonpostgres.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valerijovich.jsonpostgres.entities.Person;
import com.valerijovich.jsonpostgres.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("json")
    public void json() {
        URL url = this.getClass().getClassLoader().getResource("people.json");

        if (url != null) {
            File jsonFile = new File(url.getFile());

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<Person> people = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {

                });

                personRepository.saveAll(people);

                logger.info("All records saved.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("url is null.");
        }
    }
}

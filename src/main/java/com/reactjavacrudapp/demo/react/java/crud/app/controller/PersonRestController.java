package com.reactjavacrudapp.demo.react.java.crud.app.controller;

import com.reactjavacrudapp.demo.react.java.crud.app.model.Person;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.PersonServiceAPI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

  @Autowired
  private PersonServiceAPI personServiceAPI;

  @GetMapping("/all")
  public List<Person> getAll() {
    return personServiceAPI.getAll();
  }

  @PostMapping("/save")
  public ResponseEntity save(@RequestBody Person person) {
    Person obj = personServiceAPI.save(person);

    return new ResponseEntity(obj, HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public Person getOne(@PathVariable("id") Long id) {
    return personServiceAPI.get(id);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    Person person = personServiceAPI.get(id);

    if (person != null) {
      personServiceAPI.delete(id);
    } else {
      return new ResponseEntity(person, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity(person, HttpStatus.OK);
  }

}

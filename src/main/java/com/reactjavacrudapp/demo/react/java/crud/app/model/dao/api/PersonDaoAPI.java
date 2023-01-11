package com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api;

import com.reactjavacrudapp.demo.react.java.crud.app.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDaoAPI extends CrudRepository<Person, Long> {

}

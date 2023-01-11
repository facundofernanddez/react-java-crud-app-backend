package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.commons.GenericServiceImpl;
import com.reactjavacrudapp.demo.react.java.crud.app.model.Person;
import com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api.PersonDaoAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.PersonServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Person, Long> implements PersonServiceAPI {

  @Autowired
  private PersonDaoAPI personDaoAPI;

  @Override
  public CrudRepository<Person, Long> getDao() {
    return personDaoAPI;
  }

}

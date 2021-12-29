package com.attraction.monument;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class MonumentService {
  @Autowired
  MonumentRepository monumentRepo;

  List<Monument> getMonumets() {
    return monumentRepo.findAll();
  }

  Monument getMonument(Integer id) {
    Optional<Monument> result = monumentRepo.findById(id);
    return result.isPresent() ? result.get() : null;
  }

  List<Monument> getMonumentByCity(String city) {
    return monumentRepo.findAll(Example.of(new Monument(null, null, null, null, null, null, null, null, city)));
  }
}

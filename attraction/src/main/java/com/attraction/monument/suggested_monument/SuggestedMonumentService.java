package com.attraction.monument.suggested_monument;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestedMonumentService {
  
  @Autowired
  SuggestedMonumentRepository suggestedMonumentRepository;

  public Integer getNewId() {
    List<SuggestedMonument> monumentList = suggestedMonumentRepository.findAll();
    return monumentList.get(monumentList.size() - 1).getId() + 1;
  }
  public void save(SuggestedMonument monument) {
    suggestedMonumentRepository.save(monument);
  }


}

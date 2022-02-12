package com.attraction.suggestedmonument;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuggestedMonumentService {

  private SuggestedMonumentRepository suggestedMonumentRepository;

  public Integer getNewId() {
    List<SuggestedMonument> monumentList = suggestedMonumentRepository.findAll();
    return monumentList.get(monumentList.size() - 1).getId() + 1;
  }
  public void save(SuggestedMonument monument) {
    suggestedMonumentRepository.save(monument);
  }


}

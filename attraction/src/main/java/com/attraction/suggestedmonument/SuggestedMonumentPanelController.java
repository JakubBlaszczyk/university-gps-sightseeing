package com.attraction.suggestedmonument;

import com.attraction.security.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SuggestedMonumentPanelController {

  private SuggestedMonumentService service;

  @PostMapping("/monument/suggestion")
  @PreAuthorize("hasAnyAuthority('GUIDE', 'ADMIN')")
  public ResponseEntity<MessageResponse> suggestMonument(SuggestedMonumentRequest monument) {
    service.save(new SuggestedMonument(service.getNewId(), monument.getName(), monument.getCity(), monument.getType()));
    return ResponseEntity.ok(new MessageResponse("Thank you for this wonderful suggestion!"));
  }
}
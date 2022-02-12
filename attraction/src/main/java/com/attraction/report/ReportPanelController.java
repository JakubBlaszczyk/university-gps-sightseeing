package com.attraction.report;

import com.attraction.security.MessageResponse;
import com.attraction.security.jwt.JwtUtils;
import com.attraction.user.User;
import com.attraction.user.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ReportPanelController {

  private ReportService reportService;
  private UserService userService;
  private JwtUtils jwtUtils;

  @PostMapping("/report")
  @PreAuthorize("hasAnyAuthority('USER', 'GUIDE', 'ADMIN')")
  public ResponseEntity<MessageResponse> report(@RequestHeader String Cookie, String content) {
    User user = userService.findByUsername(jwtUtils.getUserNameFromJwtToken(Cookie.substring(Cookie.indexOf('=') + 1)));
    reportService.save(new Report(reportService.getNewId(), user.getId(), content));
    return ResponseEntity.ok(new MessageResponse("Thank you for the report, surely we will look into it"));
  }
}
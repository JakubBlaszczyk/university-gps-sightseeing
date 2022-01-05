package com.attraction.reset_password;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.attraction.reset_password.temporary_token.TemporaryToken;
import com.attraction.reset_password.temporary_token.TemporaryTokenService;
import com.attraction.security.MessageResponse;
import com.attraction.security.jwt.JwtUtils;
import com.attraction.user.User;
import com.attraction.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@Controller
public class ResetPasswordPanelController {

  @Autowired
  TemporaryTokenService tokenService;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserService userService;

  @PostMapping("/password/reset")
  public @ResponseBody ResponseEntity<MessageResponse> generateToken(
      @Email @Size(max = 50) String email) {
    User user = userService.findByEmail(email);
    if (user == null) {
      return ResponseEntity.badRequest().body(new MessageResponse("User with this email not found"));
    }
    tokenService.save(new TemporaryToken(Integer.valueOf(user.hashCode()), user.getId()));
    try {
      MailSender.sendMail(email, user.hashCode());
    } catch (Exception e) {
      log.warn("Exception while sending a mail", e);
      return ResponseEntity.badRequest().body(new MessageResponse("Error while sending a message"));
    }
    return ResponseEntity.ok(new MessageResponse("Sent email with reset token"));
  }

  @PostMapping("/temp")
  public @ResponseBody ResponseEntity<MessageResponse> temp(String temp) {
    return ResponseEntity.ok(new MessageResponse("Received"));
  }
}

package com.attraction.signup;

import com.attraction.role.Role;
import com.attraction.security.MessageResponse;
import com.attraction.user.User;
import com.attraction.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Slf4j
public class SignUpPanelController {

  @Autowired
  UserService userRepoService;

  @Autowired
  PasswordEncoder encoder;

  @GetMapping("/signup")
  public String loadPanel() {
    return "signup";
  }

  @PostMapping("/signup")
  public @ResponseBody ResponseEntity<MessageResponse> createNewUser(@Valid SignupRequest signupRequest,
      BindingResult bindingResult) {
    validateInput(bindingResult);
    if (Boolean.TRUE.equals(userRepoService.existsByUsername(signupRequest.getUsername()))) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (Boolean.TRUE.equals(userRepoService.existsByEmail(signupRequest.getEmail()))) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    String strRoles = signupRequest.getRole();
    Role role;

    if (strRoles == null) {
      role = Role.USER;
    } else {
      switch (strRoles) {
        case "admin":
          role = Role.ADMIN;
          break;
        case "guide":
          role = Role.GUIDE;
          break;
        case "user":
          role = Role.USER;
          break;
        default:
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Role is not found"));
      }
    }

    userRepoService.save(new User(userRepoService.getNewId(), signupRequest.getUsername(),
        encoder.encode(signupRequest.getPassword()), signupRequest.getEmail(), "", Integer.valueOf(0), "", "",
        role));

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  private void validateInput(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.debug(bindingResult.getAllErrors().toString());
      throw new ValidationException(bindingResult.getAllErrors().toString());
    }
  }
}
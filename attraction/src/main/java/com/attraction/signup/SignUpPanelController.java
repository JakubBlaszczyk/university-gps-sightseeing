package com.attraction.signup;

import com.attraction.role.Role;
import com.attraction.security.MessageResponse;
import com.attraction.user.UserObject;
import com.attraction.user.UserObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class SignUpPanelController {

  @Autowired
  UserObjectService userRepoService;

  @Autowired
  PasswordEncoder encoder;

  @GetMapping("/signup")
  public String loadPanel() {
    return "signup";
  }

  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> createNewUser(@Valid @RequestBody SignupRequest signupRequest,
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
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Passed role is null"));
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

    userRepoService.save(new UserObject(userRepoService.findMaxId() + 1, signupRequest.getUsername(),
        encoder.encode(signupRequest.getPassword()), signupRequest.getEmail(), "", Integer.valueOf(0), "",
        role));

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  private void validateInput(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.debug(bindingResult.getAllErrors().toString());
      throw new RuntimeException(bindingResult.getAllErrors().toString());
    }
  }
}
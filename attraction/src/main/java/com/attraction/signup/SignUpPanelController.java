package com.attraction.signup;

import com.attraction.role.ERole;
import com.attraction.role.Role;
import com.attraction.role.RoleRepository;
import com.attraction.security.MessageResponse;
import com.attraction.user.UserObject;
import com.attraction.user.UserObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class SignUpPanelController {

  @Autowired
  UserObjectRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @GetMapping("/signup")
  public String loadPanel() {
    return "signup";
  }

  @PostMapping("/signup")
  public ResponseEntity<?> createNewUser(@Valid @RequestBody SignupRequest signupRequest) {
    if (Boolean.TRUE.equals(userRepository.existsByUsername(signupRequest.getUsername()))) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }


    Set<String> strRoles = signupRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      try {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
        roles.add(userRole);
      } catch (Exception e) {
        throw new RuntimeException("Error: Role is not found.");
      }
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            try {
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
              roles.add(adminRole);
            } catch (Exception e) {
              throw new RuntimeException("Error: Role is not found.");
            }
            break;
          case "mod":
            try {
              Role guideRole = roleRepository.findByName(ERole.ROLE_GUIDE);
              roles.add(guideRole);
            } catch (Exception e) {
              throw new RuntimeException("Error: Role is not found.");
            }
            break;
          default:
            try {
              Role userRole = roleRepository.findByName(ERole.ROLE_USER);
              roles.add(userRole);
            } catch (Exception e) {
              throw new RuntimeException("Error: Role is not found.");
            }
        }
      });
    }

    // Create new user's account
    UserObject userObject = new UserObject(signupRequest.getUsername(),
            signupRequest.getEmail(),
            encoder.encode(signupRequest.getPassword()), roles);
    userRepository.save(userObject);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

}

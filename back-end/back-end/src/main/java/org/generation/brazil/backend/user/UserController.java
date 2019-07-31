package org.generation.brazil.backend.user;

import org.generation.brazil.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')")
  public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
    return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
  }

  @PostMapping("/user/check-username-availability")
  public UserIdentityAvailability checkUsernameAvailability(
      @RequestParam String username) {
    Boolean isAvailable = !userRepository.existsByUsername(username);
    return new UserIdentityAvailability(isAvailable);
  }

  @PostMapping("/user/check-email-availability")
  public UserIdentityAvailability checkEmailAvailability(
      @RequestParam String email) {
    Boolean isAvailable = !userRepository.existsByEmail(email);
    return new UserIdentityAvailability(isAvailable);
  }

  @PostMapping("/users")
  public UserProfile getUserProfile(@RequestParam String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User not found."));

    return new UserProfile(user.getId(), user.getUsername(), user.getName());
  }

}

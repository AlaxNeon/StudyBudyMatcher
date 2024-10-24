package com.sbm.backend.controller;

import com.sbm.backend.dto.UserDTO;
import com.sbm.backend.dto.UserRegistrationDTO;
import java.sql.Timestamp;

import com.sbm.backend.model.CountryEntity;
import com.sbm.backend.model.ProfileEntity;
import com.sbm.backend.model.UserEntity;
import com.sbm.backend.service.CountryService;
import com.sbm.backend.service.ProfileService;
import com.sbm.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;


	@Autowired
	private CountryService countryService;

	@GetMapping("/countries")
	public ResponseEntity<List<CountryEntity>> getAllCountries() {
	    List<CountryEntity> countries = countryService.getAllCountries();
	    return ResponseEntity.ok(countries);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userDto) {
	    if (userDto.getBio() == null || userDto.getBio().trim().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bio is required.");
	    }

	    if (userService.existsByEmail(userDto.getEmail())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
	    }

	    if (userService.existsByFullName(userDto.getFullName())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Full Name already exists");
	    }

	    UserEntity user = new UserEntity();
	    user.setFullName(userDto.getFullName());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(userDto.getPassword());
	    user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	    user.setMeetLink(userDto.getMeetLink()); // Set the meeting link

	    UserEntity registeredUser = userService.registerUser(user);

	    ProfileEntity profile = new ProfileEntity();
	    profile.setUser(registeredUser);
	    CountryEntity country = countryService.findById(userDto.getCountryId());
	    profile.setCountry(country);
	    profile.setBio(userDto.getBio());
	    profile.setMeetLink(userDto.getMeetLink());
	    profileService.createOrUpdateProfile(profile);
	    userService.saveUserInterests(registeredUser, userDto.getInterests());

	    return ResponseEntity.ok(registeredUser);
	}





    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserEntity loginRequest) {
        Optional<UserEntity> user = userService.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body("Incorrect email or password");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserEntity> userEntity = userService.findById(id);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            UserDTO userDTO = new UserDTO();
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserEntity> users = userService.findAllUsers();

        // Map to DTO to include only necessary fields like fullName and interests
        List<UserDTO> userDTOs = users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setFullName(user.getFullName());
            dto.setInterests(user.getUserInterests().stream()
                .map(ui -> ui.getInterest().getInterestName())
                .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete user");
        }
    }
}

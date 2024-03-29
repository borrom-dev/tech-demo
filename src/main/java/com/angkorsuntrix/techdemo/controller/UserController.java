package com.angkorsuntrix.techdemo.controller;

import com.angkorsuntrix.techdemo.entity.User;
import com.angkorsuntrix.techdemo.exception.ResourceNotFoundException;
import com.angkorsuntrix.techdemo.payload.UserProfile;
import com.angkorsuntrix.techdemo.payload.UserSummary;
import com.angkorsuntrix.techdemo.repository.PollRepository;
import com.angkorsuntrix.techdemo.repository.UserRepository;
import com.angkorsuntrix.techdemo.repository.VoteRepository;
import com.angkorsuntrix.techdemo.security.CurrentUser;
import com.angkorsuntrix.techdemo.security.UserPrincipal;
import com.angkorsuntrix.techdemo.service.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/profile/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Long pollCount = pollRepository.countByCreateBy(user.getId());
        Long voteCount = voteRepository.countByUserId(user.getId());
        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreateAt(), pollCount, voteCount);
    }

}

package com.angkorsuntrix.techdemo.controller;

import com.angkorsuntrix.techdemo.repository.PollRepository;
import com.angkorsuntrix.techdemo.repository.UserRepository;
import com.angkorsuntrix.techdemo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;

}

package com.rps.service;

import com.rps.entity.User;
import com.rps.entity.config.Config;
import com.rps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;

import java.io.*;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    Gson gson = new Gson();
    BufferedReader br = new BufferedReader(new FileReader("/Users/konstantin/Documents/rps/config.json"));
    String path = "/Users/konstantin/Documents/rps/config.json";

    Config config = gson.fromJson(br, Config.class);

    @Autowired
    private UserRepository userRepository;

    public UserService() throws FileNotFoundException {
    }

    public User createUser(User user) throws IOException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("This user is exists");
        }
        User createdUser = userRepository.save(user);
        createdUser.setUuid(UUID.randomUUID().toString());

        Config.Inbound.Settings.Client firstClient = config.getInbounds().getFirst().getSettings().getClients().getFirst();
        firstClient.setId(createdUser.getUuid());
        firstClient.setEmail(createdUser.getEmail());

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(config, writer);
        }

        return createdUser;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("User not exists!");
        }
        return userRepository.findUserById(id);
    }
}
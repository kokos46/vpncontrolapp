package com.rps.service;

import com.rps.entity.User;
import com.rps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;

import java.io.*;
import java.util.*;

@Service
@Transactional
public class UserService {

    public int emailCount = 0;
    private String path = "/Users/konstantin/Documents/rps/config.yml";

    @Autowired
    private UserRepository userRepository;

    public UserService() throws FileNotFoundException {
    }

    public User createUser(User user) throws IOException {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("This user is exists");
        }
        User createdUser = userRepository.save(user);
        createdUser.setUuid(UUID.randomUUID().toString());


        // Загружаем YAML как Map
        Yaml yaml = new Yaml();
        Map<String, Object> data;
        try (InputStream input = new FileInputStream(path)) {
            data = yaml.load(input);
        }

        if (data == null) {
            data = new HashMap<>();
            data.put("Nodes", new ArrayList<>());
        }

        List<Map<String, Object>> nodes = (List<Map<String, Object>>) data.get("Nodes");
        if (nodes.isEmpty()) {
            Map<String, Object> node = new HashMap<>();
            node.put("PanelType", "None");
            node.put("ControllerConfig", Map.of("userConnLimit", 1));
            node.put("Users", new ArrayList<Map<String, Object>>());
            nodes.add(node);
        }

        Map<String, Object> firstNode = nodes.get(0);
        List<Map<String, Object>> users = (List<Map<String, Object>>) firstNode.get("Users");

        Map<String, Object> newUser = new HashMap<>();
        newUser.put("uuid", createdUser.getUuid());

        users.add(newUser);

        // Сохраняем YAML обратно
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yamlWriter = new Yaml(options);
        try (FileWriter writer = new FileWriter(path)) {
            yamlWriter.dump(data, writer);
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
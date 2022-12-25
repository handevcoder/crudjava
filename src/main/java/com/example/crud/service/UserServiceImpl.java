package com.example.crud.service;

import com.example.crud.repository.UserRepository;
import com.example.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User createUser(User user) {
        User users = userRepo.save(user);
        return users;
    }

    @Override
    public User getUserById(Long userID) {
        User userDB = userRepo.findById(userID).get();
        return userRepo.save(userDB);
    }

    @Override
    public List<User> fetchUserList() {
            List<User> list = userRepo.findAll();
            return list;
    }

    @Override
    public User updateUser(User user, Long userID) {
        User userDB = userRepo.findById(userID).get();

        if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
            userDB.setUsername(user.getUsername());
        }

        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            userDB.setEmail(user.getEmail());
        }

        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            userDB.setPassword(user.getPassword());
        }

        /*if (Objects.nonNull(user.getRole()) && !"".equalsIgnoreCase(String.valueOf(user.getRole()))) {
            userDB.setRole(user.getRole());
        }*/
        return userRepo.save(userDB);
    }

    @Override
    public String deleteUserById(Long userID) {
        userRepo.deleteById(userID);
        return "Delete user with ID "+userID+" Succesfully.";
    }

}

package com.example.crud.service;

import com.example.crud.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> fetchUserList();
    User updateUser(User user, Long userID);
    String deleteUserById(Long userID);

//    private final UserRepository userRepo;
//
//    private UserService(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    public List<User> findAll() {
//        var it = userRepo.findAll();
//        var users = new ArrayList<User>();
//        it.forEach(e -> users.add(e));
//
//        return users;
//    }
//
//    public Long count() {
//        return userRepo.count();
//    }
//
//    public void deleteById(Long userID) {
//        userRepo.deleteById(userID);
//    }

}

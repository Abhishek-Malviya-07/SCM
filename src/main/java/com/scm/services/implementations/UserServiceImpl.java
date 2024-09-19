package com.scm.services.implementations;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleList(List.of(AppConstants.ROLE_USER));
       return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
       return userRepo.findById(id);
    }
    @Override
    public Optional<User> updateUser(User user) {
       User newUser = userRepo.findById(user.getUserId()).
       orElseThrow(() -> new ResourceNotFoundException("User not found"));
       newUser.setName(user.getName());
       newUser.setEmail(user.getEmail());
       newUser.setPassword(user.getPassword());
       newUser.setPhoneNumber(user.getPhoneNumber());
       newUser.setAbout(user.getAbout());
       newUser.setProfilePic(user.getProfilePic());
       newUser.setEnabled(user.isEnabled());
       newUser.setEmailVerified(user.isEmailVerified());
       newUser.setPhoneVerified(user.isPhoneVerified());
       newUser.setProvider(user.getProvider());
       newUser.setProviderUserId(user.getProviderUserId());

       User save = userRepo.save(newUser);
       return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(User user) {
        User newUser = userRepo.findById(user.getUserId()).
         orElseThrow(() -> new ResourceNotFoundException("User not found")); 

         userRepo.delete(newUser);
    }

    @Override
    public boolean isUserExist(String userId) {
        User newUser = userRepo.findById(userId).orElse(null);
        return newUser!=null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User newUser = userRepo.findByEmail(email).orElse(null);
        return newUser!=null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

}

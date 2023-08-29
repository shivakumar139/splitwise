package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.UserService;
import com.sun.jdi.InternalException;
import jakarta.validation.ConstraintViolationException;
import jdk.jshell.spi.ExecutionControl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String addUser(User user) {
        try {
            userRepository.save(user);
        }catch (DataIntegrityViolationException ex){
            System.out.println(ex);
            throw ex;
        }
        return "Success";

    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        }catch (Exception ex ){
            throw new InternalException("Internal Server Error");
        }
    }
}

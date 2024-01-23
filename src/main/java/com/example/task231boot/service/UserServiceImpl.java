package com.example.task231boot.service;

import com.example.task231boot.dao.UserRepository;
import com.example.task231boot.dao.UserRepository;
import com.example.task231boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userDAO;

    @Autowired
    public UserServiceImpl(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> allUsers() {
        return userDAO.findAll();
    }

    @Override
    public void add(User user) {
        userDAO.save(user);
    }

    @Override
    public void delete(int id) {
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()){
            userDAO.deleteById(id);
        }
        else {
            throw new NoSuchElementException ("User not found");
        }
    }


    @Override
    public void edit(User user) {
        Optional<User> userOptional = userDAO.findById(user.getId());
        if (userOptional.isPresent()){
            User updatedUser = userOptional.get();
            updatedUser.setName(user.getName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAge(user.getAge());
            userDAO.save(updatedUser);
        }
        else {
            throw new NoSuchElementException("User not found");
        }
    }

    @Override
    public User getById(int id) {
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()){
            return userOptional.get();
        }
        else {
            throw new NoSuchElementException ("User not found");
        }
    }
}

package com.manish.userinfo.service;


import com.manish.userinfo.dto.UserDTO;
import com.manish.userinfo.entity.User;
import com.manish.userinfo.mapper.UserMapper;
import com.manish.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }
/*
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());

        User savedUser = userRepo.save(user);

        UserDTO savedUserDTO = new UserDTO();
        savedUserDTO.setUserName(savedUser.getUserName());
        savedUserDTO.setUserPassword(savedUser.getUserPassword());
        savedUserDTO.setAddress(savedUser.getAddress());
        savedUserDTO.setCity(savedUser.getCity());
        return savedUserDTO;
    }
 */

    public UserDTO addUser(UserDTO userDTO) {
        //User user = UserMapper.mapUserDTOtoUserEntity(userDTO); //in case of static mapUserDTOtoUserEntity
        User user = userMapper.mapUserDTOtoUserEntity(userDTO);
        User savedUser = userRepo.save(user);
        //return UserMapper.mapUserEntitytoUserDTO(savedUser); //in case of static mapUserEntitytoUserDTO
        return userMapper.mapUserEntitytoUserDTO(savedUser);
    }

//    public UserDTO fetchUserDetailsById(Integer userId) {
//        Optional<User> fetchedUser = userRepo.findById(userId);
//        if (fetchedUser.isPresent()) {
//            return new ResponseEntity<>(userMapper.mapUserEntitytoUserDTO(fetchedUser.get()), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }


    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
        Optional<User> fetchedUser = userRepo.findById(userId);
        if (fetchedUser.isPresent()) {
            return new ResponseEntity<>(userMapper.mapUserEntitytoUserDTO(fetchedUser.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

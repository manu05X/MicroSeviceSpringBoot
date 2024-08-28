package com.manish.userinfo.mapper;

import com.manish.userinfo.dto.UserDTO;
import com.manish.userinfo.entity.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper1 {
    UserMapper1 INSTANCE = Mappers.getMapper(UserMapper1.class);

    User mapUserDTOToUser(UserDTO userDTO);
    UserDTO mapUserToUserDTO(User user);
}

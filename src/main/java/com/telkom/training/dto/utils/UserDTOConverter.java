package com.telkom.training.dto.utils;

import com.telkom.training.dto.UserDTO;
import com.telkom.training.model.UserModel;

public class UserDTOConverter {
    private UserDTO convertUserToDTO(UserModel userModel){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userModel.getUsername());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setRoles(userModel.getRoles());
        userDTO.setCreate_at(userModel.getCreate_at());
        return userDTO;
    }
}

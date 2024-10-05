package lk.ijse.appspringboot.service;

import lk.ijse.appspringboot.dto.UserStatus;
import lk.ijse.appspringboot.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);
}

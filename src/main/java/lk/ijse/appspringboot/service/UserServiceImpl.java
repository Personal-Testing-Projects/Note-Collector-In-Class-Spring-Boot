package lk.ijse.appspringboot.service;

import jakarta.transaction.Transactional;
import lk.ijse.appspringboot.Exception.DataPersistException;
import lk.ijse.appspringboot.Exception.UserNotFoundException;
import lk.ijse.appspringboot.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.appspringboot.dao.UserDAO;
import lk.ijse.appspringboot.dto.UserStatus;
import lk.ijse.appspringboot.dto.impl.UserDTO;
import lk.ijse.appspringboot.entity.impl.UserEntity;
import lk.ijse.appspringboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity userEntity = mapping.toUserEntity(userDTO);
        userEntity.setUserId(userDTO.getUserId());
        UserEntity saveUser = userDAO.save(userEntity);
        if(saveUser == null) {
            throw new DataPersistException("User Not Saved");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDAO.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(String userId) {
        if(userDAO.existsById(userId)){
            UserEntity selectedUser = userDAO.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2, "User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existedUser = userDAO.findById(userId);
        if(!existedUser.isPresent()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }else {
            userDAO.deleteById(userId);
        }
    }
    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional <UserEntity> tempUser = userDAO.findById(userId);
        if(tempUser.isPresent()) {
            tempUser.get().setFirstName(userDTO.getFirstName());
            tempUser.get().setLastName(userDTO.getLastName());
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
            tempUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }
}

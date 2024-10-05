package lk.ijse.appspringboot.controller;

import lk.ijse.appspringboot.Exception.DataPersistException;
import lk.ijse.appspringboot.Exception.UserNotFoundException;
import lk.ijse.appspringboot.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.appspringboot.dto.UserStatus;
import lk.ijse.appspringboot.dto.impl.UserDTO;
import lk.ijse.appspringboot.service.UserService;
import lk.ijse.appspringboot.util.AppUtil;
import lk.ijse.appspringboot.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json")
    public ResponseEntity<Void> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart ("lastName") String lastName,
            @RequestPart ("email") String email,
            @RequestPart ("password") String password,
            //@RequestPart ("profilePic") String profilePic
            @RequestPart ("profilePic") MultipartFile profilePic
    ) {
        System.out.println("Raw pro pic" + profilePic);

        //profilepic -> Base64
        //String base64ProPic = AppUtil.profilrPicToBase64(profilePic);
        String base64ProPic = "";
        try {
            byte [] bytesProPic = profilePic.getBytes();
            base64ProPic = AppUtil.profilrPicToBase64(bytesProPic);

            //UserId generate
            String userId = AppUtil.generateUserId();

            //Build the project
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(userId);
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setProfilePic(base64ProPic);

            userService.saveUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") String userId) {
        if(!RegexProcess.userIdMatcher(userId)){
            return new SelectedUserAndNoteErrorStatus(1,"User ID is not valid");
        }
        return userService.getUser(userId);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId){
        try {
            if(!RegexProcess.userIdMatcher(userId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUser(@RequestPart("firstName") String firstName,
                           @RequestPart ("lastName") String lastName,
                           @RequestPart ("email") String email,
                           @RequestPart ("password") String password,
                           @RequestPart ("profilePic") MultipartFile profilePic,
                           @PathVariable("userId") String userId) {

        System.out.println("Raw pro pic" + profilePic);

        //profilepic -> Base64
        //String base64ProPic = AppUtil.profilrPicToBase64(profilePic);
        String base64ProPic = "";
        try {
            byte [] bytesProPic = profilePic.getBytes();
            base64ProPic = AppUtil.profilrPicToBase64(bytesProPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Build the project
        UserDTO buildUserDTO = new UserDTO();
        buildUserDTO.setUserId(userId);
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProPic);

        userService.updateUser(userId, buildUserDTO);
    }
}

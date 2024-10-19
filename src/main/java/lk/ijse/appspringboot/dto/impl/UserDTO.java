package lk.ijse.appspringboot.dto.impl;

import lk.ijse.appspringboot.dto.UserStatus;
import lk.ijse.appspringboot.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private Role role;
    private List<NoteDTO> notes;
}

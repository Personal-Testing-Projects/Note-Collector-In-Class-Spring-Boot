package lk.ijse.appspringboot.entity.impl;

import jakarta.persistence.*;

import lk.ijse.appspringboot.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;

    @OneToMany(mappedBy = "userEntity")
    private List<NoteEntity> notes;
}

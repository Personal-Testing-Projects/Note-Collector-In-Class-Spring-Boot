package lk.ijse.appspringboot.entity.impl;

import jakarta.persistence.*;

import lk.ijse.appspringboot.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "note")
public class NoteEntity implements SuperEntity {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createdDate;
    private String priorityLevel;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity userEntity;
}

package lk.ijse.appspringboot.customStatusCodes;

import lk.ijse.appspringboot.dto.NoteStatus;
import lk.ijse.appspringboot.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedUserAndNoteErrorStatus implements UserStatus, NoteStatus {
    private int statusCode;
    private String statusMessage;
}

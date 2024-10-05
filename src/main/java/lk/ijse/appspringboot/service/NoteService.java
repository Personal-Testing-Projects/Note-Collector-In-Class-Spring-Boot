package lk.ijse.appspringboot.service;

import lk.ijse.appspringboot.dto.NoteStatus;
import lk.ijse.appspringboot.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDTO noteDTO);
    List<NoteDTO> getAllNotes();
    NoteStatus getSelectedNote(String id);
    void deleteNote(String id);
    void updateNote(String noteId, NoteDTO noteDTO);
}

package lk.ijse.appspringboot.controller;

import lk.ijse.appspringboot.Exception.DataPersistException;
import lk.ijse.appspringboot.Exception.NoteNotFoundException;
import lk.ijse.appspringboot.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.appspringboot.dto.NoteStatus;
import lk.ijse.appspringboot.dto.impl.NoteDTO;
import lk.ijse.appspringboot.service.NoteService;
import lk.ijse.appspringboot.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteStatus getSelectedNotes(@PathVariable ("noteId") String noteId) {
        if (!RegexProcess.noteIdMatcher(noteId)) {
            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
        }
        return noteService.getSelectedNote(noteId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNote(@RequestBody NoteDTO noteDTO) {
        System.out.println("Note save");
        System.out.println(noteDTO);
        try {
            noteService.saveNote(noteDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = "application/json")
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable ("noteId") String noteId){
        try {
            if (!RegexProcess.noteIdMatcher(noteId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{noteId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("noteId") String noteId,
                                           @RequestBody NoteDTO updatedNoteDTO) {
        //validations
        try {
            if (!RegexProcess.noteIdMatcher(noteId) || updatedNoteDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.updateNote(noteId, updatedNoteDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

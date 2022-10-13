package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.NotesDTO;
import com.BridgeLabz.FundooApp.Exception.ExceptionMessage;
import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.NoteRepository;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import com.BridgeLabz.FundooApp.Utility.Response;
import com.BridgeLabz.FundooApp.Utility.Utility;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements INotesService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    //To Add A note
    @Override
    @SneakyThrows
    public Response AddNote(NotesDTO notesDTO, int id) {
        Notes notes = modelMapper.map(notesDTO, Notes.class);
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            List<Notes> note = user.getNotes();
            note.add(notes);
            user.setNotes(note);
            notes.setStatus("Active");
            noteRepository.save(notes);
            userRepository.save(user);
        } else {
            throw new ExceptionMessage("Email not found");
        }
        return Utility.getResponse("Note added", HttpStatus.OK);
    }

    //To update A note
    public Response updateNote(int id, int note_id, Notes noteBody) {
        if (userRepository.findById(id).isPresent()) {
            if (noteRepository.findById(note_id).isPresent()) {
                Notes note = noteRepository.findById(note_id).get();
                note.setTitle(noteBody.getTitle());
                note.setDescription(noteBody.getDescription());
                noteRepository.save(note);
            } else {
                throw new ExceptionMessage("Note ID not found");
            }
        } else {
            throw new ExceptionMessage("Email not found");
        }
        return Utility.getResponse("note has been updated", HttpStatus.OK);
    }

    //To delete A note
    @Override
    public Response deleteByID(int user_id, int note_id) {
        if (userRepository.findById(user_id).isPresent()) {
            if (noteRepository.findById(note_id).isPresent()) {
                noteRepository.deleteById(note_id);
            } else {
                throw new ExceptionMessage("Note ID not found");
            }
        } else {
            throw new ExceptionMessage("Email not found");
        }
        return Utility.getResponse("note has been deleted", HttpStatus.OK);
    }


    //To get A note specific to a user_id
    public Response getSpecficNotesById(int user_id) {
        List<Notes> userNotes;
        if(userRepository.findById(user_id).isPresent())
        {

            userNotes =userRepository.findById(user_id).get().getNotes();
        }
        else
        {
            throw new ExceptionMessage("Invalid User Id");
        }
        return new Response("User Id : "+ user_id , userNotes);
    }

    //To Archive/UnArchive A note
    public Response archive(int note_id) {
        if (noteRepository.existsById(note_id)) {
            Notes note = noteRepository.findById(note_id).get();
            if (!note.getStatus().equals("Archived")) {
                note.setStatus("Archived");
                noteRepository.save(note);
                return Utility.getResponse("note has  been Archived", HttpStatus.OK);
            } else {
                note.setStatus("Active");
                noteRepository.save(note);
                return Utility.getResponse("note has UnArchived", HttpStatus.OK);
            }
        } else {
            throw new ExceptionMessage("Note id not found");
        }
    }

    //To Trash A note
    public Response trash(int note_id) {
        if (noteRepository.existsById(note_id)) {
            Notes note = noteRepository.findById(note_id).get();
            if (!note.getStatus().equals("Trashed")) {
                note.setStatus("Trashed");
                noteRepository.save(note);
                return Utility.getResponse("note has  been Trashed", HttpStatus.OK);
            } else {
                note.setStatus("Active");
                noteRepository.save(note);
                return Utility.getResponse("note has been removed from Trashed", HttpStatus.OK);
            }
        } else {
            throw new ExceptionMessage("Note id not found");
        }
    }

    //To Pin/UnPin A note
    public Response pin(int note_id) {
        if (noteRepository.existsById(note_id)) {
            Notes note = noteRepository.findById(note_id).get();
            if (!note.getStatus().equals("Pinned")) {
                note.setStatus("Pinned");
                noteRepository.save(note);
                return Utility.getResponse("note has  been Pinned", HttpStatus.OK);
            } else {
                note.setStatus("Active");
                noteRepository.save(note);
                return Utility.getResponse("note has UnPinned", HttpStatus.OK);
            }
        } else {
            throw new ExceptionMessage("Note id not found");
        }
    }
}

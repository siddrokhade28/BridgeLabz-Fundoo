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
public class NotesServiceImpl implements INotesService{

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @SneakyThrows
    public Response AddNote(NotesDTO notesDTO, int  id) {
        Notes notes = modelMapper.map(notesDTO, Notes.class);
        if(userRepository.findById(id).isPresent()){
            User user= userRepository.findById(id).get();
            List<Notes> note=user.getNotes();
            note.add(notes);
            user.setNotes(note);
            notes.setStatus("Active");
            noteRepository.save(notes);
            userRepository.save(user);
        }
        else {
            throw new ExceptionMessage("Email not found");
        }
        return Utility.getResponse("Note added",HttpStatus.OK);
    }

    public Response updateNote(int id,int note_id,Notes noteBody){
        if(userRepository.findById(id).isPresent()){
            if(noteRepository.findById(note_id).isPresent()){
                Notes note = noteRepository.findById(note_id).get();
                note.setNote(noteBody.getNote());
                noteRepository.save(note);
            }
            else {
                throw new ExceptionMessage("Note ID not found");
            }
        }
        else {
            throw new ExceptionMessage("Email not found");
        }
        return Utility.getResponse("note has been updated",HttpStatus.OK);
    }


    @Override
    public Response deleteByID(int user_id, int note_id) {
        if(userRepository.findById(user_id).isPresent()){
            if(noteRepository.findById(note_id).isPresent()){
                noteRepository.deleteById(note_id);
            }
            else {
                throw new ExceptionMessage("Note ID not found");
            }
        }
        else {
            throw new ExceptionMessage("Email not found");
        }
        return Utility.getResponse("note has been deleted",HttpStatus.OK);
    }


    public Response getSpecficNotesById(int id) {
        if(userRepository.findById(id).isPresent()){
           User user= userRepository.findById(id).get();
          List<Notes> allNote= user.getNotes();
          return Utility.getResponse("Notes of User "+user.getFirstName(),allNote);
        }
        else {
            throw new ExceptionMessage("Email not found");
        }
    }

    public Response archive(int note_id){
        if(noteRepository.existsById(note_id)){
            Notes note= noteRepository.findById(note_id).get();
            note.setStatus("Archived");
            noteRepository.save(note);
            return Utility.getResponse("note has Archived",HttpStatus.OK);
        }
        else {
            throw  new ExceptionMessage("Note id not found");
        }
    }

    public Response unarchive(int note_id){
        if(noteRepository.existsById(note_id)){
            Notes note= noteRepository.findById(note_id).get();
            note.setStatus("active");
            noteRepository.save(note);
            return Utility.getResponse("note has benn trashed",HttpStatus.OK);
        }
        else {
            throw  new ExceptionMessage("Note id not found");
        }

    }
}

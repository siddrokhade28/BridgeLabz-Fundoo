package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.NoteRepository;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    public void add(Notes notes,int id)  {
       User user= userRepository.findById(id).get();
        if(user!=null){
            List<Notes> note=user.getNotes();
            note.add(notes);
            user.setNotes(note);
            userRepository.save(user);
        }
        else {
            throw  new Exception("User ID not found");
        }
    }

//    @SneakyThrows
//    public List getSpecficNotesById(int id) {
//        User user=userRepository.findById(id).get();
//        if(user.getId()==id){
//
//            return noteRepository.getAllNotesById(id);
//        }
//        else {
//            throw new Exception("User ID not found");
//        }
//    }


    public void deleteByID(int id) {
        noteRepository.deleteById(id);
    }

//    public void updateNotes(int note_id, int user_id, Notes notes) {
//
//    }
}

package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.NoteRepository;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import com.BridgeLabz.FundooApp.Utility.Response;
import com.BridgeLabz.FundooApp.Utility.Utility;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService implements INotesService{

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @SneakyThrows
    public Response AddNote(List<Notes> notes,int user_id) {
        if(userRepository.findById(user_id).isPresent()){
            userRepository.findById(user_id).get().setNotes(notes);
            notes.addAll(notes);
            noteRepository.saveAll(notes);
        }
        else {
            throw new Exception("User Id not found");
        }
        return Utility.getResponse("Note added to the user"+user_id, HttpStatus.OK);
    }

    @Override
    public void deleteByID(int user_id, int note_id) {

    }


//    @SneakyThrows
//    public Response AddNote(Notes notes, int id)  {
//       User user= userRepository.findById(id).get();
//        if(user!=null){
//            List<Notes> note=user.getNotes();
//            note.add(notes);
//            user.setNotes(note);
//            userRepository.save(user);
//        }
//        else {
//            throw  new Exception("User ID not found");
//        }
//    }
//
////    @SneakyThrows
////    public List getSpecficNotesById(int id) {
////        User user=userRepository.findById(id).get();
////        if(user.getId()==id){
////
////            return noteRepository.getAllNotesById(id);
////        }
////        else {
////            throw new Exception("User ID not found");
////        }
////    }
//
//
//    public void deleteByID(int id) {
//        noteRepository.deleteById(id);
//    }
//
////    public void updateNotes(int note_id, int user_id, Notes notes) {
////
////    }
}

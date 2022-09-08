package com.BridgeLabz.FundooApp.Controller;

import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    NotesService notesService;

    //API to Add note to particular user
    @PostMapping("/add/{user_id}")
    public String addNotes(@RequestBody List<Notes> notes, @PathVariable int user_id){
        notesService.AddNote(notes,user_id);
        return "new notes added";

    }

    @DeleteMapping("/delete/")
    public String deleteNoteById(@PathVariable int user_id,@PathVariable int note_id){
        notesService.deleteByID(user_id,note_id);
        return "Note deleted";
    }
}

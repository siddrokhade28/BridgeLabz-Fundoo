package com.BridgeLabz.FundooApp.Controller;

import com.BridgeLabz.FundooApp.DTO.NotesDTO;
import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Service.NotesServiceImpl;
import com.BridgeLabz.FundooApp.Utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NotesController {

    @Autowired
    NotesServiceImpl notesServiceImpl;

    //API to Add note to particular user
    @PostMapping("/add/{user_id}")
    public Response addNotes(@RequestBody NotesDTO notes, @PathVariable int user_id) {
        return notesServiceImpl.AddNote(notes, user_id);
    }



    //APi to update a Present Note
    @PutMapping("/update/{user_id}/{note_id}")
    public Response updateNote(@PathVariable int user_id, @PathVariable int note_id, @RequestBody Notes notes) {
        return notesServiceImpl.updateNote(user_id, note_id, notes);
    }

    //API to delete a note
    @DeleteMapping("/delete/{user_id}/{note_id}")
    public Response deleteNoteById(@PathVariable int user_id, @PathVariable int note_id) {
        return notesServiceImpl.deleteByID(user_id, note_id);
    }

    //API to Fetch all note to a user_id
    @GetMapping("getNotes/{user_id}")
    public Response getNoteByEmail(@PathVariable int user_id) {
        return notesServiceImpl.getSpecficNotesById(user_id);
    }

    //API to Archive a note
    @GetMapping("/archive/{note_id}")
    public Response archiveNote(@PathVariable int note_id) {
        return notesServiceImpl.archive(note_id);
    }

    //API to Trash a note
    @GetMapping("/trash/{note_id}")
    public Response trashNote(@PathVariable int note_id) {
        return notesServiceImpl.trash(note_id);
    }

    //API to pin a note
    @GetMapping("/pin/{note_id}")
    public Response pinNote(@PathVariable int note_id) {
        return notesServiceImpl.pin(note_id);
    }


}

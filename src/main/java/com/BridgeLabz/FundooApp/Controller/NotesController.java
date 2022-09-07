//package com.BridgeLabz.FundooApp.Controller;
//
//import com.BridgeLabz.FundooApp.Model.Notes;
//import com.BridgeLabz.FundooApp.Service.NotesService;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.persistence.GeneratedValue;
//import java.util.List;
//
//@RestController
//@RequestMapping("/notes")
//public class NotesController {
//
//    @Autowired
//    NotesService notesService;
//
//
//    @PostMapping("/add")
//    public String addNotes(@RequestBody Notes notes, @RequestParam int id){
//        notesService.add(notes,id);
//        return "new notes added";
//
//    }
//
////    @GetMapping("/list")
////    public List getSpecficNotes(@RequestParam int id){
////       return notesService.getSpecficNotesById(id);
////    }
//
////    @PutMapping("/update")
////    public String updateNotes(@RequestBody Notes notes,@RequestParam int user_id,@RequestParam int note_id){
////        notesService.updateNotes(note_id,user_id,notes);
////        return "notes updated";
////    }
//
//    @DeleteMapping("/delete")
//    public String deleteNote(@RequestParam int id){
//        notesService.deleteByID(id);
//        return "Note deleted";
//    }
//}

package com.BridgeLabz.FundooApp.Service;


import com.BridgeLabz.FundooApp.DTO.NotesDTO;
import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Utility.Response;

import java.util.List;

public interface INotesService {
    public Response AddNote(NotesDTO notesDTO, int  id);

    public Response updateNote(int  id,int note_id,Notes noteBody);

    public Response deleteByID(int id, int note_id);

    public Response getSpecficNotesById(int id);

    public Response archive(int note_id);

    public Response unarchive(int note_id);
}

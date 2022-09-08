package com.BridgeLabz.FundooApp.Service;


import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Utility.Response;

import java.util.List;

public interface INotesService {
    public Response AddNote(List<Notes> notes, int user_id);

    void deleteByID(int user_id, int note_id);
}

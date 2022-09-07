package com.BridgeLabz.FundooApp.Repository;

import com.BridgeLabz.FundooApp.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends JpaRepository<Notes, Integer> {


}

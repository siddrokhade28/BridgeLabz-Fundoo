package com.BridgeLabz.FundooApp.Repository;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Notes,Integer> {

//    @Query(value = "select n.notes from notes n where userid_fk:id",nativeQuery = true)
//    List getAllNotesById(@Param("id") int id);



}

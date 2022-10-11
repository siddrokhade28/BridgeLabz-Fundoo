package com.BridgeLabz.FundooApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Note_ID")
    private int note_id;
    @Column(name = "title", length = 800)
    private String title;
    @Column(name = "Description" ,length = 8000 )
    private String description;
    @Column(name = "Status")
    private String status;

}

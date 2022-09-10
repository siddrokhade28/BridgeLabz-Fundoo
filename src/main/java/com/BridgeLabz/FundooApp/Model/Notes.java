package com.BridgeLabz.FundooApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "Notes", length = 8000)
    private String note;
    @Column(name = "Status")
    private String status;

}

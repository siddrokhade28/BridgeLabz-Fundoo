package com.BridgeLabz.FundooApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDTO {
    private String title;
    private String description;
}

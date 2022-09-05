package com.BridgeLabz.FundooApp.Utility;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
Model Class to maintain response from the Api
 */
@Data
@AllArgsConstructor
public class Response {

    private String message;

    private Object data;
}

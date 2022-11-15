package com.finalproj.devapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class SendMessageObject {

    private String title;
    private String content;
    private String param;
    private boolean pageState;


}

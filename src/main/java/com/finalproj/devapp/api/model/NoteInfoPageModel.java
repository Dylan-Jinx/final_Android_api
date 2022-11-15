package com.finalproj.devapp.api.model;

import lombok.Data;

@Data
public class NoteInfoPageModel {
    private Integer id;
    private Integer mid;
    private String content;
    private String bannerPic;
    private String face;
    private String username;
    private String sign;
}

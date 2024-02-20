package com.app.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private String title;
    private String content;
    private String author;
    private String image;
    private String category;
}

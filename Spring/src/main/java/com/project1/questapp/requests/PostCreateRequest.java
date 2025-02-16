package com.project1.questapp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
    private Long id;
    private String text;
    private String title;
    private Long userId;
}

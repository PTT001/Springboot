package com.ray.raydemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDto {
    private Integer id;
    private String username;
    private String avatarUrl;
}

package com.ray.raydemo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "characters")
@Data
public class Characters {
    @Id
    private Integer id;
    private String roleName;
    private Byte gender;
    private String task;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skills; // 技能列表，與 skills 表關聯
}

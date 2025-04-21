package com.ray.raydemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "character_id") // 對應 skills 表中的 character_id 外鍵
    private Characters character;
    private String skillName;
    private String skillDescription;
}

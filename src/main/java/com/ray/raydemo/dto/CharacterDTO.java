package com.ray.raydemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ray.raydemo.util.SkillsDTOSerializer;
import lombok.Data;

import java.util.List;

@Data
public class CharacterDTO {
    private Integer id;
    private String roleName;
    private Byte gender;
    private String task;

    @JsonSerialize(using = SkillsDTOSerializer.class)
    private List<SkillDTO> skills;
}

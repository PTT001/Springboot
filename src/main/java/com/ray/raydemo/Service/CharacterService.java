package com.ray.raydemo.Service;

import com.ray.raydemo.dto.CharacterDTO;
import com.ray.raydemo.dto.SkillDTO;
import com.ray.raydemo.model.Characters;
import com.ray.raydemo.model.Skill;
import com.ray.raydemo.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CharacterService {

    private CharacterRepository characterRepository;

    public List<CharacterDTO> getAllCharacters() {
        List<Characters> characters = characterRepository.findAll();
        return characters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CharacterDTO convertToDTO(Characters character) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(character.getId());
        dto.setRoleName(character.getRoleName());
        dto.setGender(character.getGender());
        dto.setTask(character.getTask());
        dto.setSkills(character.getSkills().stream().map(this::convertToSkillDTO).collect(Collectors.toList()));
        return dto;
    }

    private SkillDTO convertToSkillDTO(Skill skill) {
        SkillDTO dto = new SkillDTO();
        dto.setSkillName(skill.getSkillName());
        dto.setSkillDesc(skill.getSkillDescription());
        return dto;
    }
}

package com.ray.raydemo.Service;

import com.ray.raydemo.dto.CharacterDTO;
import com.ray.raydemo.dto.GamerDto;
import com.ray.raydemo.model.Characters;
import com.ray.raydemo.model.Gamers;
import com.ray.raydemo.repository.GamerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GamerService {

    private GamerRepository gamerRepository;

    public List<GamerDto> getAllGamers() {
        List<Gamers> gamers = gamerRepository.findAll();
        return gamers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Gamers SignInGamer(Gamers gamer) {
        return gamerRepository.save(gamer);
    }

    private GamerDto convertToDTO(Gamers gamers) {
        GamerDto dto = new GamerDto();
        dto.setRoleName(gamers.getRole());
        dto.setUserName(gamers.getUsername());
        return dto;
    }
}








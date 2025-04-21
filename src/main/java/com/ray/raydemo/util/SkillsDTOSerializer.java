package com.ray.raydemo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ray.raydemo.dto.SkillDTO;

import java.io.IOException;
import java.util.List;

public class SkillsDTOSerializer extends JsonSerializer<List<SkillDTO>> {
    @Override
    public void serialize(List<SkillDTO> skills, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject(); // 開始寫入外層物件（這一層保留，因為 "skills" 本身需要是物件）

        for (SkillDTO skill : skills) {
            gen.writeStringField(skill.getSkillName(), skill.getSkillDesc()); // skillName 作為鍵，skillDesc 作為值
        }

        gen.writeEndObject(); // 結束外層物件
    }
}

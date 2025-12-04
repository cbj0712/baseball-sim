package com.lsg.baseball.player.domain;

import com.lsg.baseball.player.domain.enums.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class SubPositionsConverter implements AttributeConverter<List<Position>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Position> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }

        return attribute.stream()
                .map(Position::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Position> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .map(Position::valueOf)
                .collect(Collectors.toList());
    }
}

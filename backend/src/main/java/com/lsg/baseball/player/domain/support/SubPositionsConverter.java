package com.lsg.baseball.player.domain.support;

import com.lsg.baseball.player.domain.enums.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter(autoApply = false)
public class SubPositionsConverter implements AttributeConverter<List<Position>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Position> position) {
        if (position == null || position.isEmpty()) {
            return null;
        }

        return position.stream()
                .map(Position::getCode)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Position> convertToEntityAttribute(String dbData) {
        if  (dbData == null || dbData.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(Position::safeFromCode)
                .flatMap(Optional::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}

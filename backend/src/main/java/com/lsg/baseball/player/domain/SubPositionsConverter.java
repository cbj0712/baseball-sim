package com.lsg.baseball.player.domain;

import com.lsg.baseball.player.domain.enums.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
                .filter(Objects::nonNull)
                .map(Enum::name)
                .distinct()
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Position> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .filter(token -> !token.isEmpty())
                .map(this::toPositionSafe)
                .filter(Objects::nonNull)
                .toList();
    }

    private Position toPositionSafe(String value) {
        try {
            return Position.valueOf(value);
        } catch (IllegalArgumentException e) {
            Logger log = LoggerFactory.getLogger(SubPositionsConverter.class);
            log.warn("Unknown position in sub_positions: {}", value);

            return null;
        }
    }
}

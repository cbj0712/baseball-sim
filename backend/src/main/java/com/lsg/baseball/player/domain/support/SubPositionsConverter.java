package com.lsg.baseball.player.domain.support;

import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.exception.BusinessException;
import com.lsg.baseball.player.domain.enums.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = false)
public class SubPositionsConverter implements AttributeConverter<List<Position>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Position> positions) {
        if (positions == null || positions.isEmpty()) {
            return null;
        }

        return positions.stream()
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
                .map(code -> Position.safeFromCode(code)
                        .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_POSITION_CODE))
                )
                .distinct()
                .collect(Collectors.toList());
    }
}

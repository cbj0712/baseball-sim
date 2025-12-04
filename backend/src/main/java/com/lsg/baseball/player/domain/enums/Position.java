package com.lsg.baseball.player.domain.enums;

public enum Position {
    P("P", "투수"),
    C("C", "포수"),
    _1B("1B", "1루수"),
    _2B("2B", "2루수"),
    SS("SS", "유격수"),
    _3B("3B", "3루수"),
    LF("LF", "좌익수"),
    CF("CF", "중견수"),
    RF("RF", "우익수"),
    DH("DH", "지명타자");

    private final String label;
    private final String description;

    Position(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }
}

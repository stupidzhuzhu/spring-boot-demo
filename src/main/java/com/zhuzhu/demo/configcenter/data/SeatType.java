package com.ainemo.callcenter.configcenter.data;

public enum SeatType {

    /**
     * 普通坐席
     */
    NORMAL("normal"),

    /**
     * 云会议室坐席
     */
    CONFERENCE("conference");

    private String value;

    SeatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SeatType getSeatType(String value) {
        for (SeatType type : SeatType.values()) {
            if (type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        return NORMAL;
    }

}

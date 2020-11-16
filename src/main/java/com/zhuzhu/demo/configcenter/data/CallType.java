package com.ainemo.callcenter.configcenter.data;

public enum CallType {

    /**
     * 不支持匿名呼叫
     */
    NOT_ANONYMOUS("not-anonymous"),

    /**
     * 支持匿名呼叫
     */
    ANONYMOUS("anonymous");

    private String value;

    CallType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CallType getCallType(String value) {
        for (CallType type : CallType.values()) {
            if (type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        return NOT_ANONYMOUS;
    }

}

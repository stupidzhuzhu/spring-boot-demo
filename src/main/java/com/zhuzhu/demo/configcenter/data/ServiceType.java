package com.ainemo.callcenter.configcenter.data;

public enum ServiceType {

    /**
     * 售前服务
     */
    PRE_SALE("pre-sale"),

    /**
     * 售后服务
     */
    AFTER_SALE("after-sale"),
    DEFAULT("");

    private String value;

    ServiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ServiceType getServiceType(String value) {
        for (ServiceType type : ServiceType.values()) {
            if (type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        return DEFAULT;
    }
}

package com.ainemo.callcenter.configcenter.data;

public enum RecordType {

    /**
     * 不录制
     */
    NOT_RECORD("not-record"),

    /**
     * 仅音频录制
     */
    ONLY_AUDIO("only-audio"),

    /**
     * 音视频录制
     */
    AUDIO_AND_VIDEO("audio-video");


    private String value;

    RecordType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RecordType getRecordType(String value) {
        for (RecordType type : RecordType.values()) {
            if (type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        return NOT_RECORD;
    }
}

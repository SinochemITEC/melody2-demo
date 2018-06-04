package com.eyeieye.melody.demo.enums;

public enum ResourceType {

    PROVINCE("province", "ʡ��"),
    CITY("city", "����");

    private String name;
    private String desc;

    private ResourceType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}

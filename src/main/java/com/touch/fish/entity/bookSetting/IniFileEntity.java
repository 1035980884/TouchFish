package com.touch.fish.entity.bookSetting;

import org.eclipse.xtend.lib.annotations.Data;


public class IniFileEntity {
    private String section;
    private String key;
    private String value;

    public IniFileEntity() {
    }

    public IniFileEntity(String section, String key, String value) {
        this.section = section;
        this.key = key;
        this.value = value;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

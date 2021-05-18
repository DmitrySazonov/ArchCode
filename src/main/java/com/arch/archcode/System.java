package com.arch.archcode;

import com.arch.archcode.enums.SystemType;

import java.util.Objects;

public abstract class System {
    private String name;
    private String id;
    private SystemType systemType;
    private String description;
    private String confluencrUrl;

    public System() {
    }

    public System get(){
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfluencrUrl() {
        return confluencrUrl;
    }

    public void setConfluencrUrl(String confluencrUrl) {
        this.confluencrUrl = confluencrUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        System system = (System) o;
        return Objects.equals(id, system.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

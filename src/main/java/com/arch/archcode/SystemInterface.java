package com.arch.archcode;

import com.arch.archcode.enums.Technology;

public class SystemInterface {
    private String name;
    private Technology technology;
    private final System owner;

    public SystemInterface(String name, Technology technology, System owner) {
        this.name = name;
        this.technology = technology;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public System getOwner() {
        return owner;
    }

}

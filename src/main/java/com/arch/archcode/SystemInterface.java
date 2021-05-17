package com.arch.archcode;

import com.arch.archcode.enums.Technology;

public class SystemInterface {
    private String name;
    private Technology technology;

    public SystemInterface(String name, Technology technology) {
        this.name = name;
        this.technology = technology;
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
}

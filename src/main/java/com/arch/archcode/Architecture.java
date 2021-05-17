package com.arch.archcode;

import java.util.List;

public class Architecture {
    private String name;
    private List<Flow> flows;

    public Architecture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}

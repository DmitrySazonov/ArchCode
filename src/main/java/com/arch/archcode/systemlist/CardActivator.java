package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.SystemInterface;
import com.arch.archcode.enums.SystemType;
import com.arch.archcode.enums.Technology;

public class CardActivator extends System {

    public CardActivator() {
        this.setName("CardActivator");
        this.setId("1001");
        this.setSystemType(SystemType.MIDDLE);
    }

    public SystemInterface activations() {
        return new SystemInterface("/activations", Technology.REST_HTTP, this);
    }

    public SystemInterface activationsMb() {
        return new SystemInterface("/activationsMb", Technology.REST_HTTP, this);
    }

    public SystemInterface activationsResend() {
        return new SystemInterface("/activations/resend", Technology.REST_HTTP, this);
    }

    public SystemInterface activationsCode() {
        return new SystemInterface("/activations/code", Technology.REST_HTTP, this);
    }
}

package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.SystemInterface;
import com.arch.archcode.enums.SystemType;
import com.arch.archcode.enums.Technology;

public class CardActivator extends System {

    public CardActivator() {
        this.setName("Сервис активации карты для лендинга на сайте");
        this.setId("1001");
        this.setSystemType(SystemType.MIDDLE);
    }

    public SystemInterface activations() {
        return new SystemInterface("/activations", Technology.REST_HTTP);
    }

    public SystemInterface activationsResend() {
        return new SystemInterface("/activations/resend", Technology.REST_HTTP);
    }

    public SystemInterface activationsCode() {
        return new SystemInterface("/activations/code", Technology.REST_HTTP);
    }
}

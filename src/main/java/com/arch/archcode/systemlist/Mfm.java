package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.SystemInterface;
import com.arch.archcode.enums.SystemType;
import com.arch.archcode.enums.Technology;

public class Mfm extends System {
    public Mfm() {
        this.setName("Система отправки уведомлений по смс");
        this.setId("150");
        this.setSystemType(SystemType.BACK);
    }

    public SystemInterface sendMessage() {
        return new SystemInterface("Отправка смс", Technology.MQ);
    }
}

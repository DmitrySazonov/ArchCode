package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.enums.SystemType;

public class Site extends System {

    public Site() {
        this.setName("Сайт");
        this.setId("200");
        this.setSystemType(SystemType.FRONT);
    }
}

package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.enums.SystemType;

public class MobileBank extends System{

    public MobileBank() {
        this.setName("Мобильный банк");
        this.setId("210");
        this.setSystemType(SystemType.FRONT);
    }
}

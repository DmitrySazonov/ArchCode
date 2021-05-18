package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.SystemInterface;
import com.arch.archcode.enums.SystemType;
import com.arch.archcode.enums.Technology;

public class Cif extends System {

    public Cif() {
        this.setName("CIF");
        this.setId("100");
        this.setSystemType(SystemType.BACK);
    }

    public SystemInterface getCustomer() {
        return new SystemInterface("Поиск клиента", Technology.SOAP_WS, this);
    }
}

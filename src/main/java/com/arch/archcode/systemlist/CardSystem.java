package com.arch.archcode.systemlist;

import com.arch.archcode.System;
import com.arch.archcode.SystemInterface;
import com.arch.archcode.enums.SystemType;
import com.arch.archcode.enums.Technology;

public class CardSystem extends System {
    public CardSystem() {
        this.setName("Карточная система");
        this.setId("50");
        this.setSystemType(SystemType.BACK);
    }

    public SystemInterface getCard() {
        return new SystemInterface("Поиск карты", Technology.SOAP_WS, this);
    }

    public SystemInterface setPIN() {
        return new SystemInterface("Активация карты и установка ПИН кода", Technology.SOAP_WS, this);
    }
}

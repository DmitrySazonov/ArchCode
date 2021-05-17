package com.arch.archcode.architectures;

import com.arch.archcode.Architecture;
import com.arch.archcode.Flow;
import com.arch.archcode.systemlist.*;

import java.util.ArrayList;
import java.util.List;

public class CardActivation_v1 {
    public Architecture get() {

        Architecture arch = new Architecture();
        arch.setName("Активация новой карты клиентом через страницу лендинга");

        List<Flow> flows = new ArrayList<>();

        //SITE -> CardActivator
        flows.add(new Flow(
                new Site(),
                new CardActivator().activations(),
                "Поиск по номеру телефона и части pan карты"
        ));

        flows.add(new Flow(
                new Site(),
                new CardActivator().activationsResend(),
                "Повторная отпрака смс"
        ));


        flows.add(new Flow(
                new Site(),
                new CardActivator().activationsCode(),
                "Проверка смс кода и активация карты"
        ));

        //CardActivator -> CIF
        flows.add(new Flow(
                new CardActivator(),
                new Cif().getCustomer()
        ));

        //CardActivator -> CardSystem
        flows.add(new Flow(
                new CardActivator(),
                new CardSystem().getCard()
        ));

        flows.add(new Flow(
                new CardActivator(),
                new CardSystem().setPIN()
        ));

        //CardActivator -> MFM
        flows.add(new Flow(
                new CardActivator(),
                new Mfm().sendMessage()
        ));

        arch.setFlows(flows);

        return arch;
    }
}

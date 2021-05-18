package com.arch.archcode.architectures;

import com.arch.archcode.AbstractArch;
import com.arch.archcode.Architecture;
import com.arch.archcode.Flow;
import com.arch.archcode.systemlist.*;

import java.util.ArrayList;
import java.util.List;

public class MobileCardActivation_v1 extends AbstractArch {
    @Override
    public Architecture get() {

        Architecture arch = new Architecture();
        arch.setName("Активация новой карты клиентом через страницу лендинга");

        List<Flow> flows = new ArrayList<>();

        //MB -> CardActivator
        flows.add(new Flow(
                new MobileBank(),
                new CardActivator().activationsMb(),
                "Создание сессии"
        ));

        flows.add(new Flow(
                new MobileBank(),
                new CardActivator().activationsResend(),
                "Повторная отпрака смс"
        ));


        flows.add(new Flow(
                new MobileBank(),
                new CardActivator().activationsCode(),
                "Проверка смс кода и активация карты"
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

        flows.add(new Flow(
                new CardActivator(),
                new CardSystem().changePIN()
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

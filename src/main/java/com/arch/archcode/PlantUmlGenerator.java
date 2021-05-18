package com.arch.archcode;

import com.arch.archcode.architectures.CardActivation_v1;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlantUmlGenerator {

    public static void main(String[] args) throws IOException {
        Architecture v1 = new CardActivation_v1().get();
        try (FileWriter fileWriter = new FileWriter("./out.puml", StandardCharsets.UTF_8)) {
            fileWriter.append("@startuml\n");
            fileWriter.append("skinparam rectangle {\n" +
                              "    borderColor Transparent\n" +
                              "    shadowing false\n" +
                              "}\n");
            drawUniqueSystemsAndInterfaces(v1, fileWriter);
            fileWriter.append("@enduml\n");
        }
    }

    private static void drawUniqueSystemsAndInterfaces(Architecture v1, FileWriter fileWriter) {

        v1.getFlows()
                .stream()
                .flatMap(f -> Stream.of(f.getConsumer(), f.getProducerInterface().getOwner()))
                .distinct()
                .forEach(system -> {
                    silentAppend(fileWriter, String.format("rectangle {\n    component \"%s\" as %s \n", system.getName(), system.getId()));
                    List<Flow> incomingFlows = v1.getFlows().stream()
                            .filter(f -> f.getProducerInterface().getOwner().equals(system))
                            .collect(Collectors.toList());
                    silentAppend(fileWriter, "rectangle { \n");
                    incomingFlows.forEach(f -> appendInterface(fileWriter, f.getProducerInterface()));
                    silentAppend(fileWriter, "} \n");
                    for (int i = 0; i < incomingFlows.size() - 1; i++) {
                        silentAppend(fileWriter, String.format("%s -[hidden]- %s\n", incomingFlows.get(i).getProducerInterface().hashCode(), incomingFlows.get(i + 1).getProducerInterface().hashCode()));
                    }
                    silentAppend(fileWriter, "}\n");
                });

        v1.getFlows().forEach(flow -> {
            silentAppend(fileWriter, String.format("%s --> %s\n", flow.getConsumer().getId(), flow.getProducerInterface().hashCode()));
            silentAppend(fileWriter, String.format("%s - %s\n", flow.getProducerInterface().hashCode(), flow.getProducerInterface().getOwner().getId()));
        });

    }

    private static void appendInterface(FileWriter fileWriter, SystemInterface anInterface) {
        silentAppend(fileWriter, String.format("interface \"%s %s\" as %s\n", anInterface.getName(), anInterface.getTechnology(), anInterface.hashCode()));
    }

    private static void silentAppend(FileWriter writer, String str) {
        try {
            writer.append(str);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}

package com.arch.archcode.web;

import com.arch.archcode.AbstractArch;
import com.arch.archcode.Architecture;
import com.arch.archcode.Flow;
import com.arch.archcode.SystemInterface;
import net.sourceforge.plantuml.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ArchCodeController {
    @GetMapping("/arch/{name}")
    public ResponseEntity<byte[]> getArch(@PathVariable String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        Class c = Class.forName("com.arch.archcode.architectures."+name);
        AbstractArch archClass = (AbstractArch) c.newInstance();
        Architecture v1 = archClass.get();
        String path = "./"+name+".puml";
        try (FileWriter fileWriter = new FileWriter(path, StandardCharsets.UTF_8)) {
            fileWriter.append("@startuml\n");
            fileWriter.append("skinparam rectangle {\n" +
                    "    borderColor Transparent\n" +
                    "    shadowing false\n" +
                    "}\n");
            drawUniqueSystemsAndInterfaces(v1, fileWriter);
            fileWriter.append("@enduml\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File source = new File(path);
        SourceFileReader reader = new SourceFileReader(source);
        List<GeneratedImage> list = reader.getGeneratedImages();
        File png = list.get(0).getPngFile();
        //return png;
        byte[] fileContent = Files.readAllBytes(png.toPath());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(fileContent);
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

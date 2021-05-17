package com.arch.archcode;

public class Flow {
    private System consumer;
    private SystemInterface producerInterface;
    private String description;

    public Flow() {
    }

    public Flow(System consumer, SystemInterface producerInterface, String description) {
        this.consumer = consumer;
        this.producerInterface = producerInterface;
        this.description = description;
    }

    public Flow(System consumer, SystemInterface producerInterface) {
        this.consumer = consumer;
        this.producerInterface = producerInterface;
    }

    public System getConsumer() {
        return consumer;
    }

    public void setConsumer(System consumer) {
        this.consumer = consumer;
    }

    public SystemInterface getProducerInterface() {
        return producerInterface;
    }

    public void setProducerInterface(SystemInterface producerInterface) {
        this.producerInterface = producerInterface;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

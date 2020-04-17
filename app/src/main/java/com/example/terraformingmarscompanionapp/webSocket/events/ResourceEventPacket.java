package com.example.terraformingmarscompanionapp.webSocket.events;

import java.util.HashMap;

public class ResourceEventPacket {
    private HashMap<String, Integer> resource_changes = new HashMap<>();

    public ResourceEventPacket(HashMap<String, Integer> resource_changes) {
        this.resource_changes = resource_changes;
    }

    public HashMap<String, Integer> getResourceChanges() {
        return resource_changes;
    }
}

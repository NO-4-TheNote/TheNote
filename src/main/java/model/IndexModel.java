package model;

import java.util.UUID;

public class IndexModel {
    private UUID id;
    private String type;
    // 实现getter和setter

    public IndexModel(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public IndexModel() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

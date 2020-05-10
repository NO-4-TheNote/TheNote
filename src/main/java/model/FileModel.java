package model;

import java.util.UUID;

public class FileModel {
    private UUID id;
    private String name;
    private String type;
    private Long createTime;
    private String uri;
    // 实现getter和setter

    public FileModel(UUID id, String name, String type, Long createTime, String uri) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createTime = createTime;
        this.uri = uri;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

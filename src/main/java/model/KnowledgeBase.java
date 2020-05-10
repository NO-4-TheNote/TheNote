package model;

import java.util.List;
import java.util.UUID;

public class KnowledgeBase {
    private UUID id;
    private String name;
    private Long createTime;
    private Long modifiedTime;
    private List<UUID> catalogList;
    // 实现getter和setter


    public KnowledgeBase(UUID id, String name, Long createTime, Long modifiedTime, List<UUID> catalogList) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.catalogList = catalogList;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<UUID> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<UUID> catalogList) {
        this.catalogList = catalogList;
    }
}

package model;

import java.util.List;
import java.util.UUID;

public class CatalogModel {
    private UUID id;
    private String name;
    private Long createTime;
    private Long modifiedTime;
    private List<UUID> indexList;
    // 实现getter和setter


    public CatalogModel() {
    }

    public CatalogModel(UUID id, String name, Long createTime, Long modifiedTime, List<UUID> indexList) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.indexList = indexList;
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

    public List<UUID> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<UUID> indexList) {
        this.indexList = indexList;
    }
}

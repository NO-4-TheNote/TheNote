package model;

import java.util.List;
import java.util.UUID;

public class NoteModel {
    private UUID id;
    private UUID belongTo;
    private String name;
    private Long createTime;
    private Long modifiedTime;
    private Boolean removed;
    private String content;
    private List<UUID> fileList;

    // 实现getter和setter


    public NoteModel(UUID id, UUID belongTo, String name, Long createTime, Long modifiedTime, Boolean removed, String content, List<UUID> fileList) {
        this.id = id;
        this.belongTo = belongTo;
        this.name = name;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.removed = removed;
        this.content = content;
        this.fileList = fileList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(UUID belongTo) {
        this.belongTo = belongTo;
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

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<UUID> getFileList() {
        return fileList;
    }

    public void setFileList(List<UUID> fileList) {
        this.fileList = fileList;
    }
}

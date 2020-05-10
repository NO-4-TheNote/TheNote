package dao;

import java.util.UUID;

public interface NoteDao {
    Boolean insert(UUID noteId, UUID catalogId, String name);
    Boolean remove(UUID noteId);
    Boolean restore(UUID noteId);
    Boolean delete(UUID noteId);
    Boolean updateName(UUID noteId, String name);
    NoteDao queryByName(String name);
    NoteDao queryById(UUID noteId);
    Boolean updateBelongToById(UUID noteId, UUID catalogId);
    String queryContentById(UUID noteId);
    Boolean updateContentById(UUID noteId, String content);
    Boolean insertFileById(UUID noteId, UUID fileId);
    Boolean removeFileById(UUID noteId, UUID fileId);
    Boolean judgeRemoved(UUID noteId);
}

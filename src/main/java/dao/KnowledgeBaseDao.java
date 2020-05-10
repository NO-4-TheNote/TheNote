package dao;

import model.KnowledgeBase;

import java.util.List;
import java.util.UUID;

public interface KnowledgeBaseDao {
    Boolean insertKnowledgeBase (KnowledgeBase knowledgeBase);
    Boolean deleteKnowledgeBase (UUID id);
    Boolean updateKnowledgeBase (UUID id, String name);

    KnowledgeBase findKnowledgeBaseById(UUID id);
    List<KnowledgeBase> queryAllKnowledgeBase();
    Boolean insertCatalog(UUID databaseId, UUID catalogId);
    Boolean removeCatalog(UUID databaseId, UUID catalogId);

}

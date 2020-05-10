package handler;

import model.KnowledgeBase;

import java.util.List;
import java.util.UUID;

public class KnowledgeBaseService {
    private KnowledgeBase knowledgeBase;
    private List<KnowledgeBase> allKnowledgebBase;
    //KnowledgeBase newKnowledgeBase (String name);
    Boolean deleteKnowledgeBase (UUID id){
        return false;
    };
    Boolean updateKnowledgeBase (UUID id, String name){
        return false;
    };
    Boolean newCatalog(UUID KnowledgeBaseId,String catalogName){
        return false;
    };
    Boolean removeCatalog(UUID KnowledgeBaseId, UUID catalogId){
        return false;
    };

}

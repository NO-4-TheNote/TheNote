package handler;

import model.CatalogModel;
import model.IndexModel;

import java.util.ArrayList;
import java.util.UUID;

public class CatalogService {
    ArrayList allCatalog;
    Boolean insert(UUID catalogId, String name){
        return false;
    };
    Boolean delete(UUID catalogId){
        return false;
    };
    Boolean update(UUID catalogId, String name){
        return false;
    };
    Boolean insert(UUID indexId, UUID catalogId){
        return false;
    };
    Boolean remove(UUID indexId, UUID catalogId){
        return false;
    };
    CatalogModel queryCatalogById(UUID id){
        return new CatalogModel();
    };
    IndexModel queryIndexById(UUID id){
        return new IndexModel();
    };

}

package dao;

import model.CatalogModel;

import java.util.UUID;

public interface CatalogDao {
    Boolean insert(UUID catalogId, String name);
    Boolean delete(UUID catalogId);
    Boolean update(UUID catalogId, String name);
    Boolean insert(UUID indexId, UUID catalogId);
    Boolean remove(UUID indexId, UUID catalogId);
    CatalogModel queryById(UUID id);

}

package dao;

import java.util.UUID;

public interface IndexDao {
    UUID insert(String type);
    String queryById(UUID id);
    Boolean delete(UUID id);

}

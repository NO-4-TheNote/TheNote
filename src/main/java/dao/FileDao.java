package dao;

import java.util.UUID;

public interface FileDao {
    UUID insert(String name, String type, String uri);
    Boolean delete(UUID id);

}

package org.note.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.note.util.FileTool.*;

public class CatalogHandler {
    public static Boolean createCatalog(String name) {
        File catalog = new File(getCatalogPath(name));
        return catalog.mkdir();
    }

    public static Boolean deleteCatalog(String name) {
        return delete(getCatalogPath(name));
    }

    public static List<String> getCatalogList() throws IOException {
        File dataDir = new File(getDataPath());
        dataDir.mkdirs();

        if (!dataDir.isDirectory()) {
            throw new IOException("invalid path, it should be dir");
        }

        File[] catalogList = dataDir.listFiles();

        if (catalogList == null) {
            return new ArrayList<>();
        } else {
            ArrayList<String> nameList = new ArrayList<>();
            for (File file : catalogList) {
                if (file.isDirectory()) {
                    nameList.add(file.getName());
                }
            }
            return nameList;
        }
    }

    public static List<String> getNoteList(String catalogName) throws IOException {
        File catalog = new File(getCatalogPath(catalogName));

        if (!catalog.isDirectory()) {
            throw new IOException("invalid path, it should be dir");
        }

        File[] noteList = catalog.listFiles();

        if (noteList == null) {
            return new ArrayList<>();
        } else {
            ArrayList<String> nameList = new ArrayList<>();
            for (File file : noteList) {
                if (file.isFile()) {
                    nameList.add(file.getName());
                }
            }
            return nameList;
        }
    }

    public static Boolean existsCatalog(String catalogName) {
        return new File(getCatalogPath(catalogName)).exists();
    }

    public static Boolean renameCatalog(String nameOld, String nameNew) {
        return new File(getCatalogPath(nameOld)).renameTo(new File(getCatalogPath(nameNew)));
    }
}

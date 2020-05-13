package util;


import java.io.File;
import java.net.URL;
import java.nio.file.Path;

public class FileTool {
    public static URL getFxml(String name) {
        return FileTool.class.getResource("/fxml/" + name);
    }

    public static String getCss(String name) {
        return FileTool.class.getResource("/css/" + name).toExternalForm();
    }

    public static String getWorkPath() {
        return System.getProperty("user.dir");
    }

    public static String getDataPath() {
        return Path.of(getWorkPath(), "data").toString();
    }

    public static String getCatalogPath(String catalog) {
        return Path.of(getDataPath(), catalog).toString();
    }

    public static String getNotePath(String catalog, String note) {
        return Path.of(getCatalogPath(catalog), note).toString();
    }

    public static Boolean delete(String path) {
        File f = new File(path);
        if (!f.isFile()) {
            boolean err;
            String[] list = f.list();
            if (list != null) {
                for (String sub : list) {
                    err = delete(sub);
                    if (err) {
                        return true;
                    }
                }
            }
        }
        return f.delete();
    }
}

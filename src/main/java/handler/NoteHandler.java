package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static util.FileTool.getNotePath;

public class NoteHandler {
    public static Boolean createNote(String catalogName, String noteName) {
        File note = new File(getNotePath(catalogName, noteName));
        try {
            return note.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    // todo: 删除附件
    public static Boolean deleteNote(String catalogName, String noteName) {
        File note = new File(getNotePath(catalogName, noteName));
        return note.delete();
    }

    // todo: 获取附件
    public static String getNoteContent(String catalog, String note) throws IOException {
        File file = new File(getNotePath(catalog, note));
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void copyNote(String fromCatalog, String note, String toCatalog) throws IOException {
        // todo: 复制附件
        Files.copy(Path.of(getNotePath(fromCatalog, note)), Path.of(getNotePath(toCatalog, note)));
    }

    public static Boolean existsNote(String catalog, String note) {
        return new File(getNotePath(catalog, note)).exists();
    }

    public static Boolean renameNote(String catalog, String nameOld, String nameNew) {
        return new File(getNotePath(catalog, nameOld)).renameTo(new File(getNotePath(catalog, nameNew)));
    }

    public static void saveNoteContent(String catalog, String note, String content) throws IOException {
        FileWriter file = new FileWriter(getNotePath(catalog, note));
        file.write(content);
        file.close();
    }
}

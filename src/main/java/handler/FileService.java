package handler;

import model.FileModel;
import model.NoteModel;

import java.util.UUID;

public class FileService {
    FileModel file;
    Boolean deleteFile(UUID id){
        return false;
    };
    NoteModel node;
    Boolean deleteNote(UUID id){
        return false;
    };
    Boolean restoreNote(UUID id){
        return false;
    };
    Boolean updateNoteName(UUID id, String name){
        return false;
    };
    Boolean updateNoteContent(UUID id, String content){
        return false;
    };

}

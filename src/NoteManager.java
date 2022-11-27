import java.util.LinkedList;

public class NoteManager {
    private final LinkedList<Note> noteList = new LinkedList<>();

    Note findNoteById(int id) {
        Note found = null;
        for (Note note : noteList) {
            if (note.getId() == id) {
                found = note;
            }
        }
        return found;
    }

    String[] getAllTitles() {
        if (noteList.isEmpty()) {
            return null;
        }

        String[] titles = new String[Note.numberOfNotes];
        int index = 0;
        for (Note note : noteList) {
            titles[index] = note.getTitle();
            index++;
        }

        return titles;
    }

    void addNote(Note note) {
        noteList.add(note);
    }

    void deleteNote(Note note) {
        noteList.remove(note);
        adjustId();
    }

    void adjustId() {
        int count = 0;
        for (Note note : noteList) {
            note.setId(count);
            count++;
        }
    }
}
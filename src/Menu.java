import javax.swing.*;

public class Menu {
    private JFrame frame;
    private JPanel menuPanel;
    private JList<String> notesList;
    private JButton createNewButton;
    private JButton openButton;
    private JButton deleteButton;
    static NoteManager noteManager;

    public Menu() {
        setFrame();

        if (Note.numberOfNotes != 0) {
            notesList.setListData(noteManager.getAllTitles());
        }

        notesList.addListSelectionListener(e -> {
            openButton.setEnabled(true);
            deleteButton.setEnabled(true);
        });
        createNewButton.addActionListener(e -> {
            new NoteEditor(noteManager);
            frame.dispose();
        });
        openButton.addActionListener(e -> {
            Note open = noteManager.findNoteById(notesList.getSelectedIndex());
            new NoteEditor(open);
            frame.dispose();
        });
        deleteButton.addActionListener(e -> {
            Note delete = noteManager.findNoteById(notesList.getSelectedIndex());
            noteManager.deleteNote(delete);
            new Menu();
            frame.dispose();
        });
    }

    public static void main(String[] args) {
        noteManager = new NoteManager();

        new Menu();
    }

    void setFrame() {
        frame = new JFrame("Menu");
        frame.setContentPane(menuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
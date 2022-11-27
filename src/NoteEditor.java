import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Objects;

public class NoteEditor {
    private JFrame frame;
    private JPanel editorPanel;
    private JTextField titleField;
    private JTextArea contentField;
    private JButton taskButton;
    private JButton cancelButton;

    // For creating new notes
    public NoteEditor(NoteManager noteManager) {
        setFrame();

        enableTaskButton(titleField, contentField);

        taskButton.setText("Create New Note");
        taskButton.addActionListener(e -> {
            Note note = new Note();
            note.setId(Note.numberOfNotes++);
            note.setTitle(titleField.getText());
            note.setContent(contentField.getText());
            noteManager.addNote(note);
            backToMenu();
        });
        cancelButton.addActionListener(e -> backToMenu());
    }

    // For opening notes
    public NoteEditor(Note note) {
        setFrame();

        titleField.setText(note.getTitle());
        contentField.setText(note.getContent());

        enableTaskButton(titleField, contentField);

        taskButton.setText("Save Changes");
        taskButton.addActionListener(e -> {
            note.setTitle(titleField.getText());
            note.setContent(contentField.getText());
            backToMenu();
        });
        cancelButton.addActionListener(e -> backToMenu());
    }

    void setFrame() {
        frame = new JFrame("Note Editor");
        frame.setContentPane(editorPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    void backToMenu() {
        frame.dispose();
        new Menu();
    }

    void enableTaskButton(JTextField titleField, JTextArea contentField) {
        titleField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                taskButton.setEnabled(!Objects.equals(titleField.getText(), ""));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                taskButton.setEnabled(!Objects.equals(titleField.getText(), ""));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                taskButton.setEnabled(!Objects.equals(titleField.getText(), ""));
            }
        });

        contentField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                taskButton.setEnabled(!Objects.equals(titleField.getText(), ""));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                taskButton.setEnabled(!Objects.equals(titleField.getText(), ""));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                taskButton.setEnabled(!Objects.equals(titleField.getText(), ""));
            }
        });
    }
}
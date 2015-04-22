import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Actions {
    protected Action createAction(SimpleNote simpleNote, Act act){
        ImageIcon newIcon = new ImageIcon(SimpleNote.class.getResource
                ("NewFileIcon.png"));
        ImageIcon saveIcon = new ImageIcon(SimpleNote.class.getResource
                ("SaveIcon.png"));
        ImageIcon openIcon = new ImageIcon(SimpleNote.class.getResource
                ("OpenIcon.png"));
        ImageIcon boldIcon = new ImageIcon(SimpleNote.class.getResource
                ("BoldIcon.png"));
        ImageIcon italicsIcon = new ImageIcon(SimpleNote.class.getResource
                ("ItalicIcon.png"));
        ImageIcon printIcon = new ImageIcon(SimpleNote.class.getResource
                ("PrintIcon.png"));

        Action action = null;
        switch(act){

            case NEW:
                action = simpleNote.new NewAction("New",newIcon,"Create new file",
                        KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
                break;
            case SAVE :
                action = simpleNote.new SaveAction("Save",saveIcon,"Save file in existing file",
                        KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
                break;
            case OPEN:
                action = simpleNote.new OpenAction("Open",openIcon,"Open file",
                        KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
                break;
            case SAVEAS:
                action = simpleNote.new SaveAsAction("Save as",saveIcon,
                        "Save in selected file",KeyStroke.getKeyStroke
                        (KeyEvent.VK_A,InputEvent.CTRL_MASK));
                break;
            case BOLD:
                action = simpleNote.new BoldAction("Bold",boldIcon,"Bold font",
                        KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
                break;
            case ITALICS:
                action = simpleNote.new ItalicsAction("Italics",italicsIcon,"Italic font",
                        KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_MASK));
                break;
            case PRINT:
                action = simpleNote.new PrintAction("Print",printIcon,"Print the file",
                        KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.CTRL_MASK));
                break;
            case COPY:
                action = simpleNote.new CopyAction("Copy","Copy the selected text",
                        KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
                break;
            case CUT:
                action = simpleNote.new CutAction("Cut","Copy the selected text",
                        KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
                break;
            case PASTE:
                action = simpleNote.new PasteAction("Paste","Copy the selected text",
                        KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
                break;
            case FIND:
                action = simpleNote.new FindAction ("Find and replace",
                        "Search for and replace input word",
                        KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
                break;
        }
        return action;
    }
}

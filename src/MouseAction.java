import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAction extends MouseAdapter {
    public void mouseReleased(MouseEvent e){
        getPopupMenu(e);
    }
    private void getPopupMenu(MouseEvent e){
        Actions act = new Actions();
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copy = new JMenuItem(act.createAction(SimpleNote.getSimpleNote(), Act.COPY));
        popupMenu.add(copy);
        JMenuItem cut = new JMenuItem(act.createAction(SimpleNote.getSimpleNote(), Act.CUT));
        popupMenu.add(cut);
        JMenuItem paste = new JMenuItem(act.createAction(SimpleNote.getSimpleNote(), Act.PASTE));
        popupMenu.add(paste);
        JMenuItem find = new JMenuItem(act.createAction(SimpleNote.getSimpleNote(), Act.FIND));
        popupMenu.add(find);
        JMenuItem fonts = new JMenuItem("Font");
        fonts.addActionListener(SimpleNote.getSimpleNote().new OptionsListener());
        fonts.setActionCommand("fonts");
        JMenuItem format = new JMenuItem("Format");
        format.addActionListener(SimpleNote.getSimpleNote().new OptionsListener());
        format.setActionCommand("format");
        JMenuItem margin = new JMenuItem("Margin");
        margin.addActionListener(SimpleNote.getSimpleNote().new OptionsListener());
        margin.setActionCommand("margin");
        popupMenu.add(format);
        popupMenu.add(margin);
        popupMenu.add(fonts);

        if(e.isPopupTrigger()){
            popupMenu.show(e.getComponent(),
                    e.getX(),e.getY());
        }
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInterface {
    protected JMenuBar menuBar;

    public JMenuBar getMenu(SimpleNote simpleNote, Actions act){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu options = new JMenu("Options");
        JMenu help = new JMenu("Help");

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(options);
        menuBar.add(help);

        JMenuItem newFile = new JMenuItem(act.createAction(simpleNote, Act.NEW));
        newFile.setIcon(null);
        JMenuItem openFile = new JMenuItem(act.createAction(simpleNote, Act.OPEN));
        openFile.setIcon(null);
        JMenuItem saveFile = new JMenuItem(act.createAction(simpleNote, Act.SAVE));
        saveFile.setIcon(null);
        JMenuItem saveAsFile = new JMenuItem(act.createAction(simpleNote, Act.SAVEAS));
        saveAsFile.setIcon(null);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(simpleNote.new exitListener());
        JMenuItem print = new JMenuItem(act.createAction(simpleNote, Act.PRINT));
        print.setIcon(null);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(saveAsFile);
        file.add(print);
        file.add(exit);


        JMenuItem findAndReplace = new JMenuItem(act.createAction(simpleNote, Act.FIND));
        JMenuItem copy = new JMenuItem(act.createAction(simpleNote, Act.COPY));
        JMenuItem cut = new JMenuItem(act.createAction(simpleNote, Act.CUT));
        JMenuItem paste = new JMenuItem(act.createAction(simpleNote, Act.PASTE));

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(findAndReplace);


        JMenuItem fonts = new JMenuItem("Font");
        fonts.addActionListener(simpleNote.new OptionsListener());
        fonts.setActionCommand("fonts");
        JMenuItem format = new JMenuItem("Format");
        format.addActionListener(simpleNote.new OptionsListener());
        format.setActionCommand("format");
        JMenuItem margin = new JMenuItem("Margin");
        margin.addActionListener(simpleNote.new OptionsListener());
        margin.setActionCommand("margin");
        options.add(format);
        options.add(margin);
        options.add(fonts);


        JMenuItem viewHelp = new JMenuItem("Help");
        viewHelp.addActionListener(new InfoListener());
        viewHelp.setActionCommand("help");
        JMenuItem info = new JMenuItem("Information");
        info.addActionListener(new InfoListener());
        info.setActionCommand("info");
        help.add(viewHelp);
        help.add(info);


        return menuBar;
    }
    public class InfoListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            Object source = event.getActionCommand();
            if(source.equals("help")){
                JOptionPane.showMessageDialog(menuBar,"Help");
            }else if(source.equals("info")){
                JOptionPane.showMessageDialog(menuBar,"Info");
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;

public class ToolBarInterface {
    JComboBox<String>fontSizeList;
    JComboBox<String>fontTypeList;

    public ToolBarInterface(){
        fontSizeList=null;
        fontTypeList=null;

    }
    public JToolBar getToolBar(SimpleNote simpleNote, Actions act){
        JToolBar toolBar = new JToolBar();
        AbstractButton newTool = new JButton(act.createAction(simpleNote, Act.NEW));
        AbstractButton openTool = new JButton(act.createAction(simpleNote, Act.OPEN));
        AbstractButton saveTool = new JButton(act.createAction(simpleNote, Act.SAVE));
        AbstractButton printTool = new JButton(act.createAction(simpleNote, Act.PRINT));
        AbstractButton bold = new JButton(act.createAction(simpleNote, Act.BOLD));
        AbstractButton italics = new JButton(act.createAction(simpleNote, Act.ITALICS));
        newTool.setHideActionText(true);
        openTool.setHideActionText(true);
        saveTool.setHideActionText(true);
        printTool.setHideActionText(true);
        italics.setHideActionText(true);
        bold.setHideActionText(true);
        toolBar.add(newTool);
        toolBar.add(openTool);
        toolBar.add(saveTool);
        toolBar.add(printTool);
        toolBar.addSeparator();
        toolBar.add(getFontSizeList());
        toolBar.add(getFontsList(simpleNote));
        toolBar.addSeparator();
        toolBar.add(bold);
        toolBar.add(italics);
        toolBar.setFloatable(false);

        return toolBar;
    }
    protected JComboBox<String> getFontsList(SimpleNote simpleNote){
        String fonts[] =
                GraphicsEnvironment.getLocalGraphicsEnvironment().
                        getAvailableFontFamilyNames();
        fontTypeList = new JComboBox<String>(fonts);
        fontTypeList.addActionListener(simpleNote.new ComboBoxListener());
        fontTypeList.setSelectedIndex(6);
        fontTypeList.setPreferredSize(new Dimension(280,28));
        fontTypeList.setMaximumSize(new Dimension(280,28));

        return fontTypeList;
    }
    protected JComboBox<String> getFontSizeList(){
        String[] size = new String[95];
        for(int i = 0; i<95; i++){
            size[i]=String.valueOf(i+6);
        }
        fontSizeList = new JComboBox<String>(size);
        fontSizeList.setSelectedIndex(6);
        fontSizeList.setPreferredSize(new Dimension(49,28));
        fontSizeList.setMaximumSize(new Dimension(49,28));
        fontSizeList.addActionListener(SimpleNote.getSimpleNote().new ComboBoxListener());

        return fontSizeList;
    }
}

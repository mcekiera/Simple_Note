import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;

public class SimpleNote{
    private JFrame frame;
    private JPanel background;

    private TextInterface textInterface;
    private PageDimensions pageDimensions;
    private InputOutput inputOutput;
    private Actions act = new Actions();
    private static SimpleNote simpleNote;
    private ToolBarInterface toolBarInterface;
    private OptionInterface optionInterface;
    private FindAndReplace findAndReplace;


    public static void main(String[] args){
        simpleNote = new SimpleNote();
        simpleNote.go();
    }
    protected static SimpleNote getSimpleNote(){
        return simpleNote;
    }
    private void go(){
        ImageIcon appIcon = new ImageIcon(SimpleNote.class.
                getResource("AppIcon.png"));

        frame = new JFrame();
        background = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER,background);
        frame.setTitle("New Document" + " - SimpleNote v.2");
        frame.setIconImage(appIcon.getImage());
        frame.setLocationByPlatform(true);
        background.setLayout(new BorderLayout());

        pageDimensions = new PageDimensions();
        pageDimensions.createDimensions();

        textInterface = new TextInterface(pageDimensions.A4);
        background.add(BorderLayout.CENTER, textInterface.getTextArea());
        inputOutput = new InputOutput(frame, textInterface.textArea);

        toolBarInterface = new ToolBarInterface();
        background.add(BorderLayout.PAGE_START, toolBarInterface.getToolBar(getSimpleNote(), act));

        optionInterface = new OptionInterface();
        optionInterface.createOptionsDialog(frame, toolBarInterface, textInterface);

        findAndReplace = new FindAndReplace();
        findAndReplace.getFindAndReplace(frame);

        MenuInterface menuSimpleNote = new MenuInterface();
        frame.setJMenuBar(menuSimpleNote.getMenu(getSimpleNote(), act));



        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200,600);
        frame.setVisible(true);

    }


    protected class NewAction extends AbstractAction {
        public NewAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            textInterface.textArea.setText("");
            frame.setTitle("New Document" + " - SimpleNote v.2");
        }
    }
    protected class SaveAsAction extends AbstractAction {
        public SaveAsAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            inputOutput.save(textInterface.textArea, frame);
        }
    }
    protected class OpenAction extends AbstractAction {
        public OpenAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            textInterface.textArea.setText("");
            inputOutput.open(textInterface.textArea, frame);
        }
    }
    protected class SaveAction extends AbstractAction {
        public SaveAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            if(inputOutput.file==null){
                inputOutput.saveAs(textInterface.textArea,frame);
            }else{
                inputOutput.save(textInterface.textArea,frame);
            }
        }
    }
    protected class PrintAction extends AbstractAction {
        public PrintAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            try{
                textInterface.textArea.print(null,null,true,null,null,true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    protected class CopyAction extends AbstractAction {
        public CopyAction(String name, String desc, KeyStroke key){
            super(name);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent e) {
            textInterface.textArea.copy();
        }
    }
    protected class CutAction extends AbstractAction {
        public CutAction(String name, String desc, KeyStroke key){
            super(name);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent e) {
            textInterface.textArea.cut();
        }
    }
    protected class PasteAction extends AbstractAction {
        public PasteAction(String name, String desc, KeyStroke key){
            super(name);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent e) {
            textInterface.textArea.paste();
        }
    }
    protected class FindAction extends AbstractAction {
        public FindAction(String name, String desc, KeyStroke key){
            super(name);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent e) {
            if(textInterface.textArea.getSelectedText()!=null){
                findAndReplace.findField.setText(textInterface.textArea.getSelectedText());
            }
            findAndReplace.findAndReplace.setVisible(true);
            findAndReplace.findAndReplace.setEnabled(true);
        }
    }

    public class ComboBoxListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            Object source = event.getSource();
            if(source== toolBarInterface.fontTypeList){
                textInterface.textArea.setFont(new Font(toolBarInterface.fontTypeList.getSelectedItem().
                        toString(),
                        textInterface.textArea.getFont().getStyle(),
                        textInterface.textArea.getFont().getSize()));


            }else if(source== toolBarInterface.fontSizeList){
                textInterface.textArea.setFont(new Font(textInterface.textArea.getFont().getName(),
                        textInterface.textArea.getFont().getStyle(),Integer.parseInt
                        (toolBarInterface.fontSizeList.getSelectedItem().toString())));

            }
        }
    }
    protected class OptionsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object source = e.getActionCommand();
            optionInterface.optionsDialog.setLocationRelativeTo(frame);
            if(source.equals("format")){
                optionInterface.tabbedPane.setSelectedIndex(0);
            }else if(source.equals("margin")){
                optionInterface.tabbedPane.setSelectedIndex(1);
            }else if(source.equals("fonts")){
                optionInterface.tabbedPane.setSelectedIndex(2);
            }
            optionInterface.sizeComboBox.setSelectedIndex(toolBarInterface.fontSizeList.getSelectedIndex());
            optionInterface.fontComboBox.setSelectedIndex(toolBarInterface.fontTypeList.getSelectedIndex());
            optionInterface.optionsDialog.setVisible(true);
        }
    }

    protected class TabListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            Object source = event.getActionCommand();
            Dimension tempPage;

            if(source.equals("formatList")){
                event.getSource();
                if(optionInterface.formatList.getSelectedItem()=="OTHER"){
                    optionInterface.widthField.setEnabled(true);
                    optionInterface.widthField.setText("");
                    optionInterface.heightField.setEnabled(true);
                    optionInterface.heightField.setText("");
                }else{
                    for(Dimension dimension: pageDimensions.dimensionArrayList){
                        if(pageDimensions.dimensionArrayList.indexOf(dimension)== optionInterface.formatList.
                                getSelectedIndex()){
                            optionInterface.widthField.setText(String.valueOf(dimension.getWidth()));
                            optionInterface.widthField.setEnabled(false);
                            optionInterface.heightField.setText(String.valueOf(dimension.getHeight()));
                            optionInterface.heightField.setEnabled(false);
                            frame.toFront();
                            frame.repaint();
                            break;
                        }
                    }
                }
            }else if(source.equals("cancel")){
                optionInterface.optionsDialog.setVisible(false);
            }else if(source.equals("ok")){
                Double w = Double.parseDouble(optionInterface.widthField.getText());
                Double h = Double.parseDouble(optionInterface.heightField.getText());
                tempPage=new Dimension(w.intValue(),h.intValue());
                if(optionInterface.vertical.isSelected()){
                    textInterface.mainDimension =tempPage;
                }else if(optionInterface.horizontal.isSelected()){
                    tempPage=new Dimension(h.intValue(),w.intValue());
                    textInterface.mainDimension =tempPage;
                }

                toolBarInterface.fontSizeList.setSelectedIndex(optionInterface.sizeComboBox.getSelectedIndex());
                toolBarInterface.fontTypeList.setSelectedIndex(optionInterface.fontComboBox.getSelectedIndex());
                optionInterface.optionsDialog.setVisible(false);

                int topInt =(int)(Double.parseDouble(optionInterface.topMargin.getSelectedItem().
                        toString())* textInterface.mainDimension.height/29);
                int leftInt =(int)(Double.parseDouble(optionInterface.leftMargin.getSelectedItem().
                        toString())* textInterface.mainDimension.width/21);
                int rightInt =(int)(Double.parseDouble(optionInterface.rightMargin.getSelectedItem().
                        toString())* textInterface.mainDimension.width/21);
                textInterface.textArea.setBorder(BorderFactory.createEmptyBorder
                        (topInt,leftInt,0,rightInt));

                optionInterface.optionsDialog.setVisible(false);
                frame.remove(background);
                frame.getContentPane().add(BorderLayout.CENTER,background);
                optionInterface.formatList.setSelectedIndex(optionInterface.formatList.getSelectedIndex());
                frame.toFront();
                frame.revalidate();
            }
        }
    }
    public class exitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    protected class FARListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object source = e.getActionCommand();

            if(source.equals("find")){
                findAndReplace.searchPoint=0;
                findAndReplace.replaceCheck=1;
                textInterface.removeHighlight();
                findAndReplace.searchPoint= textInterface.find(findAndReplace.findField.getText(), findAndReplace.caseCheck, findAndReplace.searchPoint);
                textInterface.textArea.setCaretPosition(findAndReplace.searchPoint);
            }else if(source.equals("findNext")){
                textInterface.removeHighlight();
                findAndReplace.replaceCheck=1;
                findAndReplace.searchPoint= textInterface.find(findAndReplace.findField.getText(), findAndReplace.caseCheck, findAndReplace.searchPoint);
                textInterface.textArea.setCaretPosition(findAndReplace.searchPoint);
            }else if(source.equals("previousFind")){
                textInterface.removeHighlight();
                findAndReplace.searchPoint= textInterface.textArea.getText().lastIndexOf(findAndReplace.findField.getText(),
                        findAndReplace.searchPoint- findAndReplace.findField.getText().length()-1);
                textInterface.find(findAndReplace.findField.getText(), findAndReplace.caseCheck, findAndReplace.searchPoint);
                textInterface.textArea.setCaretPosition(findAndReplace.searchPoint);
            }else if(source.equals("findAll")){
                findAndReplace.searchPoint = 0;
                do{
                    findAndReplace.searchPoint= textInterface.find(findAndReplace.findField.getText(), findAndReplace.caseCheck, findAndReplace.searchPoint);
                }while(findAndReplace.searchPoint< textInterface.textArea.getText().lastIndexOf(findAndReplace.findField.getText()));
            }else if(source.equals("replace")){
                if(findAndReplace.replaceCheck!=0){
                    textInterface.replace(textInterface.textArea, findAndReplace.findField.getText(), findAndReplace.replaceField.getText(), findAndReplace.searchPoint);
                }
                findAndReplace.replaceCheck=0;
                textInterface.textArea.setCaretPosition(findAndReplace.searchPoint);
            }else if(source.equals("replaceAll")){
                findAndReplace.searchPoint=0;
                do{
                    findAndReplace.searchPoint= textInterface.find(findAndReplace.findField.getText(), findAndReplace.caseCheck, findAndReplace.searchPoint);
                    textInterface.replace(textInterface.textArea, findAndReplace.findField.getText(), findAndReplace.replaceField.getText(), findAndReplace.searchPoint);
                }while(textInterface.textArea.getText().contains(findAndReplace.findField.getText()));

            }else if(source== findAndReplace.caseSensitive){
                if(findAndReplace.caseSensitive.isSelected()){
                    findAndReplace.caseCheck = 0;
                }else{
                    findAndReplace.caseCheck = 1;
                }
            }else if(source.equals("okFind")){
                findAndReplace.findAndReplace.setVisible(false);
                findAndReplace.findField.setText("find");
                findAndReplace.replaceField.setText("replace");
            }
        }
    }
    protected class BoldAction extends AbstractAction {
        public BoldAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            int caret = textInterface.textArea.getCaretPosition();
            if(textInterface.textArea.getFont().getStyle()==Font.PLAIN){
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.BOLD));
                textInterface.textArea.setText(textInterface.textArea.getText());
            }else if(textInterface.textArea.getFont().getStyle()==Font.ITALIC){
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.ITALIC+Font.BOLD));
                textInterface.textArea.setText(textInterface.textArea.getText());
            }else if(textInterface.textArea.getFont().getStyle()==Font.ITALIC+Font.BOLD){
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.ITALIC));
            }else{
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.PLAIN));
            }
            textInterface.textArea.setCaretPosition(caret);
        }
    }
    protected class ItalicsAction extends AbstractAction {
        public ItalicsAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name,icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(ACCELERATOR_KEY, key);
        }
        public void actionPerformed(ActionEvent event){
            int caret = textInterface.textArea.getCaretPosition();
            if(textInterface.textArea.getFont().getStyle()==Font.PLAIN){
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.ITALIC));
                textInterface.textArea.setText(textInterface.textArea.getText());
            }else if(textInterface.textArea.getFont().getStyle()==Font.BOLD){
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.ITALIC+Font.BOLD));
                textInterface.textArea.setText(textInterface.textArea.getText());
            }else if(textInterface.textArea.getFont().getStyle()==Font.ITALIC+Font.BOLD){
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.BOLD));
            }else{
                textInterface.textArea.setFont(textInterface.textArea.getFont().deriveFont(Font.PLAIN));
            }
            textInterface.textArea.setCaretPosition(caret);
        }
    }
}


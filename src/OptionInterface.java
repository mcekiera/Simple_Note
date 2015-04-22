import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OptionInterface {
    protected JTabbedPane tabbedPane;
    protected JTextField widthField;
    protected JTextField heightField;
    protected JRadioButton vertical;
    protected JRadioButton horizontal;
    protected JComboBox<String> formatList;
    protected JComboBox<Object> leftMargin;
    protected JComboBox<Object> rightMargin;
    protected JComboBox<Object> topMargin;
    protected JComboBox<String> fontComboBox;
    protected JComboBox<String> sizeComboBox;
    protected JDialog optionsDialog;

    OptionInterface(){
        tabbedPane = null;
        widthField = null;
        heightField = null;
        vertical = null;
        horizontal = null;
        formatList = null;
        leftMargin = null;
        rightMargin = null;
        topMargin = null;
        fontComboBox = null;
        sizeComboBox = null;
        optionsDialog = null;

    }

    protected void createOptionsDialog(JFrame frame, ToolBarInterface toolBarInterface, TextInterface textInterface){
        optionsDialog = new JDialog(frame,"Format");
        tabbedPane = new JTabbedPane();
        optionsDialog.add(tabbedPane);
        tabbedPane.addTab("Format",getFormatTab());
        tabbedPane.addTab("Margin",getMarginTab(textInterface));
        tabbedPane.addTab("Font",getFontTab(toolBarInterface));

        optionsDialog.setSize(200,200);
        optionsDialog.setVisible(false);
    }
    private JPanel getFormatTab(){
        JPanel formatPanel = new JPanel();
        JLabel formatLabel = new JLabel("Page format");
        JLabel widthLabel = new JLabel("Page width");
        JLabel heightLabel = new JLabel("Page height");
        widthField = new JTextField(1);
        heightField = new JTextField(1);
        String[] formatStringList ={"A0","A1","A2","A3","A4","A5"
                ,"A6","A7","LETTER US","LEGAL US","TABLOID US","OTHER"};
        vertical = new JRadioButton("Vertical");
        horizontal = new JRadioButton("Horizontal");
        if(horizontal.isSelected()){
            vertical.setSelected(false);
        }else{
            vertical.setSelected(true);
            horizontal.setSelected(false);
        }
        ButtonGroup areaDimension = new ButtonGroup();
        areaDimension.add(vertical);
        areaDimension.add(horizontal);
        JButton okFormat = new JButton("OK");
        okFormat.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        okFormat.setActionCommand("ok");

        JButton cancelFormat = new JButton("Cancel");
        cancelFormat.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        cancelFormat.setActionCommand("cancel");
        try{
            formatList.setSelectedIndex(formatList.getSelectedIndex());
        }catch(NullPointerException ex){
            formatList = new JComboBox<String>(formatStringList);

        }
        formatList.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        formatList.setActionCommand("formatList");
        formatList.setSelectedIndex(4);
        formatPanel.setLayout(new GridLayout(5,2,2,2));
        formatPanel.add(formatLabel);
        formatPanel.add(formatList);
        formatPanel.add(widthLabel);
        formatPanel.add(widthField);
        formatPanel.add(heightLabel);
        formatPanel.add(heightField);
        formatPanel.add(vertical);
        formatPanel.add(horizontal);
        formatPanel.add(okFormat);
        formatPanel.add(cancelFormat);
        return formatPanel;
    }
    public JPanel getMarginTab(TextInterface text){
        JPanel marginPanel = new JPanel();
        int w =(int)((double)(text.mainDimension.width/72.0*2.54/2));
        ArrayList<Float> wMargin = new ArrayList<Float>();
        ArrayList<Float> hMargin = new ArrayList<Float>();
        int h =(int)((double)(text.mainDimension.height/70.0*2.54));
        for(float d =0; d<=w; d+=0.5){
            wMargin.add(d);
        }
        for(float t =0; t<=h; t+=0.5){
            hMargin.add(t);
        }
        JLabel left = new JLabel("Left margin");
        leftMargin = new JComboBox<Object>(wMargin.toArray());
        leftMargin.setSelectedIndex(2);
        JLabel right = new JLabel("Right margin");
        rightMargin = new JComboBox<Object>(wMargin.toArray());
        rightMargin.setSelectedIndex(2);
        JLabel top = new JLabel("Top margin");
        topMargin = new JComboBox<Object>(hMargin.toArray());
        topMargin.setSelectedIndex(2);
        JButton okMargin = new JButton("Ok");
        okMargin.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        okMargin.setActionCommand("ok");
        JButton cancelMargin = new JButton("Cancel");
        cancelMargin.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        cancelMargin.setActionCommand("cancel");

        marginPanel.setLayout(new GridLayout(4,2,2,2));
        marginPanel.add(left);
        marginPanel.add(leftMargin);
        marginPanel.add(right);
        marginPanel.add(rightMargin);
        marginPanel.add(top);
        marginPanel.add(topMargin);
        marginPanel.add(okMargin);
        marginPanel.add(cancelMargin);

        return marginPanel;
    }
    public JPanel getFontTab(ToolBarInterface tool){
        String[] fonts =
                GraphicsEnvironment.getLocalGraphicsEnvironment().
                        getAvailableFontFamilyNames();
        String[] size = new String[95];
        for(int i = 0; i<95; i++){
            size[i]=String.valueOf(i+6);
        }
        JPanel fontPanel = new JPanel();
        JLabel fontSize = new JLabel("Font size:");
        JLabel fontType = new JLabel("Font:");
        JButton okFont = new JButton("OK");
        okFont.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        okFont.setActionCommand("ok");
        JButton cancelFont = new JButton("Cancel");
        cancelFont.addActionListener(SimpleNote.getSimpleNote().new TabListener());
        cancelFont.setActionCommand("cancel");
        fontComboBox = new JComboBox<String>(fonts);
        fontComboBox.setSelectedIndex(tool.fontTypeList.getSelectedIndex());
        sizeComboBox = new JComboBox<String>(size);
        sizeComboBox.setSelectedIndex(tool.fontSizeList.getSelectedIndex());
        fontPanel.setLayout(new GridLayout(3,2,2,2));
        fontPanel.add(fontSize);
        fontPanel.add(sizeComboBox);
        fontPanel.add(fontType);
        fontPanel.add(fontComboBox);
        fontPanel.add(okFont);
        fontPanel.add(cancelFont);
        return fontPanel;
    }
}

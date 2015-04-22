import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;

public class TextInterface {
    Dimension mainDimension;
    JTextArea textArea;
    Highlighter highlighter;
    DefaultCaret caret;


    TextInterface(Dimension size){
        mainDimension = size;
        textArea = null;
    }

    protected JScrollPane getTextArea(){
        textArea = new JTextArea(){

            @Override
            public Dimension getPreferredSize(){
                Dimension d = super.getPreferredSize();
                d.width =(int)mainDimension.getWidth();
                d.height =(int)Math.max(d.height,
                        mainDimension.getHeight());
                return d;
            }
        };
        highlighter = textArea.getHighlighter();
        textArea.getPreferredSize();
        caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);

        Font mainFont = new Font("Ariala",Font.PLAIN,12);

        textArea.setFont(mainFont);
        textArea.setBorder(BorderFactory.createEmptyBorder(28,28,0,28));
        textArea.addMouseListener(new MouseAction());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JPanel textPanel = new JPanel();
        textPanel.add(textArea);
        JScrollPane textScroll = new JScrollPane(textPanel);
        textScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.
                VERTICAL_SCROLLBAR_AS_NEEDED);


        return textScroll;
    }
    public int find(String findWord, int caseCheck, int keepSearch){
        String text;
        int endPoint;
        int findPoint;


        text = textArea.getText();
        String searchString = findWord;
        if(caseCheck==1){
            text = text.toLowerCase();
            searchString = searchString.toLowerCase();
        }


        Highlighter.HighlightPainter painter = new DefaultHighlighter.
                DefaultHighlightPainter(Color.LIGHT_GRAY);

        findPoint = text.indexOf(searchString,keepSearch);
        endPoint = findPoint+searchString.length();
        if(findPoint!=-1){
            try{
                highlighter.addHighlight(findPoint,endPoint,painter);
            }catch(BadLocationException ex){
                ex.printStackTrace();
            }
            keepSearch=endPoint;
        }else{
            JOptionPane.showMessageDialog(textArea,"\""+findWord+"\""+
                    " not found.","Warning!",JOptionPane.WARNING_MESSAGE);
        }
        textArea.setCaretPosition(findPoint);
        return keepSearch;
    }
    public void replace(JTextArea jTextArea, String findWord,String replaceWord, int keepSearch){

        String text = jTextArea.getText();
        text = text.substring(0,keepSearch-findWord.length())+
                replaceWord+text.substring(keepSearch);
        jTextArea.setText(text);
    }
    public void removeHighlight(){
        highlighter.removeAllHighlights();
    }

}

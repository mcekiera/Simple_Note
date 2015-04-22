import javax.swing.*;
import java.awt.*;

public class FindAndReplace {
    protected JTextField findField;
    protected JTextField replaceField;
    protected JCheckBox caseSensitive;
    protected JDialog findAndReplace;
    protected int caseCheck;
    protected int replaceCheck;
    protected int searchPoint;

    FindAndReplace(){
        findField = null;
        replaceField = null;
        caseSensitive = null;
        findAndReplace = null;
        caseCheck = 0;
        replaceCheck = 0;
        searchPoint = 0;
    }

    public void getFindAndReplace(JFrame frame){
        findAndReplace = new JDialog(frame,"Find and replace");
        JPanel findAndReplacePanel = new JPanel();
        findAndReplacePanel.setLayout(new GridLayout(7,1,2,2));
        JPanel findPanel1 = new JPanel(new GridLayout(1,2));
        JPanel findPanel2 = new JPanel(new GridLayout(1,2));
        JPanel replacePanel = new JPanel(new GridLayout(1,2));

        findField = new JTextField("find");
        replaceField = new JTextField("replace");
        JButton find = new JButton("Find");
        find.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        find.setActionCommand("find");
        JButton findNext = new JButton("Next");
        findNext.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        findNext.setActionCommand("findNext");
        JButton previousFind = new JButton("Previous");
        previousFind.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        previousFind.setActionCommand("previousFind");
        JButton findAll = new JButton("Find all");
        findAll.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        findAll.setActionCommand("findAll");
        JButton replace = new JButton("Replace");
        replace.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        replace.setActionCommand("replace");
        JButton replaceAll = new JButton("Replace all");
        replaceAll.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        replaceAll.setActionCommand("replaceAll");
        caseSensitive = new JCheckBox("Case sensitive");
        caseSensitive.setSelected(true);
        caseSensitive.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        JButton okFind = new JButton("OK");
        okFind.addActionListener(SimpleNote.getSimpleNote().new FARListener());
        okFind.setActionCommand("okFind");
        findAndReplacePanel.add(findField);
        findPanel1.add(find);
        findPanel1.add(findAll);
        findPanel2.add(findNext);
        findPanel2.add(previousFind);
        findAndReplacePanel.add(findPanel1);
        findAndReplacePanel.add(findPanel2);
        findAndReplacePanel.add(replaceField);
        replacePanel.add(replace);
        replacePanel.add(replaceAll);
        findAndReplacePanel.add(replacePanel);
        findAndReplacePanel.add(caseSensitive);
        findAndReplacePanel.add(okFind);
        findAndReplace.add(findAndReplacePanel);
        findAndReplace.pack();
        findAndReplace.setLocationRelativeTo(frame);
        findAndReplace.setVisible(false);
        findAndReplace.setEnabled(false);
    }

}

import javax.swing.*;
import java.io.*;

public class InputOutput {
    JFrame hostFrame;
    JTextArea area;
    File file;
    JFileChooser fileChooser;
    InputOutput(JFrame host, JTextArea content){
        hostFrame = host;
        area = content;
        fileChooser = new JFileChooser();
        file = null;
    }
    public void saveAs(JTextArea jTextArea, JFrame frame){
        fileChooser.showSaveDialog(hostFrame);
        this.file = fileChooser.getSelectedFile();
        save(jTextArea,frame);
    }
    public void save(JTextArea jTextArea, JFrame frame){
        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            jTextArea.write(writer);
            writer.close();
            frame.setTitle(file.getName() + " - SimpleNote v.2");
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public void open(JTextArea jTextArea, JFrame frame){
        try{
            fileChooser.showOpenDialog(hostFrame);
            this.file=fileChooser.getSelectedFile();
            InputStream inputStream=new FileInputStream(file);
            InputStreamReader inputReader=new InputStreamReader(
                    inputStream,"UTF-8");
            BufferedReader reader=new BufferedReader(inputReader);
            jTextArea.read(reader,file);
            frame.setTitle(file.getName() + "-SimpleTextEditor v.2");
            reader.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}

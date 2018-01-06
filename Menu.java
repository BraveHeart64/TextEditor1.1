import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class Menu extends JPanel implements ActionListener{

private int height = 500;
private int width = 500;
private int textHeight = 500;
private int textWidth = 0;
private JScrollPane scrollBare;
private JFrame mainFrame = new JFrame("Ryan Text Editor!");
private JMenu menu = new JMenu("File");
private JMenuBar bar = new JMenuBar();
private JMenuItem newFile = new JMenuItem("New", new ImageIcon("file.gif"));
private JMenuItem openFile = new JMenuItem("Open", new ImageIcon("open.gif"));
private JMenuItem printFile = new JMenuItem("Print", new ImageIcon("printer.gif"));
private JMenuItem endProgram = new JMenuItem("Quit", new ImageIcon("quit.gif"));
private JMenuItem saveFile = new JMenuItem("Save", new ImageIcon("save.gif"));
private JFileChooser chooser = new JFileChooser();
private String nameOfFile;
private JPanel panel = new JPanel();
private JTextArea text = new JTextArea(textHeight,textWidth);

public void window(){
 //Datuim = new String();
scrollBare = new JScrollPane(text);
bar.add(menu);


mainFrame.setIconImage(new ImageIcon("icon.gif").getImage());
mainFrame.setJMenuBar(bar);
mainFrame.setSize(height,width); 
mainFrame.add(panel,null);
mainFrame.add(scrollBare);
text.setFont(new Font("Time New Roman", Font.PLAIN, 16));
text.setLineWrap(true);
//mainFrame.setResizable(false); // remove this later

menu.add(newFile);
menu.add(openFile);
menu.add(saveFile);
menu.add(printFile);
menu.add(endProgram);


mainFrame.setLocationRelativeTo(null); // places screen relative to the montior.
mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
mainFrame.setVisible(true);

newFile.addActionListener(this);
saveFile.addActionListener(this);
openFile.addActionListener(this); 
endProgram.addActionListener(new quit());



}


 static class quit implements ActionListener
{

public void actionPerformed(ActionEvent e)
{
 System.exit(0);
 }
}


public void actionPerformed(ActionEvent e){

if(e.getSource() == saveFile){
	
	saveFile();
}
 else if(e.getSource() == openFile){


loadFile();           
 
  }
  else if(e.getSource() == newFile){
	  
	  m_NewFile();
  }
}
  
  public void loadFile(){
	
	BufferedReader rFile = null ;	
      Path  filePath;
      File file;
	  
	  String fileData = null;
      
      String newline = "\n";
	  
	  int data = chooser.showOpenDialog(this);
		

 
 
 
  FileNameExtensionFilter filter = new FileNameExtensionFilter(
        ".txt .cpp .py .pl .c .h .html .css .asm and .java files", "txt", "pl", "c", "java","cpp","py","h","html","css","asm");
		chooser.setFileFilter(filter);
		 
 // filePath = Paths.get(getName());
   

   if(data == JFileChooser.APPROVE_OPTION){
               
               filePath = Paths.get(chooser.getSelectedFile().getAbsolutePath());
               file = chooser.getSelectedFile();
			   fileData = file.getAbsolutePath();
			    text.setText("I am a programmer!");
               try{
				
			    FileReader Read = new FileReader(fileData);
			     rFile = new BufferedReader(Read); 
			   
			
				
			   text.read(rFile,null);

			   rFile.close();
			   text.requestFocus();
			   
				  
			   }
				catch(IOException ex){
					 ex.printStackTrace();
					 JOptionPane.showMessageDialog(text,"File did not load"+ ex.getMessage());
				}
				
				
				
            }
 
           
   text.setCaretPosition(text.getDocument().getLength());

              
	  
  }
  
 public void m_NewFile(){
	  
	  text.setText("");
	  
  }
  
  
  public void saveFile(){
	  
	  BufferedReader rFile = null;	
      Path  filePath = null;
      File file;
      String newline = "\n";
	  
	  
	   
 	 
                 int data = chooser.showSaveDialog(this);
	         if(data == JFileChooser.APPROVE_OPTION){
	           filePath = Paths.get(chooser.getSelectedFile().getAbsolutePath());
                    file = chooser.getSelectedFile();

	}
         BufferedWriter wFile = null;
        try{
           
          wFile = Files.newBufferedWriter(filePath, Charset.defaultCharset());
         
           
            text.write(wFile);

         }catch(IOException ex){

          ex.printStackTrace();
         }finally{ if(wFile != null){
try{
wFile.close();
}catch(IOException efile){}
 }

     }

   
	  
  }
 
}
  


 
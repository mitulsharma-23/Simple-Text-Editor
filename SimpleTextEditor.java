import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {
    JFrame frame; //Declaring frame
    JTextArea TextArea; //Declaring text area
    JMenuBar MenuBar; //Declaring Menu Bar
    JMenu File, Edit, Close; //Declaring the file menu and the edit menu and the close menu
    JMenuItem MenuItem1, MenuItem2, MenuItem3, MenuItem4, MenuItem5, MenuItem6, MenuItem7, MenuItem8; //Declaring menu items
    SimpleTextEditor(){
        frame = new JFrame("Simple Text Editor"); //Initialising frame
        frame.setBounds(200, 100, 1500, 800); //Setting frame size when running the program

        TextArea = new JTextArea("Welcome to the Simple Text Editor.");
        MenuBar = new JMenuBar(); //Initialising menu bar

        //Initialising menus
        File = new JMenu("File"); //Initialising the file menu
        Edit = new JMenu("Edit"); //Initialising the edit menu
        Close = new JMenu("Close"); //Initialising the close menu

        //Adding menus in menu bar
        MenuBar.add(File); //Adding the file menu in the menu bar
        MenuBar.add(Edit); //Adding the edit menu in the menu bar
        MenuBar.add(Close); //Adding the close menu in the menu bar

        //Initialising menu items for File menu
        MenuItem1 = new JMenuItem("New"); //Initialising new menu item
        MenuItem1.addActionListener(this); //Adding function in new menu item
        MenuItem2 = new JMenuItem("Open"); //Initialising open menu item
        MenuItem2.addActionListener(this); //Adding function in the open menu item
        MenuItem3 = new JMenuItem("Save"); //Initialising save menu item
        MenuItem3.addActionListener(this); //Adding function in save menu item
        MenuItem4 = new JMenuItem("Print"); //Initialising print menu item
        MenuItem4.addActionListener(this);

        //Initialising menu items for Edit menu
        MenuItem5 = new JMenuItem("Cut"); //Initialising cut menu item
        MenuItem5.addActionListener(this); //Adding function in the cut menu item
        MenuItem6 = new JMenuItem("Copy"); //Initialising copy menu item
        MenuItem6.addActionListener(this); //Adding function in the copy menu item
        MenuItem7 = new JMenuItem("Paste"); //Initialising paste menu item
        MenuItem7.addActionListener(this); //Adding function in the paste menu item

        //Initialising menu item for CLose menu
        MenuItem8 = new JMenuItem("Close"); //Initialising close menu item
        MenuItem8.addActionListener(this); //Adding function in the close menu item

        //Adding menu items in File menu
        File.add(MenuItem1); //Adding new menu item in file menu
        File.add(MenuItem2); //Adding open menu item in file menu
        File.add(MenuItem3); //Adding save menu item in file menu
        File.add(MenuItem4); //Adding print menu item in file menu

        //Adding menu items in Edit menu
        Edit.add(MenuItem5); //Adding cut menu item in edit menu
        Edit.add(MenuItem6); //Adding copy menu item in edit menu
        Edit.add(MenuItem7); //Adding paste menu item in edit menu

        //Adding menu item in Close menu
        Close.add(MenuItem8); //Adding close menu item in close menu

        frame.setJMenuBar(MenuBar); //Adding the menu bar in the frame
        frame.add(TextArea); //Adding the text area in the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //When closing the frame, the program will also stop
        frame.setVisible(true); //Setting frame as visible
    }

    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy")){
            TextArea.copy(); //Inbuilt copy function
        }else if(s.equals("Cut")){
            TextArea.cut(); //Inbuilt cut function
        }else if(s.equals("Paste")){
            TextArea.paste(); //Inbuilt paste function
        }else if(s.equals("Print")){
            //Using try and catch to avoid error
            try {
                TextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else if(s.equals("New")){
            TextArea.setText(""); //Setting text area blank
        }else if(s.equals("Close")){
            frame.setVisible(false); //Making the frame invisible
            System.exit(1); //Used to terminate the program
        }else if(s.equals("Open")){
            //handle file operation
            //we can give default directory
            JFileChooser choose = new JFileChooser("C:");
            //for open and close options for file explorer
            int ans = choose.showOpenDialog(null);
            //for open option
            if(ans == JFileChooser.APPROVE_OPTION){
                //now handle file
                //1. get file path
                File file = new File(choose.getSelectedFile().getAbsolutePath());
                String s1 = "", s2 = "";
                //2. read file
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    s2 = reader.readLine();
                    while((s1 = reader.readLine()) != null){
                        //for multiple lines read till buffered reader null
                        //or till not reached the end line
                        s2 += s1 + "\n";
                    }
                    //set text in text area
                    TextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s.equals("Save")){
            //write the file and save it
            JFileChooser choose = new JFileChooser("C:");
            int ans = choose.showSaveDialog(null);
            //if chooses open by user
            if(ans == choose.APPROVE_OPTION){
                //1. get file path
                File file = new File(choose.getSelectedFile().getAbsolutePath());
                //2. buffered writer to write bytes on file
                BufferedWriter writer = null;
                try {
                    //until user don't stop so don't stop and location where to write
                    //no need for while loop to check for end of file
                    writer = new BufferedWriter(new FileWriter(file, false));
                    //get text from text area
                    writer.write(TextArea.getText());
                    //flush the writer
                    writer.flush();
                    //closing
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

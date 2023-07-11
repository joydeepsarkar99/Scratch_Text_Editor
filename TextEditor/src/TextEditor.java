import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit,view;
    JMenuItem newFile,openFile,saveFile; //file menu items
    JMenuItem cut,copy,paste,selectAll; //edit menu items
    JMenuItem close; //view menu items
    JTextArea textArea;


    TextEditor(){
        //Initialize the JFrame
        frame = new JFrame("Scratch Editor");

        //setting up the icon for the text Editor
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        frame.setIconImage(icon.getImage());

        //Initialize the JMenuBar
        menuBar = new JMenuBar();

        //Initialize the JTextArea
        textArea = new JTextArea();

        //Initialize the JMenu
        file = new JMenu("File");
        edit = new JMenu("Edit");
        view = new JMenu("View");

        //Initialize the file menu items
        newFile = new JMenuItem("New Window");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");

        //Add Action Listener to the file menu items
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);

        //adding these menu items in file menu
        file.add(newFile);
        file.add(saveFile);
        file.add(openFile);

        //Initialize the edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");

        //Add Action Listener to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        //adding these menu items in the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        //Initialize the view menu items
        close = new JMenuItem("close");

        //Add Action Listener to the view menu items
        close.addActionListener(this);

        //adding the menu items in the view menu
        view.add(close);

        //adding the file, edit & view menu to the menuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);

        //add the menuBar to the frame
        frame.setJMenuBar(menuBar);

        //Create Content Pane for the Scroll pane and text area to be added
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea);

        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll pane to panel
        panel.add(scrollPane);

        //add panel to the frame
        frame.add(panel);

        //set location of the frame and dimension
        frame.setBounds(500,150,400,400);

        //setting visibility of the frame
        frame.setVisible(true);

        frame.setLayout(null); //default layout to null
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //working of the Edit menu items
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            //it performs the cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            //it performs the copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            //it performs the paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            //it performs the selectAll operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            //it performs the close operation
            //what it does is it stops the execution of the program from the console
            System.exit(0);
        }

        if (actionEvent.getSource() == openFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file picker
            int chooseOption = fileChooser.showOpenDialog(null); //two option : open or cancel

            //if we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //getting the selected file path
                String filePath = file.getPath();

                try {
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read contents of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == saveFile) {
            //Initialize the file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file picker
            int chooseOption = fileChooser.showSaveDialog(null); //two option : save or cancel

            //if we have clicked on save button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write content of text file to newly created file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            //creating a new textEditor window
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
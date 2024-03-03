package src;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NotepadGUI extends JFrame {
    // File explorer
    private JFileChooser jFileChooser;
    private JTextArea textArea;
    private File currentFile;

    public NotepadGUI() {
        super("Notepad");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("src/assets"));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        addGuiComponents();
    }

    private void addGuiComponents() {
        addToolBar();
    }

    private void addToolBar() {
        JToolBar toolBar = new JToolBar();
        // Fix toolbar
        toolBar.setFloatable(false);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);

        // Add menus
        menuBar.add(addFileMenu());

        add(toolBar, BorderLayout.NORTH);

        textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);
    }

    private JMenu addFileMenu() {
        JMenu fileMenu = new JMenu("File");

        // New
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset Notepad
                setTitle("Notepad");
                textArea.setText("");
                currentFile = null;
            }
        });
        fileMenu.add(newMenuItem);

        // Open
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = jFileChooser.showOpenDialog(NotepadGUI.this);
                if (result != JFileChooser.APPROVE_OPTION) return;

                try {
                    // Reset notepad
                    newMenuItem.doClick();

                    File selectedFile = jFileChooser.getSelectedFile();
                    currentFile = selectedFile;
                    setTitle(selectedFile.getName());

                    // Read the file
                    FileReader fileReader = new FileReader(selectedFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    // Store the text
                    StringBuilder fileText = new StringBuilder();
                    String readText;
                    while ((readText = bufferedReader.readLine()) != null) {
                        fileText.append(readText + "\n");
                    }

                    // Update text area gui
                    textArea.setText(fileText.toString());

                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        fileMenu.add(openMenuItem);

        // Save as
        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = jFileChooser.showSaveDialog(NotepadGUI.this);
                if (result != JFileChooser.APPROVE_OPTION) return;

                try {
                    File selectedFile = jFileChooser.getSelectedFile();

                    // Append .txt to the file
                    String fileName = selectedFile.getName();
                    if (!fileName.substring(fileName.length() - 4).equalsIgnoreCase(".txt")) {
                        System.out.println("HZEERE");
                        selectedFile = new File(selectedFile.getAbsoluteFile() + ".txt");
                    }

                    selectedFile.createNewFile();

                    // Write the user's text into the file created
                    FileWriter fileWriter = new FileWriter(selectedFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(textArea.getText());

                    bufferedWriter.close();
                    fileWriter.close();

                    // Update the title header of the gui to the save text file
                    setTitle(fileName);

                    currentFile = selectedFile;

                    JOptionPane.showMessageDialog(NotepadGUI.this, "File saved !");
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        fileMenu.add(saveAsMenuItem);

        // Save
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If the current file is null, I have to perferm save as functionality
                if (currentFile == null) saveAsMenuItem.doClick();

                // If the user chooses to cancel saving the file this means that current file
                // will still be null
                if (currentFile == null) return;

                try {
                    FileWriter fileWriter = new FileWriter(currentFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(textArea.getText());
                    bufferedWriter.close();
                    fileWriter.close();
                }catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        fileMenu.add(saveMenuItem);

        // Exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotepadGUI.this.dispose();
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }
}


























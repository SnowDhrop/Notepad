package src;

import javax.swing.*;
import java.awt.*;

public class NotepadGUI extends JFrame {
    public NotepadGUI() {
        super("Notepad");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        JTextArea textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);
    }

    private JMenu addFileMenu() {
        JMenu fileMenu = new JMenu("File");

        // New
        JMenuItem newMenuItem = new JMenuItem("New");
        fileMenu.add(newMenuItem);

        // Open
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);

        // Save as
        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        fileMenu.add(saveAsMenuItem);

        // Save
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);

        // Exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }
}


























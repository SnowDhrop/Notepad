package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FontMenu extends JDialog {
    private NotepadGUI source;

    public FontMenu(NotepadGUI source) {
        this.source = source;

        setTitle("Font Settings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(425, 350);
        setLocationRelativeTo(source); // Launch the menu at the center of the Notepad GUI
        setLayout(null); // Removes layout management
        setModal(true);

        addMenuComponents();
    }

    private void addMenuComponents() {
        addFontChooser();
    }

    private void addFontChooser() {
        JLabel fontLabel = new JLabel("Font:");
        fontLabel.setBounds(10, 5, 125, 10);
        add(fontLabel);

        // Font panels displays the current font and the list of fonts available
        JPanel fontPanel = new JPanel();
        fontPanel.setBounds(10, 15, 125, 160);

        JTextField currentFontField = new JTextField(source.getTextArea().getFont().getFontName());
        currentFontField.setPreferredSize(new Dimension(125, 25));
        currentFontField.setEditable(false);
        fontPanel.add(currentFontField);

        // Display list of available fonts
        JPanel listOfFontsPanel = new JPanel();
        // One column to display each font properly
        listOfFontsPanel.setLayout(new BoxLayout(listOfFontsPanel, BoxLayout.Y_AXIS));
        listOfFontsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listOfFontsPanel);
        scrollPane.setPreferredSize(new Dimension(125,125));

        // Retrieve all of the possible fonts
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        for(String fontName : fontNames) {
            JLabel fontNameLabel = new JLabel(fontName);

            fontNameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    currentFontField.setText(fontName);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Add hightlight when the mouse over them
                    fontNameLabel.setOpaque(true);
                    fontNameLabel.setBackground(Color.GRAY);
                    fontNameLabel.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    fontNameLabel.setBackground(null);
                    fontNameLabel.setForeground(null);
                }
            });

            listOfFontsPanel.add(fontNameLabel);
        }

        fontPanel.add(scrollPane);


        add(fontPanel);
    }
}

































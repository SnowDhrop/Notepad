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
        addFontStyleChooser();
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

    private void addFontStyleChooser() {
        JLabel fontStyleLabel = new JLabel("Font style:");
        fontStyleLabel.setBounds(145, 5, 125, 10);
        add(fontStyleLabel);

        JPanel fontStylePanel = new JPanel();
        fontStylePanel.setBounds(145, 15, 125, 160);

        // Get current font style
        int currentFontStyle = source.getTextArea().getFont().getStyle();
        String currentFontStyleText;

        switch(currentFontStyle) {
            case Font.PLAIN:
                currentFontStyleText = "Plain";
                break;

            case Font.BOLD:
                currentFontStyleText = "Bold";
                break;

            case Font.ITALIC:
                currentFontStyleText = "Italique";
                break;

            default:
                currentFontStyleText = "Bold Italic";
                break;
        }

        System.out.println(currentFontStyleText);

        JTextField currentFontStyleField = new JTextField(currentFontStyleText);
        currentFontStyleField.setPreferredSize(new Dimension(125, 25));
        currentFontStyleField.setEditable(false);
        fontStylePanel.add(currentFontStyleField);

        // Display list of all font style available
        JPanel listOfFontStylesPanel = new JPanel();
        listOfFontStylesPanel.setLayout(new BoxLayout(listOfFontStylesPanel, BoxLayout.Y_AXIS));
        listOfFontStylesPanel.setBackground(Color.WHITE);

        JLabel plainStyle = new JLabel("Plain");
        plainStyle.setFont(new Font("Dialog", Font.PLAIN, 12));
        listOfFontStylesPanel.add(plainStyle);

        JLabel boldStyle = new JLabel("Bold");
        boldStyle.setFont(new Font("Dialog", Font.BOLD, 12));
        listOfFontStylesPanel.add(boldStyle);

        JLabel italicStyle = new JLabel("Italic");
        italicStyle.setFont(new Font("Dialog", Font.ITALIC, 12));
        listOfFontStylesPanel.add(italicStyle);

        JLabel boldItalicStyle = new JLabel("Bold Italic");
        boldItalicStyle.setFont(new Font("Dialog", Font.ITALIC | Font.BOLD, 12));
        listOfFontStylesPanel.add(boldItalicStyle);

        JScrollPane scrollPane = new JScrollPane(listOfFontStylesPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));

        fontStylePanel.add(scrollPane);

        add(fontStylePanel);
    }
}

































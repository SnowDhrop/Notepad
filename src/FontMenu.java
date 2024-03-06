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
        addFontSizeChooser();
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
        String currentFontStyleText = switch (currentFontStyle) {
            case Font.PLAIN -> "Plain";
            case Font.BOLD -> "Bold";
            case Font.ITALIC -> "Italique";
            default -> "Bold Italic";
        };

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
        setLogicFontStyle(plainStyle, currentFontStyleField);
        listOfFontStylesPanel.add(plainStyle);

        JLabel boldStyle = new JLabel("Bold");
        boldStyle.setFont(new Font("Dialog", Font.BOLD, 12));
        setLogicFontStyle(boldStyle, currentFontStyleField);
        listOfFontStylesPanel.add(boldStyle);

        JLabel italicStyle = new JLabel("Italic");
        italicStyle.setFont(new Font("Dialog", Font.ITALIC, 12));
        setLogicFontStyle(italicStyle, currentFontStyleField);
        listOfFontStylesPanel.add(italicStyle);

        JLabel boldItalicStyle = new JLabel("Bold Italic");
        boldItalicStyle.setFont(new Font("Dialog", Font.ITALIC | Font.BOLD, 12));
        setLogicFontStyle(boldItalicStyle, currentFontStyleField);
        listOfFontStylesPanel.add(boldItalicStyle);

        JScrollPane scrollPane = new JScrollPane(listOfFontStylesPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));

        fontStylePanel.add(scrollPane);

        add(fontStylePanel);
    }

    private void setLogicFontStyle(JLabel fontStyleName, JTextField currentFontStyleField) {
        fontStyleName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Update current style field
                currentFontStyleField.setText(fontStyleName.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fontStyleName.setOpaque(true);
                fontStyleName.setBackground(Color.GRAY);
                fontStyleName.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fontStyleName.setBackground(null);
                fontStyleName.setForeground(null);
            }
        });
    }

    private void addFontSizeChooser() {
        JLabel fontSizeLabel = new JLabel("Font size:");
        fontSizeLabel.setBounds(275, 5, 125, 10);
        add(fontSizeLabel);

        JPanel fontSizePanel = new JPanel();
        fontSizePanel.setBounds(275, 15, 125, 160);

        JTextField currentFontSizeField = new JTextField((
                Integer.toString(source.getTextArea().getFont().getSize())
        ));
        currentFontSizeField.setPreferredSize(new Dimension(125, 25));
        currentFontSizeField.setEditable(false);
        fontSizePanel.add(currentFontSizeField);

        // List of font sizes to choose from
        JPanel listOfFontSizesPanel = new JPanel();
        listOfFontSizesPanel.setLayout(new BoxLayout(listOfFontSizesPanel, BoxLayout.Y_AXIS));
        listOfFontSizesPanel.setBackground(Color.WHITE);

        // Availables font size will be from 8 to 72 with increments of 2
        for(int i=0; i<73; i+=2) {
            JLabel fontSizeValueLabel = new JLabel(Integer.toString(i));
            fontSizeValueLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentFontSizeField.setText(fontSizeValueLabel.getText());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    fontSizeValueLabel.setOpaque(true);
                    fontSizeValueLabel.setBackground(Color.GRAY);
                    fontSizeValueLabel.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    fontSizeValueLabel.setOpaque(false);
                    fontSizeValueLabel.setBackground(null);
                    fontSizeValueLabel.setForeground(null);
                }
            });
            listOfFontSizesPanel.add(fontSizeValueLabel);
        }

        JScrollPane scrollPane = new JScrollPane(listOfFontSizesPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));
        fontSizePanel.add(scrollPane);

        add(fontSizePanel);
    }
}

































package appGUI;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class AppGui extends JFrame {

    private JLabel fileNameLabel;
    private JLabel fileSizeLabel;

    private JButton importButton;
    private JButton convertButton;
    private  JButton deleteButton;

    // Store the File Imported state
    private File selectedFile;

    public AppGui() {
        initialize();
    }

    private void initialize() {

        setTitle("TXT to PDF Converter");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background global
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(new GridBagLayout());

        // Card Panel (effet carte centrale)
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        // === ICON + FILE NAME PANEL ===
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        filePanel.setBackground(Color.WHITE);

        Icon fileIcon = FileSystemView.getFileSystemView()
                .getSystemIcon(new File("test.txt"));

        JLabel iconLabel = new JLabel(fileIcon);

        fileNameLabel = new JLabel("Please select a .txt file");
        fileNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        filePanel.add(iconLabel);
        filePanel.add(fileNameLabel);

        // === FILE SIZE ===
        fileSizeLabel = new JLabel("");
        fileSizeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fileSizeLabel.setForeground(Color.GRAY);
        fileSizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // === BUTTON PANEL ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(Color.WHITE);

        importButton = new JButton("Import");
        convertButton = new JButton("Convert");
        deleteButton = new JButton("X");

        convertButton.setEnabled(false);

        // Add the style
        stylePrimaryButton(importButton);
        styleSecondaryButton(convertButton);
        styleDeleteButton(deleteButton);

        // Add to the Panel
        buttonPanel.add(importButton);
        buttonPanel.add(convertButton);
        buttonPanel.add(deleteButton);

        // === ASSEMBLY ===
        cardPanel.add(filePanel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        cardPanel.add(fileSizeLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        cardPanel.add(buttonPanel);

        add(cardPanel);
    }

    // Button Design
    private void stylePrimaryButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
    }

    private void styleSecondaryButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(new Color(46, 204, 113));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
    }

    private void styleDeleteButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(231, 20, 186));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
    }

    // GETTER
    public JButton getImportButton() {
        return importButton;
    }
    public JButton getConvertButton() {
        return convertButton;
    }
    public File getSelectedFile() {
        return this.selectedFile;
    }
    public JButton getDeleteButton() {
        return this.deleteButton;
    }

    // SETTER
    public void setFileName(String name) {
        fileNameLabel.setText(name);
    }
    public void setFileSizeLabel(String sizeLabel) {
        fileSizeLabel.setText("Size:" + " " + sizeLabel + " " + "KB");
    }
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

}

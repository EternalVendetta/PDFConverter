package appControler;

import appGUI.AppGui;
import appService.AppService;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AppControler {
    // View
    private AppGui gui;
    // Logic
    private AppService service;

    // Constructor Dependency Injection
    public AppControler(AppGui gui, AppService service) {
        this.gui = gui;
        this.service = service;

        initController();
    }

    // !!! DO NOT REPEEAT YOURSELF ON selectedFile variable

    private void updateConvertButtonState() {
        gui.getConvertButton().setEnabled(gui.getSelectedFile() != null);
    }

    private void initController() {
        // IMPORT .Txt FILE
        gui.getImportButton().addActionListener(e -> {
            // Build a searching Path to desktop
            String userHome = System.getProperty("user.home");
            File desktop = new File(userHome, "Desktop");


            JFileChooser fileChooser = new JFileChooser(desktop);
            int result = fileChooser.showOpenDialog(gui);

            if (result == JFileChooser.APPROVE_OPTION) {
               // Pick up the fileName as String
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
                // Check if the file selected is a  .txt extension
                if (service.isText(fileName)) {
                    // Print the name to the View
                    gui.setSelectedFile(selectedFile);
                    gui.setFileName("File:" + " " + fileName);

                    // Print the File Size to the View
                    long fileSize = selectedFile.length();
                    gui.setFileSizeLabel(service.toKiloBytes(fileSize));

                    // Show the Convert button  & Delete only after import File
                    gui.getConvertButton().setVisible(true);
                    gui.getDeleteButton().setVisible(true);

                   // Change the Convert Btn View
                    updateConvertButtonState();

                } else {
                    // IF NOT A .Txt File
                    JOptionPane.showMessageDialog(
                            gui,
                            "Only .txt files are allowed",
                            "Invalid file",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // CONVERT TO PDF
        gui.getConvertButton().addActionListener(e -> {

            // If there is no FIle to convert -> Size = 0 KB
            if (gui.getSelectedFile() == null) {
                JOptionPane.showMessageDialog(gui, "Import a .Txt file first.");
                return;
            }

            try {
                // Take the imported file as source
                File source = gui.getSelectedFile();
                File destination = new File(
                        source.getParent(),
                        source.getName().replaceAll("\\.txt$", ".pdf") // Possible Error
                );
                // Call the convertMethod here and pass the source & destination as args
                service.convertToPdf(source, destination);
                // Make a POP UP SUCESSFUL
                JOptionPane.showMessageDialog(gui, "File has been converted successfully and sent to your /Desktop !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui, "Error:" + " " + ex.getMessage());
            }

        });

        gui.getDeleteButton().addActionListener(e -> {
            // Set the default
            gui.setFileName("Please select a .txt file");
            // Set the Size to 0
            gui.setFileSizeLabel("0");
            // Clear the File selected
            gui.setSelectedFile(null);

            // Change the Convert Btn view
            updateConvertButtonState();

        });
    }
}

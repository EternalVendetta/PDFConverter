import appControler.AppControler;
import appGUI.AppGui;
import appService.AppService;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            // Instantiate GUI and Service
            AppGui gui = new AppGui();
            AppService service = new AppService();

            // Inject both into Controller
            new AppControler(gui, service);

            // Set GUI visible
            gui.setVisible(true);
            // Set OS Native Design for File search
            FlatLightLaf.setup();
        });
    }
}
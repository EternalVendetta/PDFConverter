package appServiceTest;

// NOTE
/*
Les Test sont une simmulation de la logique métier avec des Elements factices
Que l'on apelle MOCK, donc on utilise Mockito

Avec JUnit + Mockito tu vas comprendre :
Injection de dépendance
Interfaces
Découplage
Test isolation
Architecture propre
TDD -> Test Driven Development
 */

// Import the Class to be Tested
import appGUI.AppGui;
import appService.AppService;

// Import the Tool to Test
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppServiceTest {
    AppService service = new AppService();

    @Test
    void shouldReturnTrueIfText() {
        // Arange
        String fileName = "document.txt";
        // Act
        boolean result = service.isText(fileName);
        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNotText() {
        // Arange
        String filename = "document.png";
        // Act
        boolean result = service.isText(filename);
        // Assert
        assertFalse(result);

    }

    @Test
    void shouldReturnFalseIfNull() {
        // Arrange
        String filename = null;
        // Act
        boolean result = service.isText(filename);
        // Asset
        assertFalse(result);
    }
}

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
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AppServiceTest {

    private AppService service;

    @BeforeEach
    void setUp() {
        service = new AppService();
    }

    @Test
    void shouldReturnTrueWhenFileHasTxtExtension() {
        assertTrue(service.isText("document.txt"));
    }

    @Test
    void shouldReturnTrueWhenExtensionIsUppercase() {
        assertTrue(service.isText("DOCUMENT.TXT"));
    }

    @Test
    void shouldReturnFalseWhenExtensionIsNotTxt() {
        assertFalse(service.isText("image.png"));
    }

    @Test
    void shouldReturnFalseWhenFilenameIsNull() {
        assertFalse(service.isText(null));
    }

    @Test
    void shouldReturnFalseWhenFilenameIsEmpty() {
        assertFalse(service.isText(""));
    }
}

package appService;

// Import
import java.io.*;
import java.text.DecimalFormat;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

// Logique Metier
public class AppService {

    public boolean isText(String filename) {
        return filename != null && filename.toLowerCase().endsWith(".txt");
    }

    public String toKiloBytes(long value) {
        double kb = value / 1024.0;
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(kb);
    }

    public void convertToPdf(File source, File destination) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDType1Font font = PDType1Font.HELVETICA;
            float fontSize = 12;
            float margin = 50;
            float leading = 1.5f * fontSize;

            PDPageContentStream contentStream =
                    new PDPageContentStream(document, page);

            contentStream.setFont(font, fontSize);
            contentStream.beginText();

            float yPosition = page.getMediaBox().getHeight() - margin;
            float width = page.getMediaBox().getWidth() - 2 * margin;

            contentStream.newLineAtOffset(margin, yPosition);

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.replaceAll("\\p{Cntrl}", " ");

                String[] words = line.split(" ");
                StringBuilder currentLine = new StringBuilder();

                for (String word : words) {

                    String testLine = currentLine + word + " ";
                    float size = font.getStringWidth(testLine) / 1000 * fontSize;

                    if (size > width) {

                        contentStream.showText(currentLine.toString());
                        contentStream.newLineAtOffset(0, -leading);
                        yPosition -= leading;

                        if (yPosition <= margin) {
                            contentStream.endText();
                            contentStream.close();

                            page = new PDPage();
                            document.addPage(page);

                            contentStream =
                                    new PDPageContentStream(document, page);

                            contentStream.setFont(font, fontSize);
                            contentStream.beginText();

                            yPosition = page.getMediaBox().getHeight() - margin;
                            contentStream.newLineAtOffset(margin, yPosition);
                        }

                        currentLine = new StringBuilder(word + " ");
                    } else {
                        currentLine.append(word).append(" ");
                    }
                }

                contentStream.showText(currentLine.toString());
                contentStream.newLineAtOffset(0, -leading);
                yPosition -= leading;
            }

            contentStream.endText();
            contentStream.close();

            document.save(destination);
        }
    }
}

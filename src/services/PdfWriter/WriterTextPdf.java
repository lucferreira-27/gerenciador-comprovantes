package services.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class WriterTextPdf {
	public  void create(String text) {
		Document document = new Document();
		System.out.println("Editando!");
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\HelloWorld.pdf"));
			document.open();
			document.add(new Paragraph(text));
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

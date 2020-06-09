package services.PdfWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class WriterImgPdf {
	private Document document = null;
	private PdfWriter writer = null;
	public void addImages(List<String> listMonths, Map<String, List<String>> map, String pdfPath) {
		try {
			List<File> pdfFiles = new ArrayList<>();

			// Add Image
			List<String> path = null;
			for (int cc = 0; cc < listMonths.size(); cc++) {
				path = map.get(listMonths.get(cc));
				
				document = new Document();
				//String pdfPath = "D:\\" + listMonths.get(cc) + ".pdf";
				writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath + listMonths.get(cc) + ".pdf"));
				document.open();
				newImage(document, path);
				pdfFiles.add(new File(pdfPath));
				document.close();
				
				writer.close();
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newImage(Document document,List<String> path) throws Exception {
		float x = 0f;
		float y = 600f;
		int i = 1;
		int c = 0;
		
		for (String l : path) {
			Image image1 = Image.getInstance(l);
			// Fixed Positioning

			if (i % 2 == 0) {
				x += 300f;

			} else {
				x = 0f;
			}
			if (i == 3) {
				y -= 300f;
				i = 1;
			}
			image1.setAbsolutePosition(x, y);
			

			// Scale to new height and new width of image
			image1.scaleAbsolute(300, 300);

			// Add to document
			document.add(image1);
			c++;
			if (c == 5) {
				document.newPage();
				x = 0f;
				y = 600f;
			}
			
		}
	}
}

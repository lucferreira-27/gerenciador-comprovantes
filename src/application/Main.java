package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import files.FilesMananger;
import services.ImageTranscoding;
import services.RenameReceipt;
import services.PdfWriter.WriterImgPdf;
import services.PdfWriter.WriterTextPdf;
import services.printer.Printer;

public class Main {
	public static void main(String[] args) {
		FilesMananger manager = new FilesMananger();
		RenameReceipt rename = new RenameReceipt();
		ImageTranscoding transconding = new ImageTranscoding();
		WriterTextPdf writer = new WriterTextPdf();
		WriterImgPdf writerImg = new WriterImgPdf();
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Informe caminho dos arquivos!");
		String caminho = scan.nextLine(); 
		for (File f : manager.findFiles(
				caminho, "WhatsApp Image")) {
			String path = f.getParent();
			File file = rename.rename(f);

			String pathOld = file.getAbsolutePath();
			rename.setExtension(".png");
			transconding.transcoding(pathOld,
					path + "\\Comprovantes\\Novos\\" + rename.getMonth() + "\\" + rename.getNewName());

		}
		
		
		String caminho2 = caminho + "\\Comprovantes\\Novos";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> listMonths = new ArrayList<>();
		System.out.println(caminho);
		for (File f : manager.findFiles(caminho2, "")) {
			List<String> list = new ArrayList<>();
			
			listMonths.add(f.getName());
			for (File file : manager.findFiles(caminho2 + "\\" + f.getName(), "")) {

				list.add(caminho2 + "\\" + f.getName() + "\\" + file.getName());
			}
			map.put(f.getName(), list);
		}

		
		
		String pdfPath = caminho + "\\Comprovantes\\Comprovantes PDFs\\";
		writerImg.addImages(listMonths, map, pdfPath);


	}
}

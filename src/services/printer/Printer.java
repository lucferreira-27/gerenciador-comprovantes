package services.printer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class Printer {
	private static PrintService impressora;

	public Printer() {
		detectaImpressoras();
	}

	// O metodo verifica se existe impressora conectada e a
	// define como padrao.
	public void detectaImpressoras() {
		try {
			DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
			PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);

			for (PrintService p : ps) {
				System.out.println("Impressora encontrada: " + p.getName());

				if (p.getName().equalsIgnoreCase("HP DeskJet 2600 series")) {
					impressora = p;
				}
				/*
				 * if (p.getName().contains("Text") || p.getName().contains("Generic")) {
				 * System.out.println("Impressora Selecionada: " + p.getName()); impressora = p;
				 * break; }
				 */
			}
			System.out.println("Impressora Definida: " + impressora.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized boolean imprime(File out) {

		// se nao existir impressora, entao avisa usuario
		// senao imprime texto
		if (impressora == null) {
			String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
			System.out.println(msg);
		} else {
			try {
				DocPrintJob dpj = impressora.createPrintJob();
				InputStream stream = new FileInputStream(out);
				DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				Doc doc = new SimpleDoc(stream, flavor, null);
				dpj.print(doc, null);
				return true;
			} catch (PrintException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public synchronized boolean imprime(String text ) {

		// se nao existir impressora, entao avisa usuario
		// senao imprime texto
		if (impressora == null) {
			String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
			System.out.println(msg);
		} else {
			try {
				DocPrintJob dpj = impressora.createPrintJob();
				InputStream stream = new ByteArrayInputStream(text.getBytes());
				DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				Doc doc = new SimpleDoc(stream, flavor, null);
				dpj.print(doc, null);
				return true;
			} catch (PrintException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}

package services;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

public class RenameReceipt {
	
	private String newName;
	private String date;
	private String month;
	private String time;
	private String extension;
	public File rename(File file) {
		findDate(file.getName());
		findTime(file.getName());
		findExtension(file.getName());
		formatMonth(getMonth(date));
		this.newName = getDate() + " as " + getTime() + getExtension(); 
		String pathname;
		File oldFolder = new File(file.getParent()+ "\\" + "Comprovantes\\Velhos\\" + month);
		oldFolder.mkdirs();
		File newFolder = new File(file.getParent()+ "\\" + "Comprovantes\\Novos\\" + month);
		newFolder.mkdirs();
		File createPdfFolder = new File(file.getParent()+ "\\" + "Comprovantes\\Comprovantes PDFs");
		createPdfFolder.mkdirs();
		File newFile = new File(oldFolder + "\\" + this.newName);
		file.renameTo(newFile);

		
		return newFile;
	}
	public String getMonth(String date) {
		DateTime datetime = new DateTime(date);
		return datetime.toString("MM");
	}
	public String formatMonth(String month) {
	    SimpleDateFormat monthParse = new SimpleDateFormat("MM");
	    SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
	    try {
	    	this.month = monthDisplay.format(monthParse.parse(month));
			return this.month;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
		}
	}
	
	public void findDate(String str) {
		int startDate = str.lastIndexOf("Image ");
		int endDate = str.lastIndexOf("at");
		str = str.substring(startDate + 6, endDate - 1);
		this.date  = str;
	}
	public void findTime(String str) {
		int startTime = str.lastIndexOf("at");
		int endTime = str.lastIndexOf(".");
		str = str.substring(startTime + 3, endTime);
		this.time  = str;
	}
	
	public void findExtension(String str) {
		int startExtension = str.lastIndexOf(".");
		str = str.substring(startExtension);
		this.extension  = str;
	}

	public String getDate() {
		return date;
	}


	public String getTime() {
		return time;
	}



	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNewName() {
		return newName = getDate() + " as " + getTime() + getExtension();
	}
	public String getMonth() {
		return month;
	}



	
	
}

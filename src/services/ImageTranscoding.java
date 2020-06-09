package services;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class ImageTranscoding {

	public void transcoding(String inputFile, String outputFile) {
		// read a jpeg from a inputFile
		File inFile = new File(inputFile);
		File outFile = new File(outputFile);
		try {
			BufferedImage bufferedImage = ImageIO.read(inFile);

			// write the bufferedImage back to outputFile
			ImageIO.write(bufferedImage, "png", outFile);

			// this writes the bufferedImage into a byte array called resultingBytes
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", byteArrayOut);
			
			byte[] resultingBytes = byteArrayOut.toByteArray();
			System.out.println(inFile.getName() + " Finalizado!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

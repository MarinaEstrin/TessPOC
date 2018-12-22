package com.javateer.examples.tess4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * A very simple class that simply puts to use the Tess4J solution for
 * Tesseract.
 */
public class TextImageReader {

	private Tesseract tesseract;

	public String readImageText(BufferedImage bufferedImage) {
		try {
			return tesseract.doOCR(bufferedImage);
		} catch (TesseractException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		try {
			Tesseract tesseract = new Tesseract();
			tesseract.setLanguage("eng+heb");

//			ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
//			String defaultCharacterWhitelist = resourceBundle.getString("default-character-whitelist");
//			tesseract.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-:.אבגדהוזחטיכסלמנעפצקרשתףץםן");

			/*
			 * Tesseract is looking for at least one data file like
			 * tessdata/eng.traineddata. Without this, an exception is raised that tessdata
			 * (for default english) is not found.
			 *
			 * During runtime, after this project is built by Maven, this path setting is
			 * not necessary because tessdata/* will be in the packaged classpath. However,
			 * during a JUnit test run, this path needs to be explicitly given to Tesseract,
			 * else it assumes tessdata/ is peer to src/ folder.
			 */
			tesseract.setDatapath("src/main/resources/");

			BufferedImage bufferedImage = ImageIO.read(new File("C:/Users/Imsi/Downloads/license.jpeg"));

			System.out.println(tesseract.doOCR(bufferedImage));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

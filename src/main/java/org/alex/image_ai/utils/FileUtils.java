package org.alex.image_ai.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class FileUtils {
	private static int counter = 0;

	public static final String IMAGE_RESOURCES_PATH = "src/main/resources/images";
	
	private FileUtils() {
		//only static functions, prevent instantiation
	}

	public static String readFile(String fileName) {
		try {
			return Files.readString(Path.of(fileName));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static boolean writeImageToFile(String imageData) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String fileName = String.format("image_%s_%d.png", timestamp, counter++);
		Path directory = Paths.get(IMAGE_RESOURCES_PATH);
		Path filePath = directory.resolve(fileName);
		try {
			byte[] bytes = Base64.getDecoder().decode(imageData);
			Files.write(filePath, bytes, StandardOpenOption.CREATE_NEW);
			System.out.printf("Saved %s to %s%n", fileName, IMAGE_RESOURCES_PATH);
			return true;
		} catch (IOException e) {
			throw new UncheckedIOException("Error writing prompt to file", e);
		}
	}

}

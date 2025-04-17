package org.alex.image_ai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.alex.image_ai.beans.Image64;
import org.alex.image_ai.beans.ImageRequest;
import org.alex.image_ai.beans.ImageResponse;
import org.alex.image_ai.beans.ImageSize;
import org.alex.image_ai.utils.FileUtils;
import org.alex.image_ai.utils.Settings;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Generate images from a prompt using OpenAI
 * 
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
public class GenerateImage {

	//https://platform.openai.com/docs/api-reference/images/create
	
	private static final String DALL_E_2 = "dall-e-2";
	//private static final String DALL_E_3 = "dall-e-3";
	
	private final Gson gson = new GsonBuilder().setPrettyPrinting()
			// convert from snake to camel case
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	public static void main(String[] args) throws InterruptedException {
		
		//(new GenerateImage()).test();
		//(new GenerateImage()).getImages("A photo realistic image of Juan Diego and Our Lady of Guadalupe. Juan Diego was a native.", 2);
		(new GenerateImage()).getImages("A silhouette image of Juan Diego and Our Lady of Guadalupe.", 2);
	}

	
	private void test() {
		String j = " {\"created\": 1589478378,"
				+ " \"data\": ["
				+ "  { \"b64_json\": \"https://...\" },"
				+ "  { \"b64_json\": \"https://...\" }"
				+ " ]"
				+ "}";
		ImageResponse ir = gson.fromJson(j, ImageResponse.class);
		System.out.println(ir);
	}

	public long getImages(String prompt, int numOfImages) throws InterruptedException {
		ImageRequest request = new ImageRequest(DALL_E_2, prompt, numOfImages, ImageSize.SMALL256X256.getSize(), "b64_json");
		ImageResponse response = makeRequest(request);
		return response.data().stream().map(Image64::b64Json).filter(FileUtils::writeImageToFile).count();
	}

	private ImageResponse makeRequest(ImageRequest request) throws InterruptedException {
		String key = Settings.getProperty("openai.key");
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://api.openai.com/v1/images/generations"))
				.header("Authorization", "Bearer %s".formatted(key))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request))).build();
		System.out.println("Headers Sent '" + httpRequest.headers() + "'");
		System.out.println("Sent request '" + gson.toJson(request) + "'");
		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println("rc=" + response.statusCode() + " " + response.body());
			System.out.flush();
			return gson.fromJson(response.body(), ImageResponse.class);
		} catch (InterruptedException e) {
			throw e;
		} catch (IOException e) {
			throw new RuntimeException("Error sending prompt prompt", e);
		}
	}

}

package org.alex.image_ai.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImageRequestTest {
	private final Gson gson = new GsonBuilder().setPrettyPrinting()
			// convert from snake to camel case
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	@Test
	public void testJson() {
		ImageRequest request = new ImageRequest("myMode", "hi", 10, ImageSize.SMALL256X256.getSize(), "b64_json");

		assertEquals("{\n"
				+ "  \"model\": \"myMode\",\n"
				+ "  \"prompt\": \"hi\",\n"
				+ "  \"n\": 10,\n"
				+ "  \"size\": \"256x256\",\n"
				+ "  \"response_format\": \"b64_json\"\n"
				+ "}",
				gson.toJson(request) );
	}

}

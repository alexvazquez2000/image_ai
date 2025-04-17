package org.alex.image_ai.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImageResponseTest {

	private final Gson gson = new GsonBuilder().setPrettyPrinting()
			// convert from snake to camel case
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	@Test
	public void testJson() {
		String j = " {\"created\": 1589478378,"
				+ " \"data\": ["
				+ "  { \"b64_json\": \"https://...\" },"
				+ "  { \"b64_json\": \"https://...\" }"
				+ " ]"
				+ "}";
		ImageResponse ir = gson.fromJson(j, ImageResponse.class);
		assertEquals("ImageResponse[createdTimestamp=null, data=[Image64[b64Json=https://...], Image64[b64Json=https://...]]]",
				ir.toString());
	}

}

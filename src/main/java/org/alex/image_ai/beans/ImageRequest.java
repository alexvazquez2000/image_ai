package org.alex.image_ai.beans
;

/**
 * Based on json on https://platform.openai.com/docs/api-reference/images/create
 * 
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
public record ImageRequest (String model, String prompt, Integer n, String size, String responseFormat) {

	/*
	 * https://platform.openai.com/docs/api-reference/images/create
	 *
	 * n - defaults to 1 -  it can be between 1 and 10 for dall-e-2,  but for dall-e-3, only n=1 is supported.
	 * 
	size -	string or null
	Optional
	Defaults to 1024x1024
	The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024 for dall-e-2. Must be one of 1024x1024, 1792x1024, or 1024x1792 for dall-e-3 models.
	*/
	
}

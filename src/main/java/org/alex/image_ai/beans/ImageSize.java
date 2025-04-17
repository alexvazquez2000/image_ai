package org.alex.image_ai.beans;

public enum ImageSize {
	SMALL256X256("256x256"),
	MED512X512("512x512"),
	LARGE1024X1024("1024x1024");

	//Must be one of 256x256, 512x512, or 1024x1024 for dall-e-2. 
	//Must be one of 1024x1024, 1792x1024, or 1024x1792 for dall-e-3 models.
	
	private String size;
	
	ImageSize(String imageSize) {
		this.size = imageSize;
	}

	/**
	 * @return the image size as text
	 */
	public String getSize() {
		return size;
	}
	
}

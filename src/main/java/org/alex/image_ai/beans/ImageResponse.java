package org.alex.image_ai.beans
;

import java.util.List;

/**
 * Based on json on https://platform.openai.com/docs/api-reference/images/create
 * 
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
public record ImageResponse (Long createdTimestamp, List<Image64> data) {
	
}

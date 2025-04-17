package org.alex.image_ai.beans
;

import java.util.List;

public record ImageResponse (Long createdTimestamp, List<Image64> data) {
	
}

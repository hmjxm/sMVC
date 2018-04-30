package org.inzy.framework.poi.word.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

import org.inzy.framework.poi.util.POIPublicUtil;
import org.inzy.framework.poi.word.entity.WordImageEntity;
import org.inzy.framework.poi.word.entity.params.ExcelListEntity;

public class ParseWordUtil {
	public static Object getRealValue(String currentText,
			Map<String, Object> map) throws Exception {
		String params = "";
		while (currentText.indexOf("{{") != -1) {
			params = currentText.substring(currentText.indexOf("{{") + 2,
					currentText.indexOf("}}"));
			Object obj = getParamsValue(params.trim(), map);

			if (((obj instanceof WordImageEntity)) || ((obj instanceof List))
					|| ((obj instanceof ExcelListEntity))) {
				return obj;
			}
			currentText = currentText.replace("{{" + params + "}}",
					obj.toString());
		}

		return currentText;
	}

	private static Object getParamsValue(String params, Map<String, Object> map)
			throws Exception {
		if (params.indexOf(".") != -1) {
			String[] paramsArr = params.split("\\.");
			return getValueDoWhile(map.get(paramsArr[0]), paramsArr, 1);
		}
		return map.containsKey(params) ? map.get(params) : "";
	}

	public static Object getValueDoWhile(Object object, String[] paramsArr,
			int index) throws Exception {
		if (object == null) {
			return "";
		}
		if ((object instanceof WordImageEntity)) {
			return object;
		}
		if ((object instanceof Map)) {
			object = ((Map) object).get(paramsArr[index]);
		} else {
			object = POIPublicUtil.getMethod(paramsArr[index],
					object.getClass()).invoke(object, new Object[0]);
		}
		return index == paramsArr.length - 1 ? object : object == null ? ""
				: getValueDoWhile(object, paramsArr, ++index);
	}

	public static Object[] getIsAndType(WordImageEntity entity)
			throws Exception {
		Object[] result = new Object[2];
		String type;
		if (entity.getType().equals(WordImageEntity.URL)) {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").toURI().getPath()
					+ entity.getUrl();
			path = path.replace("WEB-INF/classes/", "");
			path = path.replace("file:/", "");
			BufferedImage bufferImg = ImageIO.read(new File(path));
			ImageIO.write(
					bufferImg,
					entity.getUrl().substring(entity.getUrl().indexOf(".") + 1,
							entity.getUrl().length()), byteArrayOut);
			result[0] = byteArrayOut.toByteArray();
			type = entity.getUrl().split("/.")[(entity.getUrl().split("/.").length - 1)];
		} else {
			result[0] = entity.getData();
			type = POIPublicUtil.getFileExtendName(entity.getData());
		}
		result[1] = getImageType(type);
		return result;
	}

	private static Integer getImageType(String type) {
		if ((type.equalsIgnoreCase("JPG")) || (type.equalsIgnoreCase("JPEG"))) {
			return Integer.valueOf(5);
		}
		if (type.equalsIgnoreCase("GIF")) {
			return Integer.valueOf(8);
		}
		if (type.equalsIgnoreCase("BMP")) {
			return Integer.valueOf(8);
		}
		if (type.equalsIgnoreCase("PNG")) {
			return Integer.valueOf(6);
		}
		return Integer.valueOf(5);
	}
}
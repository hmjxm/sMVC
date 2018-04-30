package org.inzy.framework.poi.cache.manager;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.inzy.framework.poi.util.POIPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FileLoade {
	private static final Logger logger = LoggerFactory
			.getLogger(FileLoade.class);

	public byte[] getFile(String url) {
		FileInputStream fileis = null;
		ByteArrayOutputStream baos = null;
		try {
			String path = POIPublicUtil.getWebRootPath(url);
			fileis = new FileInputStream(path);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[2048];
			int len;
			while ((len = fileis.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileis != null)
					fileis.close();
				if (fileis != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.error(fileis + "这个路径文件没有找到,请查询");
		return null;
	}
}
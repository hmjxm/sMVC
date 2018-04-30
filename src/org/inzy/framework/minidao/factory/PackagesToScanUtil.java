package org.inzy.framework.minidao.factory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.log4j.Logger;

public class PackagesToScanUtil {
	private static final Logger logger = Logger
			.getLogger(PackagesToScanUtil.class);

	private static final String SUB_PACKAGE_SCREEN__SUFFIX = ".*";

	private static final String SUB_PACKAGE_SCREEN__SUFFIX_RE = ".\\*";

	public static Set<Class<?>> getClasses(String pack) {
		boolean recursive = false;
		String[] packArr = new String[0];

		if (pack.lastIndexOf(".*") != -1) {
			packArr = pack.split(".\\*");
			if (packArr.length > 1) {
				pack = packArr[0];
				for (int i = 0; i < packArr.length; i++) {
					packArr[i] = packArr[i].replace(".*".substring(1), "");
				}
			} else {
				pack = pack.replace(".*", "");
			}
			recursive = true;
		}

		Set<Class<?>> classes = new LinkedHashSet();

		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');

		try {
			Enumeration<URL> dirs = Thread.currentThread()
					.getContextClassLoader().getResources(packageDirName);

			while (dirs.hasMoreElements()) {
				URL url = (URL) dirs.nextElement();

				String protocol = url.getProtocol();

				if ("file".equals(protocol)) {
					logger.debug("-------------- file类型的扫描 ----------------");

					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

					findAndAddClassesInPackageByFile(packageName, packArr,
							filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					findAndAddClassesInPackageByJarFile(packageName, packArr,
							url, packageDirName, recursive, classes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	private static void findAndAddClassesInPackageByFile(String packageName,
			String[] packArr, String packagePath, final boolean recursive,
			Set<Class<?>> classes) {
		File dir = new File(packagePath);

		if ((!dir.exists()) || (!dir.isDirectory())) {
			return;
		}

		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return ((recursive) && (file.isDirectory()))
						|| (file.getName().endsWith(".class"));
			}
		});
		File[] arrayOfFile1;
		int j = (arrayOfFile1 = dirfiles).length;
		for (int i = 0; i < j; i++) {
			File file = arrayOfFile1[i];

			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(), packArr,
						file.getAbsolutePath(), recursive, classes);
			} else {
				String className = file.getName().substring(0,
						file.getName().length() - 6);

				try {
					String classUrl = packageName + '.' + className;

					if (classUrl.startsWith(".")) {
						classUrl = classUrl.replaceFirst(".", "");
					}

					boolean flag = true;
					if (packArr.length > 1) {
						for (int k = 1; k < packArr.length; k++) {
							if (classUrl.indexOf(packArr[k]) <= -1) {
								flag = false;
							} else {
								flag = flag;
							}
						}
					}

					if (flag) {
						classes.add(Thread.currentThread()
								.getContextClassLoader().loadClass(classUrl));
					}
				} catch (ClassNotFoundException e) {
					logger.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	private static void findAndAddClassesInPackageByJarFile(String packageName,
			String[] packArr, URL url, String packageDirName,
			boolean recursive, Set<Class<?>> classes) {
		logger.debug("------------------------ jar类型的扫描 ----------------------");

		try {
			JarFile jar = ((JarURLConnection) url.openConnection())
					.getJarFile();

			Enumeration<JarEntry> entries = jar.entries();

			while (entries.hasMoreElements()) {
				JarEntry entry = (JarEntry) entries.nextElement();
				String name = entry.getName();

				if (name.charAt(0) == '/') {
					name = name.substring(1);
				}

				if (name.startsWith(packageDirName)) {
					int idx = name.lastIndexOf('/');

					if (idx != -1) {
						packageName = name.substring(0, idx).replace('/', '.');
					}

					if ((idx != -1) || (recursive)) {
						if ((name.endsWith(".class")) && (!entry.isDirectory())) {
							String className = name
									.substring(packageName.length() + 1,
											name.length() - 6);

							try {
								boolean flag = true;
								if (packArr.length > 1) {
									for (int i = 1; i < packArr.length; i++) {
										if (packageName.indexOf(packArr[i]) <= -1) {
											flag = false;
										} else {
											flag = flag;
										}
									}
								}

								if (flag) {
									classes.add(Class.forName(packageName + '.'
											+ className));
								}
							} catch (ClassNotFoundException e) {
								logger.error("添加用户自定义视图类错误 找不到此类的.class文件");
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (IOException e) {
			logger.error("在扫描用户定义视图时从jar包获取文件出错");
			e.printStackTrace();
		}
	}
}
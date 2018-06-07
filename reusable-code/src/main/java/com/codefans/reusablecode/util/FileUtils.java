package com.codefans.reusablecode.util;

import java.io.*;
import java.util.*;

public class FileUtils {

	public String file;
	private FileWriter fw;

	public FileUtils(String file) throws IOException {
		this.file = file;
		fw = new FileWriter(new File(file));
	}

	public FileUtils() {
		// TODO Auto-generated constructor stub
	}

	public void appendLine(String line) throws IOException {
		if (line != null && line.trim().length() > 0) {
			fw.append(line).append("\r\n");
		}
	}

	public void close() throws IOException {
		if (fw != null) {
			fw.flush();
			fw.close();
			fw = null;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtils util = new FileUtils();
		// util.printPropertiesFile();

		// util.setClassPath();
		// util.fileProperties();

		// util.copy();

		System.out.println(File.separator);
		System.out.println(File.pathSeparator);

		// util.fileCount();

//		delete("D:\\GitHub\\test-utf8\\temp\\performanceinfindstr\\multiPaths");

//		readFile();
		
//		String dirPath = "C:\\Users\\Administrator\\AppData\\Local\\YNote\\data\\caiszf@163.com\\";
//		String destPath = "C:\\Users\\Administrator\\AppData\\Local\\YNote\\data\\caiszf@163.com_allFiles\\";
//		util.copyAllFilesToPath(dirPath, destPath);
		
//		String filePath = "D:/tmp/收入确认表(新).txt";
//		String newFilePath = "D:/tmp/收入确认表(新改)_out.txt";
		String filePath = "D:/tmp/erpSettlement4154.txt";
//		String newFilePath = "D:/tmp/excel.txt";
		String newFilePath = "D:/tmp/finSettleDetails4156.txt";
		util.compareFile(filePath, newFilePath);
		
//		String filePath = "D:/tmp/寄卖商品撤卖报表.txt";
//		String newFilePath = "D:/tmp/寄卖商品收货报表.txt";
//		String filePath = "D:/tmp/寄卖商品收货报表.txt";
//		String newFilePath = "D:/tmp/寄卖售出后结算.txt";
//		util.compareFileColumn(filePath, newFilePath);
		
//		String filePath = "D:/tmp/寄卖售出后结算.txt";
//		String toAdd = "D:/tmp/收货地点.txt";
//		util.fileAppend(filePath, toAdd);
		
		
		
	}
	
	public void fileAppend(String sourceFilePath, String toAddFilePath, String destFilePath) {
		List<String> sourceList = this.fileToList(file);
		List<String> toAddList = this.fileToList(toAddFilePath);
		List<String> resList = new ArrayList<String>();
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < sourceList.size(); i ++) {
			str.append(sourceList.get(i)).append(toAddList.get(i));
			resList.add(str.toString());
		}
		print(resList);
		writeTo(destFilePath, resList);
		
	}
	
	/**
	 * 比较源文件中的第三列是否包含目标文件的第三列
	 * @param sourcePath
	 * @param destPath
	 */
	public void compareFileColumn(String sourcePath, String destPath) {
		List<String> sourceList = this.fileToList(sourcePath);
		List<String> destList = this.fileToList(destPath);
		
		Map<String, String> sourceMap = new HashMap<String, String>();
		String[] arr = null;
		String key = "";
		for(String str : sourceList) {
			arr = str.split(",");
			key = arr[0] + "," + arr[1];
			sourceMap.put(key, arr[2]);
		}
		
		String value = "";
		for(String str : destList) {
			arr = str.split(",");
			key = arr[0] + "," + arr[1];
			value = sourceMap.get(key);
			if(value != null && !value.trim().equals("")) {
				if(value.contains(arr[2])) {
					continue;
				} else {
					System.out.println(str + "," + value);
				}
			} else {
//				System.out.println(str);
			}
		}
		
	}
	
	/**
	 * 求两个文件的差集、交集、并集,文件每行一条记录
	 * @param sourceFilePath
	 * @param destFilePath
	 */
	public void compareFile(String sourceFilePath, String destFilePath) {
		
		List<String> sourceList = this.fileToList(sourceFilePath);
		List<String> destList = this.fileToList(destFilePath);
		
		System.out.println(sourceFilePath + ":共" + sourceList.size() + "行.");
		System.out.println(destFilePath + ":共" + destList.size() + "行.");
		
		List<String> result = new ArrayList<String>();
		result.addAll(sourceList);
		result.removeAll(destList);
		System.out.println("只存在于[" + sourceFilePath + "]文件中,共:" + result.size() + "个:");
		print(result);
		
		result.clear();
		result.addAll(destList);
		result.removeAll(sourceList);
		System.out.println("只存在于[" + destFilePath + "]文件中,共:" + result.size() + "个:");
		print(result);
		
		result.clear();
		result.addAll(destList);
		result.retainAll(sourceList);
		System.out.println("两个文件的交集,共:" + result.size() + "个:");
//		print(result);
		
		result.clear();
		result.addAll(destList);
		result.addAll(sourceList);
		System.out.println("两个文件的并集,共:" + result.size() + "个:");
//		print(result);
		
	}
	
	public List<String> fileToList(String filePath) {
		List<String> list = new ArrayList<String>();
		Scanner sc = null;
		try {
			File file = new File(filePath);
			sc = new Scanner(new FileInputStream(file));
			String line = "";
			while(sc.hasNextLine()) {
				line = sc.nextLine().trim();
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(sc);
		}
		return list;
	}
	
	public static void print(List<String> list) {
		for(String str : list) {
			System.out.println(str);
		}
	}
	
	public void writeTo(String outFilePath, List<String> list) {
		File outFile = new File(outFilePath);
		BufferedWriter bw = null;
		try {
			if(!outFile.exists()) {
				boolean flag = outFile.createNewFile();
				if(!flag) {
					throw new RuntimeException("文件创建失败,[" + outFilePath + "]");
				}
			}
			bw = new BufferedWriter(new FileWriter(outFile));
			for(String str : list) {
				System.out.println(str);
				bw.write(str);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.close(bw);
		}
	}
	
	public void close(Scanner sc) {
		if(sc != null) {
			sc.close();
			sc = null;
		}
	}
	public void close(InputStream is, OutputStream os) {
		this.close(is);
		this.close(os);
	}
	public void close(InputStream is) {
		try {
			if(is != null) {
				is.close();
				is = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close(OutputStream os) {
		try {
			if(os != null) {
				os.flush();
				os.close();
				os = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close(Reader r, Writer w) {
		this.close(r);
		this.close(w);
	}
	public void close(Reader r) {
		try {
			if(r != null) {
				r.close();
				r = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close(Writer w) {
		try {
			if(w != null) {
				w.flush();
				w.close();
				w = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void compareFile() {
		try {
//			String filePath = "D:/tmp/收入确认表(新).txt";
//			String newFilePath = "D:/tmp/收入确认表(新改)_out.txt";
			String filePath = "D:/tmp/收入确认表(新改)_out.txt";
			String newFilePath = "D:/tmp/收入确认表(新).txt";
			File file = new File(filePath);
			File newFile = new File(newFilePath);
			Scanner sc = new Scanner(new FileInputStream(file));
			Scanner newSc = new Scanner(new FileInputStream(newFile));
			String line = "";
			String newLine = "";
			boolean found = false;
			int notFoundCount = 0;
			System.out.println("compare begin:");
			long dateBegin = System.currentTimeMillis();
			
			List<String> arr = new ArrayList<String>();
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				arr.add(line.trim());
			}
			
			while(newSc.hasNextLine()) {
				newLine = newSc.nextLine();
				newLine = newLine.trim();
				
				found = false;
				if(arr.contains(newLine)) {
					found = true;
				}
				if(!found) {
					notFoundCount++;
					System.out.println(newLine + " not found.");
				}
				
			}
			
			long dateEnd = System.currentTimeMillis();
			System.out.println("compare end, cost:[" + (dateEnd - dateBegin) / 1000 + "s]");
			System.out.println("notFoundCount:" + notFoundCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void readLineAndWriteToNewFile() {
		BufferedWriter bw = null;
		try {
			
			String outFilePath = "D:/tmp/收入确认表(新改)_out.txt";
			
//			String filePath = "D:/tmp/收入确认表(新).txt";
			String filePath = "D:/tmp/收入确认表(新改).txt";
			File file = new File(filePath);
			Scanner sc = new Scanner(new FileInputStream(file));
			
			File outFile = new File(outFilePath);
			if(!outFile.exists()) {
				outFile.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(outFile));
			
			String line = "";
			int columnIndex = 2;
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				if(line != null && line.indexOf(",") >= 0) {
					String[] arr = line.split(",");
					if(arr.length == 3) {
//						System.out.println(arr[columnIndex]);
					} else {
//						System.out.println("barCode");
						line += "bar_code";
					}
					bw.write(line);
					bw.newLine();
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.flush();
					bw.close();
					bw = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void readLineFromFile() {
		
		try {
			
//			String filePath = "D:/tmp/收入确认表(新).txt";
			String filePath = "D:/tmp/收入确认表(新改).txt";
			File file = new File(filePath);
			Scanner sc = new Scanner(new FileInputStream(file));
			
			String line = "";
			int columnIndex = 2;
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				if(line != null && line.indexOf(",") >= 0) {
					String[] arr = line.split(",");
					if(arr.length == 3) {
						System.out.println(arr[columnIndex]);
					} else {
						System.out.println("barCode");
					}
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
	}
	
	public void copyAllFilesToPath(String sourcePath, String destPath) {
		
		copyRecursion(sourcePath, destPath);
		
	}
	
	public void copyRecursion(String sourcePath, String destPath) {
		
		File dir = new File(sourcePath);
		if(dir.isDirectory()) {
			File[] files = dir.listFiles();
			for(int i = 0; i < files.length; i ++) {
				File file = files[i];
				if(file.isDirectory()) {
					copyRecursion(sourcePath + file.getName() + File.separator, destPath);
				} else {
					System.out.println(file.getAbsolutePath());
					copy(file.getAbsolutePath(), destPath);
				}
			}
		} else {
			System.out.println(dir.getAbsolutePath());
			copy(dir.getAbsolutePath(), destPath);
		}
		
	}
	

	public static void readFile() {
		try {
			File file = new File("d:/aa.txt");
			FileReader fr = new FileReader(file);
//			fr.r
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	public void fileCount() {
		String path = "\\\\10.0.0.56\\temp\\streamming\\email";
		Set<String> uidKeys = new HashSet<String>();
		long start = System.currentTimeMillis();
		long freeMemory = Runtime.getRuntime().freeMemory();

		this.getAllUidKey(path, uidKeys);

		long end = System.currentTimeMillis();
		long endFreeMemory = Runtime.getRuntime().freeMemory();

		System.out.println("total cost time:[" + (end - start) / 1000 + "]s");
		System.out.println("total cost memory:[" + (freeMemory - endFreeMemory) / 1024 + "]KB");

	}

	public void fileCountMultiThread() {

	}

	public void getAllUidKey(String path, Set<String> uidKeys) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				String name = "";
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file.isDirectory()) {
						getAllUidKey(path + File.separator + file.getName(), uidKeys);
					} else {
						name = file.getName();
						name = name.substring(0, name.indexOf("."));
						uidKeys.add(name);
					}
				}
			}
		}
	}

	public void fileProperties() {
		File file = new File("D:\\Media\\������Ļ.rmvb");
		long length = file.length();
		System.out.println("File.length: " + length / 1024f);

		try {
			RandomAccessFile raf = new RandomAccessFile("D:\\Media\\������Ļ.rmvb", "r");
			long len = raf.length();
			System.out.println("RandomAccessFile.length: " + len / 1024f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("long to int: " + (int) 124l);
	}

	public static void move(Object[] files, String sourcePath, String destPath) {
		File file = new File(destPath);
		if (!file.exists()) {
			file.mkdir();
		}
		copy(files, sourcePath, destPath);
		delete(files, sourcePath);
	}

	public static void delete(Object[] names, String p) {
		if (names != null) {
			String path = "";
			File file = null;
			for (Object name : names) {
				path = p + File.separator + String.valueOf(name);
				file = new File(path);
				file.deleteOnExit();
				System.out.println("file [" + path + "] is deleted");
			}
			System.out.println(names.length + " files deleted");
		}
	}

	public void copy() {
		String jarStr = "C:/Program Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/csdk.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/exchange.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/Notes.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-codec.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/hessian-3.1.6.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-3.0.1-FINAL-20070705.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-contrib-3.0.1-FINAL-20070705.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.0.1-FINAL-20070705.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-all-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-web-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;";
		jarStr = jarStr.replace("C:/Program Files/MessageSolution/MEAHE/eas/lib/", "");
		String[] fileNames = jarStr.split(";");

		// String sourcePath = "D:\\dev\\Hosting-RB-1.2.x\\build\\lib";
		// String destPath = sourcePath + "\\destLib";
		String sourcePath = "C:\\hostinglib\\lib";
		String destPath = sourcePath + "\\destLib";

		// copy(fileNames, sourcePath, destPath);

		String filePath01 = "C:\\Users\\Administrator\\Downloads\\BaiduYun_3.8.0.exe";
		String filePath02 = "C:\\Users\\Administrator\\Downloads\\BaiduYun_3.8.0_new.exe";
		File file01 = new File(filePath01);
		File file02 = new File(filePath02);
		copy(file01, file02);

	}

	public static void copy(Object[] files, String sourcePath, String destPath) {
		String temp = "";
		String temp2 = "";
		String file = null;
		File sf = null;
		File df = null;
		try {
			for (int i = 0; i < files.length; i++) {
				file = String.valueOf(files[i]);
				temp = sourcePath + File.separator + file;
				System.out.println(temp);
				sf = new File(sourcePath + File.separator + file);
				temp2 = file.substring(file.lastIndexOf(File.separator) + 1);
				temp2 = destPath + File.separator + temp2;
				df = new File(temp2);
				if (!df.exists()) {
					df.createNewFile();
				}
				copy(sf, df);
				sf = null;
				df = null;
			}
			System.out.println(files.length + " files copied");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copy(Collection<String> files, String destPath) {
		if (files != null) {
			Iterator<String> iter = files.iterator();
			while (iter.hasNext()) {
				copy(iter.next(), destPath);
			}
		} else {
			System.out.println("list is null");
		}
	}

	public static void copy(String file, String path) {
		try {
			
			File dir = new File(path);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String fileName = file.substring(file.lastIndexOf(File.separator) + 1);
			String destFile = path + File.separator + fileName;
			File source = new File(file);
			File dest = new File(destFile);
			if (!dest.exists()) {
				dest.createNewFile();
			}
			copy(source, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copy(File file, File newFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);

			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			fos = new FileOutputStream(newFile);
			copy(fis, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
					fis = null;
				}
				if (fos != null) {
					fos.flush();
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void copy(FileInputStream fis, FileOutputStream fos) {
		int n = 0;
		byte[] bytes = new byte[1024];
		try {
			while ((n = fis.read(bytes)) != -1) {
				fos.write(bytes, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setClassPath() {
		// String lib = "D:\\csz\\��Ŀ\\Hosting\\SNMP��ֲ\\lib\\";
		// String lib = "E:/MessageSolution/MEAHE/eas/lib/";
		// String lib = "D:\\dev\\Hosting-RB-1.2.x\\build\\lib\\";
		// String lib =
		// "D:/csz/��Ŀ/���¼�¼/��ӹ��ܡ�����SNMP��ֲ��Hosting/Hosting��ֲ_��װ����/lib";

		// String lib = "D:/dev/Hosting-RB-1.2.x/build/lib/";
		String lib = "C:/hostinglib/lib/";
		// String lib =
		// "D:/csz/libs/apache/Axis/axis2-1.6.2-bin/axis2-1.6.2/lib";
		// String lib =
		// "C:/apache-tomcat-6.0.35-windows-x64/apache-tomcat-6.0.35/webapps/webservicetest/WEB-INF/lib";

		File file = new File(lib);
		StringBuilder sb = new StringBuilder("");
		int count = 0;
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.getName().endsWith(".jar")) {
					count++;
					// System.out.println(";%LIB_PATH%/" + f.getName());
					// sb.append(lib + f.getName() + ";");
					sb.append("C:/Program Files/MessageSolution/MEAHE/eas/lib/" + f.getName() + ";");
					// sb.append(f.getName() + ";");
					// System.out.println("C:/Program
					// Files/MessageSolution/MEAHE/eas/lib/" + f.getName());
				}
				if (f.getName().startsWith("eas")) {
					System.out.println(f.getName());
				}
			}
			System.out.println(sb.toString());
			System.out.println("共: " + count + "个");
		}
	}

	public void format() {
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test.properties";
		String path2 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "new_test.properties";
		String path3 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "repeat.txt";
		File file = new File(path);
		File newFile = new File(path2);
		File repeat = new File(path3);
		try {
			Scanner sc = new Scanner(file);
			Set set = new HashSet();
			while (sc.hasNextLine()) {
				String temp = sc.nextLine();
				String[] arr = temp.split("=");
				if (!set.contains(arr[0].trim())) {
					set.add(temp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void write(File file, String cnt) {

	}

	public void printPropertiesFile() {
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test.properties";
		System.out.println("path: " + path);

		try {
			InputStream is = new FileInputStream(new File(path));
			Properties p = new Properties();
			p.load(is);

			Set set = p.entrySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				System.out.println("name: " + entry.getKey() + ", value: " + entry.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// load conf or properties file, for debug
	public static Properties load(String path) {
		Properties prop = new Properties();
		try {
			File file = new File(path);
			InputStream is = new FileInputStream(file);
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	// read string from file, for debug
	public static String fileToStr(String path) {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				sb.append(sc.nextLine());
			}
			sc.close();
			sc = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void p(Object o) {
		System.out.println(o);
	}

	public static int fileCount(String path, String suffix) {
		File file = new File(path);
		int total = 0;
		File[] files = null;
		String name = null;
		if (file.isDirectory()) {
			files = file.listFiles();
			for (File f : files) {
				name = f.getName();
				if (name.endsWith(suffix)) {
					total++;
				}
			}
		}
		System.out.println("path: [" + path + "] has " + total + " [" + suffix + "] file");
		return total;
	}

	public static void delete(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					delete(path + File.separator + f.getName());
				} else {
					boolean flag = f.delete();
					if (!flag) {
						System.out.println("fail to delete file:[" + f.getAbsolutePath() + "]");
					} else {
						System.out.println("file delete success!");
					}
				}
			}
		}
	}

}

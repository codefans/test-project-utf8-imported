package com.codefans.reusablecode.util;

import java.io.*;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import org.apache.commons.io.FileUtils;

//import com.sun.mail.util.BASE64DecoderStream;
//import com.sun.mail.util.BASE64EncoderStream;

public class Base64FileDecoderUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Base64FileDecoderUtil decoder = new Base64FileDecoderUtil();

		// File srcFile = new
		// File("C:\\Users\\Administrator\\Desktop\\abc.docx");
		// File destFile = new
		// File("C:\\Users\\Administrator\\Desktop\\GetAttachmentResponse_Content.txt");
		// decoder.encodeBase64File(srcFile, destFile);

		File srcFile = new File("C:\\Users\\Administrator\\Desktop\\GetAttachmentResponse_Content.txt");
		File destFile = new File("C:\\Users\\Administrator\\Desktop\\abc_decoded.docx");
		// decoder.decoderBase64File(srcFile, destFile);

		// String str =
		// "UmFyIRoHAM+QcwAADQAAAAAAAAARUHQgkC8AFgMAAGYEAAACsff4nE14+kAdMwoAIAAAAHN"
		// +
		// "vdXJjZS50eHQA8OboSRHdUQyM082BV79qt8HfZs8ynTK3ycFRGNjcghIGkCAYCgvixJCSMRprcZo"
		// +
		// "yVu7CpCSEYDcHKlMZBizEsum4idU8CXQqpcy+Zd85nMXjl/HL5fLtXar9X/p/+E2eedTXmfv+2tF"
		// +
		// "koInCfzIfUzg2XD08ybjzO+LhkGdvNTevIb7VdVINMZ9UJuzo1RtRdS1wIdS3X2tX0vZRZ5JiRpl"
		// +
		// "UN/PyV7XeyC+HSTKOvXeHPc8QBaTtjjP05bmX5Zcr+fKZvBoq4KXynMP5Tlp6dHsfPXOZxx1kL/c"
		// +
		// "80s8KKPZXYjHRT/ggR3kgWGSA7mCphHtzAwlDFNtwC+7ss5t7QBU7KrKhwrfOJSB4pAXMezxWge4"
		// +
		// "74pxMLVutsmRcwZh6qStuV75bjPH9FZFHXrXIwDamk9wl98Gp+SlS9kDak2pGA1fAISFKVsMLNHz"
		// +
		// "qzClfcfjrMTuUn1B7vbu6XN6VfrjLWdA4NyFuqr67OiMSizpa826HCm4UNzEyn4spn48Ysj/qBek"
		// +
		// "bXUaxfpYMDi4Ybwffa+sCxcPXiDhz9FENIV568G48MGY0MTyesuWFAVgQPuTpLA5pbrIzs5JxxT6"
		// +
		// "CLPdKfLcwW+pI2yrPugxGondMq+KEMlwYu+vMdsBEyhnHweOanRtF4Aeaq7mKKgjOTQ0mxT+UiQz"
		// +
		// "waQSzh26836IqK4NJD/daDljKFA33NCrMP5rnPQeAlq+T+BQog+gO07it/5Q+MIBm8XC9ataZy+G"
		// +
		// "LHsQnH1XHiPNxQJri4pcR4vQW947byhzTwOa9JcwWMx29sbPrdRoX0nZ30XtdwVJvE7eQym/A9ep"
		// +
		// "hrvQOv/1qX2w4De+8OJLiNqsKyLO8RGx7wRxFfuUWK7udQmV5aKpRLDat1b2zk1LYz4Dre8ceSoz"
		// +
		// "vo/2Is3JE02oQn8detvXmQbQ+pC4BU3Zv9J+3jPctnLDmPNiLKbTwm8tjADE9DilORJH8GIWNids"
		// +
		// "3Df/dsARFVC6CyS8pf4ibrbKRWYj0RxriGR7FUuamH4v7mBykrcOMaE1kLlHRxSfCXSd5tMihvp4ucJKa37LEPXsAQAcA";
		// File decode = new File("C:/source_decode.rar");

		String str = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAByElEQVQ4jaWTv2sTYRjHP3dJz"
				+ "Jk2Q6RoMzQi6HCiCFdKJCgiVHFQkEIp1Eymgz8G5y7i4uAiiCCoiAoqIkTyBwgOSrAEQ0HaKuhwSaV"
				+ "NaKNJo+auyT0OodFrLgj2Mz7P+/2+3/fHo+iGKWwBP8D8+9h/ifcPF1C9Gq9e/+TQ4SLpTB2Auw9qX"
				+ "LteoeX0SLCZFy/rrK8Lj56uEY36uXXnOwCJuMbJ0ZBrrStB5Vt7i1CoXd4RUVlaanb6G3XPBPlZi2S"
				+ "qRHxE4+p0BC2ocG4iTGzIz0zO4uCBbezbGyA70yAR1/446IYpIiI3blZEN0zRDVM+f7HFi6MnFkU3T"
				+ "HnyvCYiIrphSidTdLAdRgsqDAz4mFuwGZtcZjy5zOLXJg1LWFltATC/YHcfYXIiTCSismd3gGrV4fy"
				+ "FMmv19p1MXS7z7OEgibhGLm8xejzUbeBT4fSpPlZWWyRTpY4YoFBscvFKmcf3d6EFld6vUK05JFMlC"
				+ "sUmm/kwZzN1qewy7jJIZ+qe4g3ysxbpzI/eBoGAO54XjuMeHddPPHumjzfZX7zNNogN+Tl2ZDs+H7z"
				+ "LWXz8ZDMyHGR8rL+3Qbhf5d7tnf9M8TfKVsf5N9mtvVgM6ArFAAAAAElFTkSuQmCC";
//		File decode = new File("C:/baidulogo.png");
		str = "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";
		File decode = new File("H:/274500.jpg");
//		decoder.str2file(str, decode);
		
		decoder.file2str("C:\\Users\\Sean\\Downloads\\奇色视频地址发布器二维码版_qiseqr.zip");

	}

	public void encodeBase64File(File sourceFile, File destFile) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(sourceFile));

			bos = new BufferedOutputStream(new BASE64EncoderStream(new FileOutputStream(destFile)));

			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				if (bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}

	public void decoderBase64File(File source, File dest) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new BASE64DecoderStream(new FileInputStream(source)));

			bos = new BufferedOutputStream(new FileOutputStream(dest));

			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				if (bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}

	/*
	 * UmFyIRoHAM+QcwAADQAAAAAAAAARUHQgkC8AFgMAAGYEAAACsff4nE14+
	 * kAdMwoAIAAAAHNvdXJj
	 * ZS50eHQA8OboSRHdUQyM082BV79qt8HfZs8ynTK3ycFRGNjcghIGkCAYCgvixJCSMRprcZoyVu7C
	 * pCSEYDcHKlMZBizEsum4idU8CXQqpcy+Zd85nMXjl/HL5fLtXar9X/p/+E2eedTXmfv+
	 * 2tFkoInC
	 * fzIfUzg2XD08ybjzO+LhkGdvNTevIb7VdVINMZ9UJuzo1RtRdS1wIdS3X2tX0vZRZ5JiRplUN
	 * /Py
	 * V7XeyC+HSTKOvXeHPc8QBaTtjjP05bmX5Zcr+fKZvBoq4KXynMP5Tlp6dHsfPXOZxx1kL/
	 * c80s8K KPZXYjHRT/ggR3kgWGSA7mCphHtzAwlDFNtwC+
	 * 7ss5t7QBU7KrKhwrfOJSB4pAXMezxWge474pxM
	 * LVutsmRcwZh6qStuV75bjPH9FZFHXrXIwDamk9wl98Gp+
	 * SlS9kDak2pGA1fAISFKVsMLNHzqzClf
	 * cfjrMTuUn1B7vbu6XN6VfrjLWdA4NyFuqr67OiMSizpa826HCm4UNzEyn4spn48Ysj/
	 * qBekbXUax fpYMDi4Ybwffa+
	 * sCxcPXiDhz9FENIV568G48MGY0MTyesuWFAVgQPuTpLA5pbrIzs5JxxT6CLPdK
	 * fLcwW+pI2yrPugxGondMq+KEMlwYu+vMdsBEyhnHweOanRtF4Aeaq7mKKgjOTQ0mxT+
	 * UiQzwaQSz
	 * h26836IqK4NJD/daDljKFA33NCrMP5rnPQeAlq+T+BQog+gO07it/5Q+MIBm8XC9ataZy+
	 * GLHsQn
	 * H1XHiPNxQJri4pcR4vQW947byhzTwOa9JcwWMx29sbPrdRoX0nZ30XtdwVJvE7eQym/
	 * A9ephrvQO
	 * v/1qX2w4De+8OJLiNqsKyLO8RGx7wRxFfuUWK7udQmV5aKpRLDat1b2zk1LYz4Dre8ceSozvo
	 * /2I s3JE02oQn8detvXmQbQ+pC4BU3Zv9J+
	 * 3jPctnLDmPNiLKbTwm8tjADE9DilORJH8GIWNids3Df/d
	 * sARFVC6CyS8pf4ibrbKRWYj0RxriGR7FUuamH4v7mBykrcOMaE1kLlHRxSfCXSd5tMihvp4ucJKa
	 * 37LEPXsAQAcA
	 * 
	 * the string come from the base64 encode stream of file 'source.rar'
	 */
	public void str2file(String str, File file) {
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;

		bis = new BufferedInputStream(new BASE64DecoderStream(new ByteArrayInputStream(str.getBytes())));
		byte[] data = new byte[4096];

		try {
			
			if(!file.exists()) {
				boolean flag = file.createNewFile();
				if(!flag) {
					throw new RuntimeException("file:[" + file.getAbsolutePath() + "] create fail.");
				}
			}
			
			bos = new BufferedOutputStream(new FileOutputStream(file));
			int len = 0;
			while ((len = bis.read(data)) != -1) {
				bos.write(data, 0, len);
			}
			bos.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				
				if(bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}

	public String file2str(String filePath) {

		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		String base64Str = "";
		StringBuffer sb = null;
		OutputStream os = null;
		byte[] data = new byte[4096];

		try {
			
			File file = new File(filePath);
			File out = null;
			
			String fileName = file.getName();
			String opt = "";
			if(file.length() >= FileUtils.ONE_MB * 10) {
				opt = "file";
				System.out.println(fileName + ":");
				out = new File(filePath + "_out.txt");
				if(!out.exists()) {
					boolean flag = out.createNewFile();
					System.out.println("createNewFile:" + flag);
				}
				os = new FileOutputStream(out);
			} else {
				opt = "string";
				sb = new StringBuffer();
				sb.append(fileName).append(":");
				os = new ByteArrayOutputStream();
			}
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new BASE64EncoderStream(os));
			int len = 0;
			while ((len = bis.read(data)) != -1) {
				bos.write(data, 0, len);
			}
			bos.flush();
			
			if(opt.equals("string")) {
				ByteArrayOutputStream byteArray = (ByteArrayOutputStream)os;
				sb.append(byteArray.toString());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				
				if(bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
		base64Str = sb.toString();
		System.out.println(base64Str);
		return base64Str;
		
	}
}

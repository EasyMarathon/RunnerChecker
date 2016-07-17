package com.easymarathon.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;
import com.easymarathon.util.NumIdentify;
import com.easymarathon.bean.Athlete;
import com.easymarathon.dao.AthleteDao;
import com.easymarathon.dao.DaoBase;


public class CameraUtil {
	 Connection conn=null;

	public static String bytes2Hex(byte[] src) {
		char[] res = new char[src.length * 2];
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		for (int i = 0, j = 0; i < src.length; i++) {
			res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
			res[j++] = hexDigits[src[i] & 0x0f];
		}

		return new String(res);
	}

	/* 给照片打水印 */
	public static void watermark(String iconPath, String srcImgPath, String targerPath) {
		OutputStream os = null;

		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage result = null;
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			double height = imgIcon.getIconHeight();
			double width = imgIcon.getIconWidth();
			double height1 = buffImg.getHeight();
			double width1 = buffImg.getWidth();
			if (height / width > height1 / width1) {
				height1 = height1 / width1 * width;
				width1 = width;
			} else {
				width1 = width1 / height1 * height;
				height1 = height;
			}
			result = new BufferedImage((int) width1, (int) height1, BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = result.createGraphics();
			g.drawImage(srcImg, 0, 0, (int) width1, (int) height1, null);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			// 表示水印图片的位置
			g.drawImage(img, 0, 0, null);
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			srcImg.flush();
			ImageIO.write(result, "JPG", os);
			os.flush();
			System.out.println("图片完成添加Icon印章。。。。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* 保存原图 */
	public static void Original(String srcImgPath, String targerPath) {
		OutputStream os = null;

		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = buffImg.createGraphics();

			g.drawImage(srcImg, 0, 0, srcImg.getWidth(null), srcImg.getHeight(null), null);

			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			srcImg.flush();

			ImageIO.write(buffImg, "JPG", os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public  int compare(int athleteID)
	{
		Connection conn = DaoBase.getConnection(true);
		System.out.println("人脸比对中...");
		String srcImgPath="G:\\face\\athleteCheck\\temp.jpg";
	    String path = "G:\\face\\athleteCheck\\alignment\\temp";
	    FaceAlignment ni = new FaceAlignment();
    	System.out.println("run dll");
    	int ans=0;
    	try{
    			System.out.println(1);
    			ans = ni.cutface(srcImgPath, path);
    			System.out.println("ans:"+ans);
    	}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
			System.out.println("图片对齐完毕");	
			int i=0;
			File file =new File("G:\\face\\athleteCheck\\alignment\\libface_lfw.txt");
			try{
			  if(!file.exists()){
			       file.createNewFile();
			      }
			  FileWriter fileWritter = new FileWriter(file);
		      int pname = athleteID;
			  String data=pname+"-0.jpg "+1+"\r\n";
			  System.out.println("照片号："+String.valueOf(i));
			  System.out.println(data);
			  fileWritter.write(data);			 
			  data="temp-0.jpg"+" "+0;
			  fileWritter.write(data);
			  fileWritter.flush();
			  fileWritter.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("成功写入数据！");
			Facerecognition face=new Facerecognition();
			ans=face.recFace(0, "test", "G:\\face\\athleteCheck\\alignment\\netv8_2_deploy_lfw_0107.prototxt", "G:\\face\\athleteCheck\\alignment\\recog0107_iter_2840000.caffemodel", 2);
		    return ans;

	}
}

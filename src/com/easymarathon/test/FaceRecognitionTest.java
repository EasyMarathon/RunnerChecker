package com.easymarathon.test;

import com.easymarathon.util.Facerecognition;

public class FaceRecognitionTest {

	public static void main(String[] args)
	{
		int in = 0;
		String model="test";
		String deploy="G:\\face\\facerecognize\\netv8_2_deploy_lfw_0107.prototxt";
		String weight="G:\\face\\facerecognize\\recog0107_iter_2840000.caffemodel";
		int iteration=9;
		Facerecognition ni = new Facerecognition();
		System.out.println("run dll");
		try{
			int ans = ni.recFace(in, model, deploy, weight, iteration);
			System.out.println("ans:"+ans);
			//System.getProperty("java.library.path");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

package com.easymarathon.util;

import java.net.URI;
import java.net.URISyntaxException;

public class Facerecognition {
	public static String curpath;
	private native int FaceRecognition(int  in,  String model, String deploy, String weight, int iteration,String path);

	public int recFace(int  in,  String model, String deploy, String weight, int iteration)
	{
		System.out.println("haha");
		return FaceRecognition(in,model,deploy,weight,iteration,curpath);
	}
	
	static
	{
		try
		{
			curpath = new URI(Facerecognition.class.getResource("").getPath()).getPath().substring(1);
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}

		
		System.load(curpath + System.mapLibraryName("lmdb"));
		System.load(curpath + System.mapLibraryName("libgflags"));
		System.load(curpath + System.mapLibraryName("libglog"));
		System.load(curpath + System.mapLibraryName("gflags"));
		System.load(curpath + System.mapLibraryName("zlib"));
		System.load(curpath + System.mapLibraryName("szip"));
		System.load(curpath + System.mapLibraryName("hdf5"));
		System.load(curpath + System.mapLibraryName("hdf5_hl"));
		
		
		
		System.load(curpath + System.mapLibraryName("opencv_core2410"));
		System.load(curpath + System.mapLibraryName("opencv_imgproc2410"));
		System.load(curpath + System.mapLibraryName("opencv_highgui2410"));
		System.load(curpath + System.mapLibraryName("libquadmath-0"));
		System.load(curpath + System.mapLibraryName("libgcc_s_sjlj-1"));
		System.load(curpath + System.mapLibraryName("libgfortran-3"));
		System.load(curpath + System.mapLibraryName("libopenblas"));
		System.load(curpath + System.mapLibraryName("caffe"));
	}

}

package com.easymarathon.util;

import java.net.URI;
import java.net.URISyntaxException;

public class FaceAlignment
{
	public static String curpath;
	private native int faceAlignment(String fname, String dname,String path);

	public int cutface(String fname,String dname)
	{
		return faceAlignment(fname,dname,curpath);
	}
	
	static
	{
		try
		{
			curpath = new URI(FaceAlignment.class.getResource("").getPath()).getPath().substring(1);
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		//System.loadLibrary("facealignmentdll");
		//System.out.println(curpath);
		System.load(curpath + System.mapLibraryName("opencv_core2411"));
		System.load(curpath + System.mapLibraryName("opencv_imgproc2411"));
		System.load(curpath + System.mapLibraryName("opencv_highgui2411"));
		System.load(curpath + System.mapLibraryName("libfacedetect"));
		System.load(curpath + System.mapLibraryName("lmdb"));
		System.load(curpath + System.mapLibraryName("libgflags"));
		System.load(curpath + System.mapLibraryName("libglog"));
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
		System.load(curpath + System.mapLibraryName("caffedll"));
		System.load(curpath + System.mapLibraryName("jlw_face_jjt"));
		//System.load(curpath + System.mapLibraryName("opencv_core2412"));
		//System.load(curpath + System.mapLibraryName("opencv_imgproc2412"));
		//System.load(curpath + System.mapLibraryName("opencv_highgui2412"));
		//System.load(curpath + System.mapLibraryName("opencv_contrib2412"));
		//System.load(curpath + System.mapLibraryName("opencv_calib3d2412"));
		System.load(curpath + System.mapLibraryName("facealignmentdll"));
		
	}
}

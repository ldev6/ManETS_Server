package ca.etsmtl.gti785;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 * Servlet implementation class ServletManETS
 */
public class ServletManETS extends HttpServlet {
	private static final long serialVersionUID = 1L;


	//EXECUTED AT START
	static {
//		System.out.println(System.getProperty("java.version"));
//		System.out.println(System.getProperty("java.vendor"));
//		System.out.println(System.getProperty("java.vm.specification.version"));
//		System.out.println(System.getProperty("java.vm.specification.name"));
//		System.out.println(System.getProperty("java.vm.version"));
//		System.out.println(System.getProperty("java.vm.vendor"));
		
//		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (86)\\VideoLAN\\VLC");
//		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files (86)/VideoLAN/VLC");
		
//		System.out.println(System.getProperty("java.library.path"));
		
//		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		System.out.println("Native Lib loaded");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletManETS() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.getWriter().write("[1,2,3,4,5,6,7,8,9,10]");
//		PrintWriter out = response.getWriter();
//		out.println("[1,2,3,4,5,6,7,8,9,10]");
//		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("[1,2,3,4,5,6,7,8,9,10]");
		out.flush();
	}

}

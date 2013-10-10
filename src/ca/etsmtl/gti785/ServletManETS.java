package ca.etsmtl.gti785;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import ca.etsmtl.gti785.model.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 * Servlet implementation class ServletManETS
 */
public class ServletManETS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String hostAddress;
	private static String userHome;
	private static String musicHome;
	private static String videoHome;

	// EXECUTED AT START
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"C:\\Program Files\\VideoLAN\\VLC");
		System.out.println("C:\\Program Files\\VideoLAN\\VLC");

		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		System.out.println("Native Lib loaded");

		userHome = System.getProperty("user.home");
		musicHome = checkMusicHomeExist();
		videoHome = checkMusicVideoExist();

		System.out.println(userHome);

		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		hostAddress = IP.getHostAddress();
		hostAddress += ":8080/ManETS_Server/#";
		System.out.println("IP of my system is := " + hostAddress);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletManETS() {
		super();
	}

	private static String checkMusicVideoExist() {
		return new File(userHome + "/Music").exists() ? userHome + "/Music"
				: "false";
	}

	private static String checkMusicHomeExist() {
		return new File(userHome + "/Video").exists() ? userHome + "/Music"
				: "false";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");

		DataSource r = new DataSource("root", hostAddress);
		DataSource m = new DataSource("musicHome", musicHome);
		DataSource v = new DataSource("videoHome", videoHome);

//		response.getWriter().write("{\"dataSources\":[\"music\",\"video\"]}");
		response.getWriter().write(new ObjectMapper().writeValueAsString(r));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("[1,2,3,4,5,6,7,8,9,10]");
		out.flush();
	}

}

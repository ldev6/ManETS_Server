package ca.etsmtl.gti785;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import ca.etsmtl.gti785.model.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	// EXECUTED AT START, ONLY ONE TIME
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"C:\\Program Files\\VideoLAN\\VLC");
		System.out.println("C:\\Program Files\\VideoLAN\\VLC");

		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		System.out.println("Native Lib loaded");

		userHome = System.getProperty("user.home");
		musicHome = checkMusicHomeExist();
		if (!musicHome.equals("null")) {
			startScrapingMusicFolder();
		}
		videoHome = checkMusicVideoExist();
		System.out.println("============================");
		System.out.println("Server System info :");
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("os.version"));
		System.out.println("User Home folder : " + userHome);
		System.out.println("============================");
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		hostAddress = "http://" + IP.getHostAddress();
		hostAddress += ":8080/ManETS_Server/#";
		System.out.println("Adress of my system is : " + hostAddress);
		System.out.println("============================");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletManETS() {
		super();
	}

	private static void startScrapingMusicFolder() {

	}

	private static String checkMusicVideoExist() {
		String string = new File(userHome + "\\Music").exists() ? userHome
				+ "\\Music" : "false";
		System.out.println("\\Music Exists");
		return string;
	}

	private static String checkMusicHomeExist() {
		return new File(userHome + "\\Video").exists() ? userHome + "\\Video"
				: "false";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String servInfo = request.getServletPath();
		String requestURL = request.getRequestURL().toString();

		System.out.println(servInfo);
		System.out.println(requestURL);

		if (servInfo.equals("/ServletManETS/")) {

			response.setContentType("application/json");

			DataSource r = new DataSource("root", hostAddress);
			DataSource m = new DataSource("musicHome", musicHome);
			DataSource v = new DataSource("videoHome", videoHome);

			List<DataSource> dataSources = new ArrayList<DataSource>();
			dataSources.add(r);
			dataSources.add(m);
			dataSources.add(v);

			response.getWriter().write(
					new ObjectMapper().writeValueAsString(dataSources));

		} else {

			PrintWriter out = response.getWriter();

			out.println("GET request handling");
			out.println(servInfo);
			out.println(request.getParameterMap());
			try {
				RestRequest resourceValues = new RestRequest(servInfo);
				out.println(resourceValues.getId());
			} catch (ServletException e) {
				response.setStatus(400);
				response.resetBuffer();
				e.printStackTrace();
				out.println(e.toString());
			}
			out.close();
		}
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

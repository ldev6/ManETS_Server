package ca.etsmtl.gti785;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import ca.etsmtl.gti785.model.DashBoardFeed;
import ca.etsmtl.gti785.model.RepertoireDefinition;
import ca.etsmtl.gti785.model.DashBoardFeed.Settings;
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
		if (!musicHome.equals("false")) {
			startScrapingMusicFolder();
		}
		videoHome = checkVideoExist();
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

	private static String checkVideoExist() {
		String string = new File(userHome + "\\Videos").exists() ? userHome
				+ "\\Videos" : "false";
		return string;
	}

	private static String checkMusicHomeExist() {
		return new File(userHome + "\\Music").exists() ? userHome + "\\Music"
				: "false";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String servInfo = request.getServletPath();
		// String requestURL = request.getRequestURL().toString();

		System.out.println(">Path : " + servInfo);
		// System.out.println(requestURL);

		response.setContentType("application/json");
		if (servInfo.equals("/")) {

			DataSource r = new DataSource("root", hostAddress);
			DataSource m = new DataSource("musicHome", musicHome);
			DataSource v = new DataSource("videoHome", videoHome);

			ArrayList<DataSource> list = new ArrayList<DataSource>();
			list.add(r);
			list.add(m);
			list.add(v);
			DashBoardFeed feed = new DashBoardFeed(list, new Settings());

			response.getWriter().write(
					new ObjectMapper().writeValueAsString(feed));

		} else {

			PrintWriter out = response.getWriter();

			Map<String, String[]> parameterMap = request.getParameterMap();
			RestRequest resourceValues = new RestRequest(servInfo);
			File[] array;
			switch (resourceValues.c) {
			case ADD:

				break;
			case CLEAR:
			case LIST:
				array = new File(musicHome).listFiles();
				List<RepertoireDefinition> listRepertoire = new ArrayList<RepertoireDefinition>();
				for (int i = 0; i < array.length; i++) {
					listRepertoire.add(new RepertoireDefinition(musicHome,
							array[i].getName()));
				}
				response.getWriter().write(
						new ObjectMapper().writeValueAsString(listRepertoire));
				break;
			case NEXT:
			case OPEN:
			case ORDER:
			case PAUSE:
			case PLAY:
			case PLAYLIST:
			case PLAYPLAYLIST:
			case PREVIOUS:
			case REMOVE:
			case SEEK:
			case STATE:
			case STOP:
			case VOLUME:
			case NONE:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				break;
			}
			out.close();
			out.flush();
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

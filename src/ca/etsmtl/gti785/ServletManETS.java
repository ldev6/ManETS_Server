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



import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import ca.etsmtl.gti785.model.DashBoardFeed;
import ca.etsmtl.gti785.model.DashBoardFeed.Settings;
import ca.etsmtl.gti785.model.DataSource;
import ca.etsmtl.gti785.model.PlayList;
import ca.etsmtl.gti785.model.RepertoireDefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
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
	private static final String extensions[] = new String[] { "mp3", "flac",
			"mp4" };

	private static final FileFilter fileFilter = new FileFilter() {

		public boolean accept(File file) {
			if (file.isDirectory()) {
				return true;
			} else {
				String path = file.getAbsolutePath().toLowerCase();
				for (int i = 0, n = extensions.length; i < n; i++) {
					String extension = extensions[i];
					if ((path.endsWith(extension) && (path.charAt(path.length()
							- extension.length() - 1)) == '.')) {
						return true;
					}
				}
			}
			return false;
		}
	};
	// EXECUTED AT START, ONLY ONE TIME
	static {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"C:\\Program Files\\VideoLAN\\VLC");
		System.out.println("C:\\Program Files\\VideoLAN\\VLC");

		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		System.out.println("Native Lib loaded");

		userHome = System.getProperty("user.home");
		musicHome = checkMusicHomeExist();
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

	private HeadlessMediaPlayer mediaPlayer;
	private PlayList playlists = new PlayList();
	private int listIdPlay = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletManETS() {
		super();

		final MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

		mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
		assert mediaPlayer != null;
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

	// ////////////////////////////////////////////////////
	//
	// HTTP MANAGEMENT
	//
	// ////////////////////////////////////////////////////

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String servInfo = request.getServletPath();

		System.out.println(">Path : " + servInfo);

		response.setContentType("application/json");
		if (servInfo.equals("/")) {

			final DataSource m = new DataSource("list", "/list");
			final DataSource v = new DataSource("playlist", "/playlist");

			final ArrayList<DataSource> list = new ArrayList<DataSource>();
			list.add(m);
			list.add(v);
			final DashBoardFeed feed = new DashBoardFeed(list, new Settings());

			response.getWriter().write(
					new ObjectMapper().writeValueAsString(feed));

		} else {

			final PrintWriter out = response.getWriter();

			final Map<String, String[]> parameterMap = request
					.getParameterMap();

			System.out.println(">>Params : " + parameterMap);

			final RestRequest resourceValues = new RestRequest(servInfo);

			switch (resourceValues.c) {
			case ADD:
				manageAddRequest(response, parameterMap);
				break;
			case CLEAR:
				manageClearRequest(response, parameterMap);
				break;
			case LIST:
				manageListRequest(response, parameterMap);
				break;
			case NEXT:
				manageNextRequest(response, parameterMap);
				break;
			case OPEN:
				manageOpenRequest(response, parameterMap);
				break;
			case ORDER:
				manageOrderRequest(response, parameterMap);
				break;
			case PAUSE:
				managePauseRequest(response, parameterMap);
				break;
			case PLAY:
				managePlayRequest(response, parameterMap);
				break;
			case PLAYLIST:
				managePlaylistRequest(response, parameterMap);
				break;
			case PLAYPLAYLIST:
				managePlayPlayListRequest(response, parameterMap);
				break;
			case PREVIOUS:
				managePreviousRequest(response, parameterMap);
				break;
			case REMOVE:
				manageRemoveRequest(response, parameterMap);
				break;
			case SEEK:
				manageSeekRequest(response, parameterMap);
				break;
			case STATE:
				manageStateRequest(response, parameterMap);
				break;
			case STOP:
				manageStopRequest(response, parameterMap);
				break;
			case VOLUME:
				manageVolumeRequest(response, parameterMap);
				break;
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

	private void manageNextRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub
		System.out.println("do something in next"+listIdPlay);
		if(listIdPlay<playlists.paths.size()-1){
			listIdPlay++;
			mediaPlayer.stop();
			if(mediaPlayer.playMedia(playlists.paths.get(listIdPlay))){
				response.setStatus(HttpServletResponse.SC_OK);
			}
		}else{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void manageVolumeRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) throws IOException {
		if (parameterMap.containsKey("value")) {
			int vol = Integer.parseInt(parameterMap.get("value")[0]);
			mediaPlayer.setVolume(vol);
			response.setStatus(200);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void manageStopRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {

		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
	}

	private void manageStateRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub
	}

	private void manageSeekRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) throws IOException {
		if (parameterMap.containsKey("value")) {
			int seek = Integer.parseInt(parameterMap.get("value")[0]);
			mediaPlayer.setPosition(seek);
			response.setStatus(200);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void manageRemoveRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub

	}

	private void managePreviousRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub
		if(listIdPlay>0){
			listIdPlay--;
			mediaPlayer.stop();
			if(mediaPlayer.playMedia(playlists.paths.get(listIdPlay))){
				response.setStatus(200);
			}
		}else{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	private void managePlayPlayListRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub

	}

	private void managePlaylistRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub

	}

	private void managePlayRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		// TODO Auto-generated method stub
		if (mediaPlayer.isPlayable()) {
			mediaPlayer.play();
		}
	}

	private void managePauseRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		if (mediaPlayer.canPause()) {
			mediaPlayer.pause();
		}
	}

	private void manageOrderRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		playlists.randomise();
	}

	private void manageClearRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) {
		playlists.paths.clear();
		response.setStatus(200);
	}

	private void manageOpenRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) throws JsonProcessingException, IOException {
		
		listIdPlay =0;
		File[] array = null;
		String path="";

		if (parameterMap.containsKey("path")) {
			path += parameterMap.get("path")[0];
			mediaPlayer.stop();
			playlists.paths.clear();
			array = new File(path).listFiles(fileFilter);
			//mediaPlayer.setPlaySubItems(true);

			for(int i=0; i<array.length; i++){
		    	playlists.paths.add(array[i].getAbsolutePath());
		    }
			if(mediaPlayer.playMedia(playlists.paths.get(0))){
				response.setStatus(HttpServletResponse.SC_OK);
			}else{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			}
			for(int i=1; i<playlists.paths.size(); i++){
				mediaPlayer.playNextSubItem(playlists.paths.get(i));
			}


			System.out.println(">>Path asked : " + path);
		}
		 else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	private void manageAddRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) throws IOException {

		if (parameterMap.containsKey("path")) {
			playlists.paths.add(parameterMap.get("path")[0]);
			response.setStatus(200);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void manageListRequest(HttpServletResponse response,
			Map<String, String[]> parameterMap) throws IOException,
			JsonProcessingException {
		File[] array;
		String path = musicHome;
		// add path param if key=rep is present
		if (parameterMap.containsKey("rep")) {

			path += parameterMap.get("rep")[0];
			System.out.println(">>Path asked : " + musicHome);
		}
		// list all file from directory with file filtering
		array = new File(path).listFiles(fileFilter);
		if (array != null) {

			List<RepertoireDefinition> listRepertoire = new ArrayList<RepertoireDefinition>();
			for (int i = 0; i < array.length; i++) {
				listRepertoire.add(new RepertoireDefinition(path, array[i]
						.getName()));
			}

			response.getWriter().write(
					new ObjectMapper().writeValueAsString(listRepertoire));
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		array = null;
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

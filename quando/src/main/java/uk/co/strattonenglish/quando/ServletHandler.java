package uk.co.strattonenglish.quando;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.strattonenglish.quando.route.*;

// This class encompasses localhost handling that requires access to the platform, including
// serial port and platform keyboard and mouse control (java.awt.Robot)

public class ServletHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String serverURL = "quando.eu-gb.mybluemix.net";
	private static final HashMap<String, Route> routes = new HashMap<>();
	static {
		routes.put("/", new Home());
		routes.put("/control/type", new KeyboardType());
		routes.put("/control/key", new KeyboardKey());
		routes.put("/control/mouse", new Mouse());
		routes.put("/ubit/display", new UbitDisplay());
		routes.put("/ubit/icon", new UbitShowIcon());
	}
	private static final Route unknown = new Unknown();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String target = request.getRequestURI();
		routes.getOrDefault(target, unknown).handle(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String target = request.getRequestURI();
		String message = "GET:" + target;
		HttpURLConnection connection = (HttpURLConnection) new URL("https", serverURL, target).openConnection();
		message += "/" + connection.getResponseCode();
		response.setStatus(connection.getResponseCode());
		try (InputStream in = connection.getInputStream()) {
			byte[] buffer = new byte[4096];
			int bytes = -1;
			OutputStream out = response.getOutputStream();
			while ((bytes = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytes);
			}
			message += "/" + connection.getContentType();
			response.setContentType(connection.getContentType());
		} catch (FileNotFoundException ex) {
		} catch (IOException ioe) {
			ioe.printStackTrace(System.err);
		}
		System.out.println(message);
	}
}

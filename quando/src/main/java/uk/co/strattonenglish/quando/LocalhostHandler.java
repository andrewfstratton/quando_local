package uk.co.strattonenglish.quando;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.strattonenglish.quando.route.*;

// This class encompasses localhost handling that requires access to the platform, including
// serial port and platform keyboard and mouse control (java.awt.Robot)

public class LocalhostHandler extends HttpServlet {
	static HashMap<String, Route> routes = new HashMap<>();
	static {
		routes.put("/", new Home());
		routes.put("/control/type", new KeyboardType());
		routes.put("/control/key", new KeyboardKey());
		routes.put("/control/mouse", new Mouse());
		routes.put("/ubit/display", new UbitDisplay());
	}
	static Route unknown = new Unknown();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		String target = request.getRequestURI();
		// System.out.println(target);
		routes.getOrDefault(target, unknown).handle(request, response);
	}
}

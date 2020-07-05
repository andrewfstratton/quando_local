package uk.co.strattonenglish.quando.route;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends Route {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Implement local server info page
		// This could, when accessed from the same machine, provide a dashboard for enabling keyboard/mouse control, etc.
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("{server:'ok'}");
	}
}

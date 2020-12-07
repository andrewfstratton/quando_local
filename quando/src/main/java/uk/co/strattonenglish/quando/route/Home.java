package uk.co.strattonenglish.quando.route;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends Route {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

				String host = InetAddress.getLocalHost().getHostAddress();
        PrintWriter out = response.getWriter();

        out.println("{ip:'"+host+"'}");
	}
}

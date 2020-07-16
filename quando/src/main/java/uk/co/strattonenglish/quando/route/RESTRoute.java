package uk.co.strattonenglish.quando.route;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RESTRoute extends Route {
	private String contentType = "application/json";
	private int status_code = HttpServletResponse.SC_OK;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
        response.setContentType(contentType);
        response.setStatus(status_code);

        PrintWriter out = response.getWriter();
        out.println(handle_REST(request));
	}
	
	public abstract String handle_REST(HttpServletRequest request) throws IOException;
}
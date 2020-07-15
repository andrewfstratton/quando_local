package uk.co.strattonenglish.quando;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class LocalServer {
	private static final int SERVER_PORT = 8080;

	private static Server createServer(int port) {
		Server server = new Server(port);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(LocalhostHandler.class,"/*");
		return server;
	}

	public static void main(final String[] args) throws Exception {
		final Server server = createServer(SERVER_PORT);
		server.start();
		server.join();
	}
}
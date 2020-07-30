package uk.co.strattonenglish.quando;

import javax.servlet.ServletException;

import org.eclipse.jetty.http.pathmap.ServletPathSpec;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.WebSocketUpgradeFilter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import uk.co.strattonenglish.quando.device.Handlers;

public class LocalServer {
	private static final int SERVER_PORT = 8080;

	public static class LocalServerSocketCreator implements WebSocketCreator {
		@Override
		public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
			return new WebSocketHandler();
		}
	}

	private static Server createServer(int port) {
		Server server = new Server(port);

		ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletHandler.setContextPath("/");
		server.setHandler(servletHandler);

		// Add websocket as a filter
		WebSocketUpgradeFilter websocketfilter;
		try {
			websocketfilter = WebSocketUpgradeFilter.configure(servletHandler);
			// wsfilter.getFactory().getPolicy().setIdleTimeout(5000);
			websocketfilter.addMapping(new ServletPathSpec("/ws/*"), new LocalServerSocketCreator());
			Handlers.getUbitHandler().handle();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		servletHandler.addServlet(ServletHandler.class,"/*");
		return server;
	}

	public static void main(final String[] args) throws Exception {
		final Server server = createServer(SERVER_PORT);
		server.start();
		server.join();
	}
}
package uk.co.strattonenglish.quando;

import com.tulskiy.keymaster.common.Provider;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.HotKey;
import org.eclipse.jetty.server.Server;
import javax.swing.*;

public class LocalServer {

	private static final int SERVER_PORT = 8080;

	public static void main(final String[] args) throws Exception {
		final Server server = new Server(SERVER_PORT);

		Provider provider = Provider.getCurrentProvider(false);
		provider.register(KeyStroke.getKeyStroke("F"), new HotKeyListener() {
			public void onHotKey(HotKey hotKey) {
				System.out.println(hotKey);
			}
		});

		server.setHandler(new LocalhostHandler());
		server.start();
		server.join();
	}
}
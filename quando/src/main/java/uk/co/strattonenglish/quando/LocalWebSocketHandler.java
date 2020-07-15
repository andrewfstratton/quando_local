package uk.co.strattonenglish.quando;

import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class LocalWebSocketHandler implements Runnable {
    private Session session;

    @OnWebSocketMessage
    public void onMessage(Session session, String message)
    {
      System.out.println(message);
    }

    @OnWebSocketConnect
    public void onOpen(Session session)
    {
        this.session = session;
        new Thread(this).start();
    }

    @OnWebSocketClose
    public void onClose(int closeCode, String closeReasonPhrase)
    {
        this.session = null;
    }

    @Override
    public void run()
    {
      int count = 0;
      while (this.session != null)
      {
        try {
          this.session.getRemote().sendString(String.valueOf(++count));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
}
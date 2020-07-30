package uk.co.strattonenglish.quando;

import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketHandler implements Runnable {
    private Session session;
    int count = 0;

    @OnWebSocketMessage
    public void onMessage(Session session, String message)
    {
      System.out.println(message + ":" + count);
    }

    @OnWebSocketConnect
    public void onOpen(Session session)
    {
      System.out.println("Open:" + count);
        this.session = session;
        new Thread(this).start();
    }

    @OnWebSocketClose
    public void onClose(int closeCode, String closeReasonPhrase)
    {
      System.out.println("Close:" + count);
        this.session = null;
    }

    @Override
    public void run()
    {
      while (this.session != null)
      {
        try {
          this.session.getRemote().sendString(String.valueOf(++count));
          Thread.sleep(1000);
        } catch (IOException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
}
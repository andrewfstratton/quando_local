package uk.co.strattonenglish.quando;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketHandler {
  private Session session;
  private static List<Session> sessions = new ArrayList<Session>();

  @OnWebSocketMessage
  public void onMessage(Session session, String message) {
    System.out.println("Message:"+message);
  }

  @OnWebSocketConnect
  public void onOpen(Session session) {
    this.session = session;
    sessions.add(session);
  }

  @OnWebSocketClose
  public void onClose(int closeCode, String closeReasonPhrase) {
    sessions.remove(this.session);
    this.session = null;
  }

  public static synchronized void broadcast(String msg) {
    // Note: synchronized is essential here to avoid runtime error
    for (Session session : sessions) {
      try {
        session.getRemote().sendString(msg);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
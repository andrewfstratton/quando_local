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
public class WebSocketHandler implements Runnable {
  private Session session;
  private static List<Session> sessions = new ArrayList<Session>();
  int count = 0;
  private static Thread thread = null;


  @OnWebSocketMessage
  public void onMessage(Session session, String message) {
    System.out.println(message + ":" + count);
  }

  @OnWebSocketConnect
  public void onOpen(Session session) {
    System.out.println("Open:" + count);
    this.session = session;
    sessions.add(session);
    if (thread == null) {
      thread = new Thread(this);
      thread.start();
    }
  }

  @OnWebSocketClose
  public void onClose(int closeCode, String closeReasonPhrase) {
    System.out.println("Close:" + count);
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

  @Override
  public void run() {
    while (true) {
      try {
        broadcast(String.valueOf(sessions.size()) + ":" + String.valueOf(++count));
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
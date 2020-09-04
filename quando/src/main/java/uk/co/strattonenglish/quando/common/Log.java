package uk.co.strattonenglish.quando.common;

public class Log {
  private int state;

  public void onState(String message, int iState) {
    if (state != iState) {
      state = iState;
      System.out.println(message);
    }
  } 

  public Log() {
    state = 0;
  }

  public Log(int start_state) {
    state = start_state;
  }
}
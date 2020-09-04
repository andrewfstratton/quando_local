package uk.co.strattonenglish.quando.device.common;

import uk.co.strattonenglish.quando.common.Log;
import uk.co.strattonenglish.quando.common.USBSerial;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;

public class Ubit {
  private static final int CONNECTED = 1;
  private static final int DISCONNECTED = 2;
  private static final int PORT_BLOCKED = 3;
  private static Log log = new Log(DISCONNECTED);
  private static SerialPort serialPort = null;
  private BufferedReader in = null;

  public String getMessage() {
    String message = null;
    try {
      if (in == null) {
        getSerialPort();
      }
      if (in != null) {
        message = in.readLine();
      }
    } catch (SerialPortTimeoutException e) {
      // ignore - just return null message
    } catch (Exception e) {
      in = null;
      serialPort = null;
      log.onState("...micro:bit disconnected", DISCONNECTED);
    }
    return message;
  }

  private void getSerialPort() {
    if (serialPort == null) {
      in = null;
      serialPort = USBSerial.getSerialPort("mbed Serial Port");
      serialPort.setBaudRate(115200);
    }
    if ((serialPort != null) && (in == null)) { // i.e. port hasn't been opened successfully
      if (serialPort.openPort()) {
        log.onState("micro:bit connected...", CONNECTED);
        in = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
      } else {
        log.onState("micro:bit port won't open...may be in use by another application", PORT_BLOCKED);
      }
    }
  }
}
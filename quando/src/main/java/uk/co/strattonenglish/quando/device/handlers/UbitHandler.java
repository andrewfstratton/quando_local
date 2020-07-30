package uk.co.strattonenglish.quando.device.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fazecast.jSerialComm.SerialPort; // TODO move into common class
import com.fazecast.jSerialComm.SerialPortTimeoutException;

import uk.co.strattonenglish.quando.common.USBSerial;
import uk.co.strattonenglish.quando.device.Handlers;

public class UbitHandler extends Handlers implements Runnable {
	// For handling micro:bit events from the serial port on the local machine
	SerialPort serialPort = null;
	BufferedReader in = null;
	boolean connected_reported = false;

	public void handle() {
		new Thread(this).start();
	}

	private void checkMessage() {
		String message;
		try {
			message = in.readLine();
			if (message != null) {
				System.out.println(message);
			}
		} catch (SerialPortTimeoutException e) {
		} catch (Exception e) {
			in = null;
			serialPort = null;
			System.out.println("...micro:bit disconnected");
			connected_reported = false;
		}
	}

	private boolean checkConnected() {
		boolean connected = true;
				in = null;
				serialPort = USBSerial.getSerialPort("mbed Serial Port");
				if (serialPort != null) {
					serialPort.setBaudRate(115200);
					if (!connected_reported) {
						System.out.println("micro:bit connected...");
						connected_reported = true;
					}
					if (serialPort.openPort()) {
						in = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
					} else {
						// port failed to open - probably in use by another program
						// serialPort = null; // force search for new micro:bit
						connected = false;
					}
				} else {
						connected = false;
				}
		return connected;
	}

	@Override
	public void run() {
		do {
			int snooze = 20; // i.e. 1000/50 times a second
			if (serialPort != null) {
				checkMessage();
			} else if (!checkConnected()) {
				snooze = 250;
			}
			try {
				Thread.sleep(snooze);
			} catch (InterruptedException e) {
			}
		} while (true);
	}
}
package uk.co.strattonenglish.quando.common;

import com.fazecast.jSerialComm.SerialPort;

public class USBSerial {
	// For access to serial port on the local machine

	public static final SerialPort getSerialPort(String description) {
		SerialPort result = null;

		for (SerialPort sp : SerialPort.getCommPorts()) {
			if (sp.getPortDescription().equals("mbed Serial Port")) {
				result = sp;
				break;
			}
		}
		return result;
	}
}
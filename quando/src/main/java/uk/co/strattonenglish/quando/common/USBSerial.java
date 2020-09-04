package uk.co.strattonenglish.quando.common;

import com.fazecast.jSerialComm.SerialPort;

public final class USBSerial {
	// For access to serial port on the local machine
	static SerialPort last = null;

	public static final SerialPort getSerialPort(String description) {

		if ((last != null) && (last.isOpen())) {
		} else {
			for (SerialPort sp : SerialPort.getCommPorts()) {
				if (sp.getPortDescription().equals("mbed Serial Port")) {
					sp.setComPortParameters(115200, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
					if (!sp.isOpen()) {
						sp.openPort();
					}
					last = sp;
					break;
				}
			}
		}
		System.out.println(last.isOpen());
		return last;
	}

}
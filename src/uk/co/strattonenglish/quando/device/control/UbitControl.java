package uk.co.strattonenglish.quando.device.control;

import com.fazecast.jSerialComm.SerialPort;

import uk.co.strattonenglish.quando.device.Controllers;

public class UbitControl  extends Controllers {
	// For controlling a micro:bit through the serial port on the local machine

	private SerialPort getSerialPort() {
		SerialPort result = null;

		for (SerialPort sp : SerialPort.getCommPorts()) {
			if (sp.getPortDescription().equals("mbed Serial Port")) {
				result = sp;
				break;
			}
		}
		return result;
	}

	public void display(String str) {
		SerialPort sp = getSerialPort();
		if (sp != null) {
			System.out.println("** Ubit **: '" + str + "'" + sp.getSystemPortName());
			// TODO implement display on ubit
		} else {
			System.out.println("No micro:bit found");
		}
	}
}
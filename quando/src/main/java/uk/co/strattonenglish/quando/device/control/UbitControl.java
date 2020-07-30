package uk.co.strattonenglish.quando.device.control;

import com.fazecast.jSerialComm.SerialPort;

import uk.co.strattonenglish.quando.device.Controllers;
import uk.co.strattonenglish.quando.common.USBSerial;

public class UbitControl extends Controllers {
	// For controlling a micro:bit through the serial port on the local machine

	public void display(String str) {
		SerialPort sp = USBSerial.getSerialPort("mbed Serial Port");
		if (sp != null) {
			String msg = "D=" + str + "\r\n";
			System.out.println("** Ubit **: '" + msg + "'" + sp.getSystemPortName());
			byte[] buf = msg.getBytes();
			sp.writeBytes(buf, (long) buf.length);
		} else {
			System.out.println("No micro:bit found");
		}
	}
}
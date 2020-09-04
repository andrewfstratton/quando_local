package uk.co.strattonenglish.quando.device.control;

import java.io.UnsupportedEncodingException;

import com.fazecast.jSerialComm.SerialPort;

import uk.co.strattonenglish.quando.device.Controllers;
import uk.co.strattonenglish.quando.common.USBSerial;

public class UbitControl extends Controllers {
	// For controlling a micro:bit through the serial port on the local machine

	public void display(String str) {
		SerialPort sp = USBSerial.getSerialPort("mbed Serial Port");
		if (sp != null) {
			String msg = "D=" + str + "\n";
			byte[] buf;
			try {
				buf = msg.getBytes("UTF-8");
				int sent = sp.writeBytes(buf, (long) buf.length);
				System.out.println("** Ubit **: sent("+sent+")'" + msg + "'" + sp.getSystemPortName());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No micro:bit found");
		}
	}
}
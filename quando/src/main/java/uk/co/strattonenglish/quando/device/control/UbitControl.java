package uk.co.strattonenglish.quando.device.control;

import java.io.UnsupportedEncodingException;

import com.fazecast.jSerialComm.SerialPort;

import uk.co.strattonenglish.quando.device.Controllers;
import uk.co.strattonenglish.quando.common.USBSerial;

public class UbitControl extends Controllers {
	// For controlling a micro:bit through the serial port on the local machine
	private void send(String prefix, String str) {
		SerialPort sp = USBSerial.getSerialPort("mbed Serial Port");
		if (sp != null) {
			String msg = prefix + str + "\n";
			byte[] buf;
			try {
				buf = msg.getBytes("UTF-8");
				int sent = sp.writeBytes(buf, (long) buf.length);
				System.out.println("** Ubit **: sent("+sent+")'" + msg + "'" + sp.getSystemPortName());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public void display(String str) {
		send("D=", str);
	}

	public void icon(int index) {
		send("I=", Integer.toString(index));
	}

	public void servo(int servo, int angle) {
		// Already in degrees and has had 360, or 1 for servo, added - so python will get 0 if the string is corrupt
		String msg = Integer.toString(angle) + "," + Integer.toString(servo);
		send("T=", msg);
	}
}
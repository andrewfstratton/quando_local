package uk.co.strattonenglish.quando.device.control;

import uk.co.strattonenglish.quando.device.Controllers;
import uk.co.strattonenglish.quando.device.common.Ubit;

public class UbitControl extends Controllers {
	// For controlling a micro:bit through the serial port on the local machine

	public void display(String str) {
		Ubit.sendMessage("D=" + str);
	}

	public void icon(int index) {
		Ubit.sendMessage("I=" + Integer.toString(index));
	}

	public void servo(int servo, int angle) {
		// Already in degrees and has had 360, or 1 for servo, added - so python will
		// get 0 if the string is corrupt
		Ubit.sendMessage("T=" + Integer.toString(angle) + "," + Integer.toString(servo));
	}
}
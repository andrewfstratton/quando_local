package uk.co.strattonenglish.quando.device;

import uk.co.strattonenglish.quando.device.control.*;
import java.awt.AWTException;
import java.awt.Robot;

public abstract class Controllers {
	static protected Robot robot;
	static private KeyControl keyControl;
	static private MouseControl mouseControl;
	static private UbitControl ubitControl;
	static {
		try {
			robot = new Robot();
			keyControl = new KeyControl();
			mouseControl = new MouseControl();
			ubitControl = new UbitControl();
		} catch (AWTException e1) {
			System.out.println("Warning - Local Control threw exception - ignored from now");
		}
	}

	// Factory - warning - can return null
	public static KeyControl getKeyControl() {
		return keyControl;
	}
	public static MouseControl getMouseControl() {
		return mouseControl;
	}
	public static UbitControl getUbitControl() {
		return ubitControl;
	}
}

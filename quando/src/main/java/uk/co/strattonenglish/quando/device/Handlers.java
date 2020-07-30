package uk.co.strattonenglish.quando.device;

import uk.co.strattonenglish.quando.device.handlers.*;

public abstract class Handlers {
	static private KeyHandler keyHandler;
	static private UbitHandler ubitHandler;
	static {
    keyHandler = new KeyHandler();
    ubitHandler = new UbitHandler();
	}

	// Factory - warning - can return null
	public static KeyHandler getKeyHandler() {
		return keyHandler;
	}
	public static UbitHandler getUbitHandler() {
		return ubitHandler;
	}
}
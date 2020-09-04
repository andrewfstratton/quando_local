package uk.co.strattonenglish.quando.device.handlers;

import org.json.JSONException;

import uk.co.strattonenglish.quando.common.JSON;
import uk.co.strattonenglish.quando.device.Handlers;
import uk.co.strattonenglish.quando.device.common.Ubit;

public class UbitHandler extends Handlers implements Runnable {
	// For handling micro:bit events from the serial port on the local machine
	Ubit ubit = new Ubit();

	public void handle() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		do {
			int snooze = 20; // i.e. 1000/50 times a second
			String message = ubit.getMessage();
			if (message != null) {
				handleMessage(message);
				System.out.println("u:"+message);
			}
			try {
				Thread.sleep(snooze);
			} catch (InterruptedException e) {
			}
		} while (true);
	}

	private void handleMessage(String message) {
		try {
			JSON jso = new JSON(message);
			String orientation = jso.getString("orientation", "");
			boolean button_a = jso.getBoolean("button_a", false);
			boolean button_b = jso.getBoolean("button_b", false);
			System.out.println(orientation + ":" + button_a + ":" + button_b);
		} catch (JSONException e) {
			// ignore - micro:bit errors fairly often
		}
	}
}
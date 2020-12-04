package uk.co.strattonenglish.quando.device.handlers;

import org.json.JSONException;
import org.json.JSONObject;

import uk.co.strattonenglish.quando.WebSocketHandler;
import uk.co.strattonenglish.quando.common.JSON;
import uk.co.strattonenglish.quando.device.Handlers;
import uk.co.strattonenglish.quando.device.common.Ubit;

public class UbitHandler extends Handlers implements Runnable {
	// For handling micro:bit events from the serial port on the local machine
	private final static int PER_SECOND = 100;
	private final static int SNOOZE_MS = 1000/PER_SECOND;

	public void handle() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		do {
			String message = Ubit.getMessage();
			while (message != null) {
				handleMessage(message);
				message = Ubit.getMessage();
				// System.out.println("u:"+message);
			}
			try {
				Thread.sleep(SNOOZE_MS);
			} catch (InterruptedException e) {
			}
		} while (true);
	}

	private void putJSON(boolean condition, JSONObject json, String key, String val) {
		if (condition) {
			json.put(key, val);
		}
	}

	private void handleMessage(String message) {
		try {
			JSON jsin = new JSON(message);
			JSONObject jsout = new JSONObject();
			String orientation = jsin.getString("Or", "");
			putJSON(orientation != "", jsout, "orientation", orientation);
			boolean btn_a = jsin.getBoolean("Ba", false);
			boolean btn_b = jsin.getBoolean("Bb", false);
			putJSON(btn_a && !btn_b, jsout, "button_a", "true");
			putJSON(btn_b && !btn_a, jsout, "button_b", "true");
			putJSON(btn_a && btn_b, jsout, "button_ab", "true");
			Float heading = jsin.getFloat("He", -999);
			if (heading != -999) {
				// heading is integer 0..360
			  putJSON(true, jsout, "heading", heading.toString());
			}
			Float roll = jsin.getFloat("Ro", -999);
			if (roll != -999) {
				roll = roll * 180/(float)Math.PI;
				putJSON(true, jsout, "roll", roll.toString());
			}
			Float pitch = jsin.getFloat("Pi", -999);
			if (pitch != -999) {
				pitch = pitch * 180/(float)Math.PI;
				putJSON(true, jsout, "pitch", pitch.toString());
			}
			WebSocketHandler.broadcast(jsout.toString());
			// System.out.println(jsout.toString());
		} catch (JSONException e) {
			// ignore - micro:bit errors fairly often
		}
	}
}
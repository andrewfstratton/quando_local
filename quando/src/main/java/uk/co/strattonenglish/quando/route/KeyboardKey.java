package uk.co.strattonenglish.quando.route;

import java.io.IOException;
import java.awt.event.KeyEvent;

import uk.co.strattonenglish.quando.device.control.KeyControl;
import uk.co.strattonenglish.quando.device.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import uk.co.strattonenglish.quando.common.JSON;

public class KeyboardKey extends RESTRoute {
	// REST access to controlling the keyboard and mouse on the local machine
	// Note: the factory should return a dummy KeyControl for servers
	private static KeyControl keyControl = Controllers.getKeyControl();

	@Override
	public String handle_REST(HttpServletRequest request) throws IOException {
		StringBuffer result = new StringBuffer();
		if (keyControl == null) {
			result.append("{'error':'cloud deploy'}");
		} else {
			try {
				JSON jso = new JSON(request);
				jso = jso.createOnKey("val");

				String key = jso.getString("key");
				boolean press = jso.getBoolean("press", false);
				boolean shift = jso.getBoolean("shift", false);
				boolean ctrl = jso.getBoolean("ctrl", false);
				boolean alt = jso.getBoolean("alt", false);
				boolean command = jso.getBoolean("command", false);

				if (shift) {
					keyControl.pressKeyCode(KeyEvent.VK_SHIFT);
				}
				if (ctrl) {
					keyControl.pressKeyCode(KeyEvent.VK_CONTROL);
				}
				if (alt) {
					keyControl.pressKeyCode(KeyEvent.VK_ALT);
				}
				if (command) {
					keyControl.pressKeyCode(KeyEvent.VK_META);
				}
				keyControl.press_release_Key(key, press);
				if (command) {
					keyControl.releaseKeyCode(KeyEvent.VK_META);
				}
				if (alt) {
					keyControl.releaseKeyCode(KeyEvent.VK_ALT);
				}
				if (ctrl) {
					keyControl.releaseKeyCode(KeyEvent.VK_CONTROL);
				}
				if (shift) {
					keyControl.releaseKeyCode(KeyEvent.VK_SHIFT);
				}

				// System.out.println("handled by Control Key Route");
				result.append("{}");
			} catch (JSONException ex) {
				System.out.println("Malformed JSON received");
				result.append("{err: 'Malformed JSON received'}");
			}
		}
		return result.toString();
	}

}

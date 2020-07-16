package uk.co.strattonenglish.quando.route;

import java.io.IOException;

import uk.co.strattonenglish.quando.device.control.KeyControl;
import uk.co.strattonenglish.quando.device.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import uk.co.strattonenglish.quando.common.JSON;

public class KeyboardType extends RESTRoute {
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
				String val = jso.getString("val");

				if (val != null) {
					for (char ch : val.toCharArray()) {
						keyControl.typeKey(Character.toString(ch), KeyControl.DEFAULT_TYPING_DELAY);
					}
				}

				// System.out.println("handled by Control Type Route");

				result.append("{}");
			} catch (JSONException ex) {
				System.out.println("Malformed JSON received");
				result.append("{err: 'Malformed JSON received'}");
			}
		}
		return result.toString();
	}

}

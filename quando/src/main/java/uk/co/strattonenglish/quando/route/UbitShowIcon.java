package uk.co.strattonenglish.quando.route;

import uk.co.strattonenglish.quando.device.control.UbitControl;
import uk.co.strattonenglish.quando.device.Controllers;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import uk.co.strattonenglish.quando.common.JSON;


public class UbitShowIcon extends RESTRoute {
	// REST access to control a micro bit on the local machine though serial port
	// Note: the factory should return a dummy UbitControl for servers
	private static UbitControl ubitControl = Controllers.getUbitControl();

	@Override
	public String handle_REST(HttpServletRequest request) throws IOException {
		StringBuffer result = new StringBuffer();
		try {
			JSON jso = new JSON(request);
			int val = jso.getInteger("val", -1);

			if (val != -1) {
				ubitControl.icon(val);
			}
			result.append("{}");
		} catch (JSONException ex) {
			System.out.println("Malformed JSON received");
			result.append("{err: 'Malformed JSON received'}");
		}
		return result.toString();
	}
}

package uk.co.strattonenglish.quando.route;

import uk.co.strattonenglish.quando.device.control.UbitControl;
import uk.co.strattonenglish.quando.device.Controllers;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import uk.co.strattonenglish.quando.common.JSON;


public class UbitServo extends RESTRoute {
	// REST access to control a micro bit on the local machine though serial port
	// Note: the factory should return a dummy UbitControl for servers
	private static UbitControl ubitControl = Controllers.getUbitControl();

	@Override
	public String handle_REST(HttpServletRequest request) throws IOException {
		StringBuffer result = new StringBuffer();
		try {
			JSON jso = new JSON(request);
			jso = jso.createOnKey("val");
			int servo = jso.getInteger("servo", -1);
			int angle = jso.getInteger("angle", -1);

			if ((servo != -1) && (angle != -1)) {
				ubitControl.servo(servo, angle);
			}
			result.append("{}");
		} catch (JSONException ex) {
			System.out.println("Malformed JSON received");
			result.append("{err: 'Malformed JSON received'}");
		}
		return result.toString();
	}
}

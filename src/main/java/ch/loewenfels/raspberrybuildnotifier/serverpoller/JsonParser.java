package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class JsonParser {

	private static final String USER_AGENT = "Mozilla/5.0";

	public BuildInformationDto get() {
		try {
			//String url = "http://maximal-helper-179916.appspot.com/a/build/put/get";
			String url = "http://localhost:800/server.json";
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			String jsonData = EntityUtils.toString(response.getEntity());
			JSONObject obj = new JSONObject(jsonData);
			System.out.println("name: " + obj.getString("name"));
			JSONObject build = obj.getJSONObject("build");
			System.out.println("timestamp: " + build.getString("timestamp"));
			System.out.println("status: " + build.getString("status"));
			return new BuildInformationDto("a", build.getString("status"), LocalDateTime.now());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BuildInformationDto("a", "SUCCESS", LocalDateTime.now());
	}

}

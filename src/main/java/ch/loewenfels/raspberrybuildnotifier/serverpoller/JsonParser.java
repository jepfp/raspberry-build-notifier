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
            final String url = "http://maximal-helper-179916.appspot.com/rest/build/get";
            //String url = "http://192.168.43.23:800/server.json";
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            final String jsonData = EntityUtils.toString(response.getEntity());
            final JSONObject obj = new JSONObject(jsonData);
            System.out.println("name: " + obj.getString("name"));
            final JSONObject build = obj.getJSONObject("build");
            System.out.println("timestamp: " + build.getString("timestamp"));
            System.out.println("status: " + build.getString("status"));
            return new BuildInformationDto("a", build.getString("status"), LocalDateTime.now());
        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new BuildInformationDto("a", "SUCCESS", LocalDateTime.now());
    }
}

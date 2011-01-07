/*
 * Jinx is Copyright 2010 by Jeremy Brooks
 *
 * This file is part of Jinx.
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.jeremybrooks.jinx;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import net.jeremybrooks.jinx.logger.JinxLogger;


public class HTTPRequestPoster {

    /**
     * Sends an HTTP GET request to a url
     *
     * @param endpoint - The URL of the server. (Example: " http://www.yahoo.com/search")
     * @param requestParameters - all the request parameters (Example: "param1=val1&param2=val2"). Note: This method will add the question mark (?) to the request - DO NOT add it yourself
     * @return - The response from the end point
     */
    public static String sendGetRequest(String endpoint, String requestParameters) {
	String result = null;
	if (endpoint.startsWith("http://")) {
	    // Send a GET request to the servlet
	    try {
		// Construct data
		StringBuilder data = new StringBuilder();
		// Send data
		String urlStr = endpoint;
		if (requestParameters != null && requestParameters.length() > 0) {
		    urlStr += "?" + requestParameters;
		}
		JinxLogger.getLogger().log("sendGetRequest URL is " + urlStr);

		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
		    sb.append(line);
		}
		rd.close();
		result = sb.toString();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return result;
    }


    /**
     * Sends data as a post request, returning the server response.
     *
     * @param endpoint URL to send data to.
     * @param data the data to post to the remote server.
     * @return server response.
     * @throws JinxException if there are any errors.
     */
    public static String post(String endpoint, String data) throws JinxException {
	//System.out.println("POST: " + endpoint + " " + data);
	DataOutputStream out = null;
	BufferedReader in = null;
	StringBuilder sb = new StringBuilder();

	try {

	    // Send data
	    URL url = new URL(endpoint);
	    URLConnection conn = url.openConnection();
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    conn.setUseCaches(false);
	    conn.setConnectTimeout(30000);
	    conn.setReadTimeout(600000);    // 10 minutes
	    out = new DataOutputStream(conn.getOutputStream());
	    out.writeBytes(data);
	    out.flush();

	    // Get the response
	    in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while ((line = in.readLine()) != null) {
		sb.append(line);
	    }

	} catch (Exception e) {
	    throw new JinxException("Error while performing http post operation.", e);
	} finally {
	    try {
		if (out != null) {
		    out.close();
		}
	    } catch (Exception e) {
		// ignore
	    } finally {
		try {
		    if (in != null) {
			in.close();
		    }
		} catch (Exception e) {
		    // ignore
		}
	    }
	}

	return sb.toString();
    }


}

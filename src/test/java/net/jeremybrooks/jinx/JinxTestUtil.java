/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.api.AuthApi;
import net.jeremybrooks.jinx.dto.Frob;
import net.jeremybrooks.jinx.dto.Token;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.Properties;

public class JinxTestUtil {

	private static Properties properties = null;
		
	public JinxTestUtil()
	{}
	
	public static Token login() throws Exception
    {
		JinxLogger.setLogger(new StdoutLogger());
		if(properties==null)
		{
			properties = new Properties();
            properties.load(JinxTestUtil.class.getResourceAsStream("/secret.properties"));
//			properties.load(new FileReader(new File("./test/resources/flickr.properties")));
			JinxLogger.getLogger().log(properties.toString());
		}
		
			Token token = null;
			Jinx jinx = Jinx.getInstance();
			File tokenFile = new File((String)properties.get("path.to.token"));
			if(tokenFile.exists())
			{
				token = new Token();
				// load token and initialize Jinx
				token.load(tokenFile);
				Jinx.getInstance().init((String)properties.get("flickr.key"),(String)properties.get("flickr.secret"), token);
			}
			else
			{
	            jinx.init((String)properties.get("flickr.key"),(String)properties.get("flickr.secret"));
	            Frob frob = AuthApi.getInstance().getFrob(JinxConstants.PERMS_WRITE);
	
	            JinxLogger.getLogger().log("Go to this URL to authorize the application:");
	            JinxLogger.getLogger().log(frob.getLoginUrl());
	
	            Desktop desktop = Desktop.getDesktop();
	            desktop.browse(URI.create(frob.getLoginUrl()));

                System.out.println("Waiting 30 seconds for app authorization...");
                Thread.sleep(30000);
//	            System.out.print("When you have authorized the app, press Enter to continue...");
//	            System.in.read();
	            token = AuthApi.getInstance().getToken(frob);
	            if (token.getNsid()!=("")) {
	                JinxLogger.getLogger().log("Authorization successful.");
	
	                File file = new File((String)properties.get("path.to.token"));
	                JinxLogger.getLogger().log(file.getAbsolutePath());
	
	                token.store(file); // put the token in the Jinx class instance jinx.setToken(token);
	                // // now you can do all the cool stuff....
	                }
	                else {
	                JinxLogger.getLogger().log("Authorization failed.");
	                //JinxLogger.getLogger().log("Error code: " + token.getErrorCode() + " - " + token.getErrorMessage()); System.exit(1);
	            }
	        }
	        return token;
    }
}
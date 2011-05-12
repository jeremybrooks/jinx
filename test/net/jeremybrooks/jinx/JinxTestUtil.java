package net.jeremybrooks.jinx;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.Properties;

import net.jeremybrooks.jinx.api.AuthApi;
import net.jeremybrooks.jinx.dto.Frob;
import net.jeremybrooks.jinx.dto.Token;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;

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
			properties.load(new FileReader(new File("./test/flickr.properties")));
			JinxLogger.getLogger().log(properties.toString());
		}
		
			Token token = null;
			Jinx jinx = Jinx.getInstance();
			File tokenFile = new File((String)properties.get("flickt.tokenfile"));
			if(tokenFile.exists())
			{
				token = new Token();
				// load token and initialize Jinx
				token.load(tokenFile);
				Jinx.getInstance().init((String)properties.get("flickr.api_key"),(String)properties.get("flickr.secret"), token);
			}
			else
			{
	            jinx.init((String)properties.get("flickr.api_key"),(String)properties.get("flickr.secret"));
	            Frob frob = AuthApi.getInstance().getFrob(JinxConstants.PERMS_READ);
	
	            JinxLogger.getLogger().log("Go to this URL to authorize the application:");
	            JinxLogger.getLogger().log(frob.getLoginUrl());
	
	            Desktop desktop = Desktop.getDesktop();
	            desktop.browse(URI.create(frob.getLoginUrl()));
	
	            System.out.print("When you have authorized the app, press Enter to continue...");
	            System.in.read();
	            token = AuthApi.getInstance().getToken(frob);
	            if (token.getNsid()!=("")) {
	                JinxLogger.getLogger().log("Authorization successful.");
	
	                File file = new File((String)properties.get("flickt.tokenfile"));
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
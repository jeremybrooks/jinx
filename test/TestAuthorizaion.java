
import java.io.File;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.api.AuthApi;
import net.jeremybrooks.jinx.dto.Frob;
import net.jeremybrooks.jinx.dto.Token;

/**
 * This test class demonstrates how to perform authorization for your application.
 *
 * Your application must have its own key and secret. These can be obtained here:
 * http://www.flickr.com/services/apps/create/apply/?
 *
 * 
 * @author jeremyb
 */
public class TestAuthorizaion {

    /*
     * Get a key for your app here:
     * http://www.flickr.com/services/apps/create/apply/?
     */
    private static final String KEY = "";
    private static final String SECRET = "";

    public static void main(String[] args) {

	File tokenFile = new File("/tmp/myToken");
	Token token = null;

	try {
	    // Attempt to initialize with an existing token
	    if (tokenFile.exists()) {
		token = new Token();
		token.load(tokenFile);
		Jinx.getInstance().init(KEY, SECRET, token);

	    } else {

		// No token exists, so initialize with our key and secret,
		// then prompt user to authorize our application
		Jinx.getInstance().init(KEY, SECRET);
		Frob frob = AuthApi.getInstance().getFrob(JinxConstants.PERMS_READ);

		// Send user to the login URL
		// In a real application, you would probably do this in a GUI
		// of some sort
		System.out.println("Please go to this URL and allow access: " + frob.getLoginUrl());
		System.out.println("After you have authorized this application, press a key.");

		// Wait for user to press a key
		System.in.read();

		// Complete authorization by getting the token and telling
		// Jinx about it
		token = AuthApi.getInstance().getToken(frob);
		Jinx.getInstance().setToken(token);

		System.out.println("Authorization successful.");
		
		// The token can be stored for future use, and is valid until
		// the user revokes access
		token.store(new File("/tmp/myToken"));
	    }
	} catch (Exception e) {
	    System.out.println("Oops, something went wrong!");
	    e.printStackTrace();
	}
    }
}


import java.io.File;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.api.PhotosApi;
import net.jeremybrooks.jinx.dto.Photo;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.dto.SearchParameters;
import net.jeremybrooks.jinx.dto.Token;

/**
 *
 * @author jeremyb
 */
public class TestSearch {

    private static final String KEY = "";

    private static final String SECRET = "";



    public static void main(String[] args) {
	
	File tokenFile = new File("/tmp/myToken");
	Token token = new Token();

	try {
	    // load token and initialize Jinx
	    token.load(tokenFile);
	    Jinx.getInstance().init(KEY, SECRET, token);

	    // Create a search request
	    SearchParameters search = new SearchParameters();

	    // Search our photos. Excluding this will search everybody's photos.
	    search.setUserId(token.getNsid());

	    // Search by tag, requiring all tags to be found
	    search.setTagMode(JinxConstants.TAG_MODE_ALL);
	    search.setTags("sanfrancisco, fog");

	    // Return 10 items per page
	    search.setPerPage(10);
	    
	    // perform search
	    Photos photos = PhotosApi.getInstance().search(search);
	    System.out.println("Found " + photos.getTotal() + " results.");
	    for (Photo p : photos.getPhotos()) {
		System.out.println(p.getTitle() + " <" + p.getId() + ">");
	    }

	} catch (Exception e) {
	    System.out.println("Oops! Something is broken somewhere.");
	    e.printStackTrace();
	}
    }
}

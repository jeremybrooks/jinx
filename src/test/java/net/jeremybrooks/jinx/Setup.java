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


import net.jeremybrooks.jinx.dto.Token;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is used to set up the Flickr auth token and key/secret for
 * test cases.
 *
 * Test cases should call Setup.doSetup() in the setUpClass method.
 * 
 * @author jeremyb
 */
public class Setup {


    public static void doSetup() throws Exception {
        Properties p = new Properties();
	InputStream in = Setup.class.getResourceAsStream("/secret.properties");
	p.load(in);

	String pathToTokenFile = p.getProperty("path.to.token");
	String key = p.getProperty("flickr.key");
	String secret = p.getProperty("flickr.secret");
	Token t = new Token();
	File file = new File(pathToTokenFile);
	t.load(file);

	Jinx jinx = Jinx.getInstance();
	jinx.init(key, secret, t);
    }
}

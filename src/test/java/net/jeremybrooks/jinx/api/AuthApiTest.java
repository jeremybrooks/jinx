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
package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.JinxTestUtil;
import net.jeremybrooks.jinx.dto.Token;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthApiTest {

	@Before
	public void setup() throws Exception
	{
		JinxTestUtil.login();
	}
	
	@Test
	public void test() throws Exception
	{
		Token token = JinxTestUtil.login();
		Assert.assertNotNull(token);
	}
}

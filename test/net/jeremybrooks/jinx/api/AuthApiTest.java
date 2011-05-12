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

package net.jeremybrooks.jinx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class JinxUtilsTest {

	@Test
	public void testValidateParams() throws Exception {
		String x = "whatever";
		List<String> list = new ArrayList<String>();
		list.add(x);
		JinxUtils.validateParams(x);
		JinxUtils.validateParams(list);
		JinxUtils.validateParams(x, list);
		Exception exception = null;
		Exception exception1 = null;

		try {
			JinxUtils.validateParams(null);
			fail();
		} catch (Exception e) {
			exception = e;
		}
		try {
			list.add(null);
			JinxUtils.validateParams(list);
			fail();
		} catch (Exception e) {
			exception1 = e;
		}
		assertNotNull(exception);
		assertNotNull(exception1);
	}

	@Test
	public void testToFlickrPrivacy() throws Exception {
		assertEquals(1, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyPublic));
		assertEquals(2, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyFriends));
		assertEquals(3, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyFamily));
		assertEquals(4, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyFriendsAndFamily));
		assertEquals(5, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyPrivate));
	}
}
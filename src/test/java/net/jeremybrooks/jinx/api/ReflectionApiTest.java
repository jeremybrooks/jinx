/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.response.reflection.MethodInfo;
import net.jeremybrooks.jinx.response.reflection.Methods;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class ReflectionApiTest {
  private static ReflectionApi reflectionApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    reflectionApi = new ReflectionApi(JinxApiTestCommon.getJinx());
  }

  @Test
  public void testGetMethods() throws Exception {
    Methods methods = reflectionApi.getMethods();
    assertNotNull(methods);
    assertNotNull(methods.getMethods());
    assertTrue(methods.getMethods().size() > 0);
  }

  @Test
  public void getGetMethodInfo() throws Exception {
    MethodInfo methodInfo = reflectionApi.getMethodInfo("flickr.photos.search");
    assertNotNull(methodInfo);
    assertNotNull(methodInfo.getMethod());
    assertEquals("flickr.photos.search", methodInfo.getMethod().getName());
    assertNotNull(methodInfo.getArguments());
    assertTrue(methodInfo.getArguments().size() > 0);
    assertNotNull(methodInfo.getErrors());
    assertTrue(methodInfo.getErrors().size() > 0);
  }
}

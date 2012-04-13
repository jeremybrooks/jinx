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

import junit.framework.Assert;
import net.jeremybrooks.jinx.JinxTestUtil;
import net.jeremybrooks.jinx.dto.Namespaces;
import net.jeremybrooks.jinx.dto.Pairs;
import net.jeremybrooks.jinx.dto.Predicates;
import net.jeremybrooks.jinx.dto.Values;
import net.jeremybrooks.jinx.logger.JinxLogger;

import org.junit.Before;
import org.junit.Test;

public class MachinetagsApiTest {

	@Before
	public void setup() throws Exception
	{
		JinxTestUtil.login();
	}
	
	@Test
	public void getValues() throws Exception
	{
    	Values values = MachinetagsApi.getInstance().getValues("bhaa","id",null,null);
    	JinxLogger.getLogger().log(values+" "+values.getValues());
        Assert.assertNotNull(values);
	}
	
	@Test
	public void getPredicates() throws Exception
	{    
        Predicates predicates = MachinetagsApi.getInstance().getPredicates("bhaa",null,null);
        JinxLogger.getLogger().log(predicates+" "+predicates.getPredicates());
        Assert.assertNotNull(predicates);
	}
	
	@Test
	public void getNamespaces() throws Exception
	{
        Namespaces namespaces = MachinetagsApi.getInstance().getNamespaces("b",null,null);
        JinxLogger.getLogger().log(namespaces+" "+namespaces.getNamespaces());
        Assert.assertNotNull(namespaces);
	}
	
	@Test
	public void getRecentValues() throws Exception
	{
		Values values = MachinetagsApi.getInstance().getRecentValues("a","a",null);
        JinxLogger.getLogger().log(values+" "+values.getValues());
        Assert.assertNotNull(values);
	}
	
	@Test
	public void getPairs() throws Exception
	{
		Pairs pairs = MachinetagsApi.getInstance().getPairs("b",null,null,null);
        JinxLogger.getLogger().log(pairs+" "+pairs.getPairs());
        Assert.assertNotNull(pairs);
	}
}

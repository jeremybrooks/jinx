package net.jeremybrooks.jinx.api;

import junit.framework.Assert;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxTestUtil;
import net.jeremybrooks.jinx.dto.Namespaces;
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
}

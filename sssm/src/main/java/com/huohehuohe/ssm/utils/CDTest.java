package com.huohehuohe.ssm.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDconfig.class)
public class CDTest {
	
	@Autowired
	private CompactDisc cd;
	
	@Test
	public void cds() {
		System.out.println(cd);
		assertNotNull(cd);
	}
}

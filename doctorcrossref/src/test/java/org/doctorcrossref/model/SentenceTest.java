package org.doctorcrossref.model;


import junit.framework.Assert;
import org.junit.Test;

public class SentenceTest {
	
	@Test
	public void testGrouping() {
 
		Sentence gp = new Sentence();
		Assert.assertEquals(gp.getSentence(), "abc123");
 
	}
}

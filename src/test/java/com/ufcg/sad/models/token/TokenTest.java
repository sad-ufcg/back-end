package com.ufcg.sad.models.token;

import com.ufcg.sad.SadApplicationTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by sampaio on 16/11/17.
 */
public class TokenTest extends SadApplicationTests {

	private Token token1;
	private Token token2;
	
    @Before
    public void setUp() {
        token1 = new Token(new Long(1));
        token2 = new Token(new Long(1));
    }

    @Test
    public void testaGetter(){
        Assert.assertNotEquals(token1.getId(),token2.getId());
        Assert.assertEquals(new Long(1), token1.getIdQuestionarioAplicado());
    }

}

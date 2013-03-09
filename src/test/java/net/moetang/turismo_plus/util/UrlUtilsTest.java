package net.moetang.turismo_plus.util;

import net.moetang.turismo_plus.util.UrlUtils.RootEntry;

import org.junit.Assert;
import org.junit.Test;

public class UrlUtilsTest extends UrlUtils {
	private UrlUtils utils = new UrlUtils();

	@Test
	public void testUriNull(){
		Assert.assertEquals(0, utils.uriToPathEntry(null).size());
	}
	
	@Test
	public void testUriFile(){
		Assert.assertEquals(1, utils.uriToPathEntry("/1.jsp").size());
	}
	
	@Test
	public void testUriIsNotStartWithRoot(){
		Assert.assertEquals(0, utils.uriToPathEntry("aa").size());
	}
	
	@Test
	public void testUriIsRoot(){
		Assert.assertEquals(1, utils.uriToPathEntry("/").size());
		Assert.assertTrue(utils.uriToPathEntry("/").get(0) instanceof RootEntry);
	}
	
	@Test
	public void testUriEndsWithRoot(){
		Assert.assertEquals(1, utils.uriToPathEntry("/aa/").size());
		Assert.assertTrue(utils.uriToPathEntry("/aa/").get(0) instanceof NormalEntry);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAnalyseTypeWithAmpAndNumSymbol(){
		utils.analyseEntryType("/d*d#L");
	}
	
	@Test
	public void testUriWithWildcard(){
		Assert.assertTrue(utils.uriToPathEntry("/*/").get(0) instanceof WildCardEntry);
	}
	
	@Test
	public void testUriWithTwoNumSymbolButIllegal(){
		Assert.assertFalse(utils.uriToPathEntry("/##/").get(0) instanceof VarEntry);
	}
	
	@Test
	public void testUriWithNumSymbol(){
		Assert.assertTrue(utils.uriToPathEntry("/##d/").get(0) instanceof RegExEntry);
		Assert.assertTrue(utils.uriToPathEntry("/#d#/").get(0) instanceof VarEntry);
	}
	
	@Test
	public void testTwo(){
		String test = "sdfsf*sdfsdf";
		String test2 = "ssfsdf**sfsfs";
		String test3 = "*ssfsdf**sfsfs";
		String test4 = "ssfsdf**sfsfs*";
		String test5 = "*ssfsdf**sfsfs*";
		System.out.println(test.split("\\*").length);
		System.out.println(test2.split("\\*").length);
		System.out.println(test3.split("\\*").length);
		System.out.println(test4.split("\\*").length);
		System.out.println(test5.split("\\*").length);
		String pathName = "sdf#dfg#dsg";
		int firstpos = pathName.indexOf("#");
		int secpos = pathName.indexOf("#", firstpos+1);
		System.out.println(firstpos+" dd "+secpos);
		System.out.println("dd".substring(0, 0).length());
		System.out.println("dd".substring(1+1).length());
	}
	
	@Test
	public void testWildcartEntryMatch(){
		WildCardEntry we = new WildCardEntry("/*aaaa*bbbb", null);
		
		NormalEntry ne = new NormalEntry("/aabbaaaaddbbbb", null);
		Assert.assertTrue(we.match(ne));
		
		NormalEntry ne2 = new NormalEntry("/bbbb", null);
		Assert.assertFalse(we.match(ne2));
	}
}

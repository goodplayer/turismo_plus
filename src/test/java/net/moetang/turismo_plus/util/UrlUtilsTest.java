package net.moetang.turismo_plus.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	@Test
	public void testDouhao(){
        String[] routes = "dsf,sdfdsf.sdfsd,sdfsd".split(",");
        Assert.assertEquals(3, routes.length);
        Assert.assertEquals("dsf", routes[0]);
        Assert.assertEquals("sdfdsf.sdfsd", routes[1]);
        Assert.assertEquals("sdfsd", routes[2]);
	}
	
	@Test
	public void testWildcard(){
		//*.jsp
		Pattern p = Pattern.compile("\\S+\\.jsp$");
		Matcher m = p.matcher("sdfkl_sdf-e%derjwof.jsp");
		Assert.assertTrue(m.matches());
	}
	
	@Test
	public void testMapOrder(){
		String key1 = "key1";
		String key2 = "sldfjslf";
		String key3 = "vnqwelkj";
		String key4 = "key2";
		
		Map<String, String> hashMap = new HashMap<>();
		Map<String, String> linkedHashMap = new LinkedHashMap<>();
		
		hashMap.put(key1, key1);
		hashMap.put(key2, key2);
		hashMap.put(key3, key3);
		hashMap.put(key4, key4);

		linkedHashMap.put(key1, key1);
		linkedHashMap.put(key2, key2);
		linkedHashMap.put(key3, key3);
		linkedHashMap.put(key4, key4);
		
		System.out.println("==========================");
		for(String key : hashMap.keySet()){
			System.out.println(key);
		}
		System.out.println();
		for(String key : linkedHashMap.keySet()){
			System.out.println(key);
		}
		System.out.println("==========================");
	}
	
	@Test(expected=NullPointerException.class)
	public void testEnum(){
		DD s = null;
		switch (s) {
		case ss:
			System.out.println("--a");
			break;
		case dd:
			System.out.println("--a");
			break;
		default:
			System.out.println("--b");
			break;
		}
	}
	private enum DD{
		ss, dd
	}
}

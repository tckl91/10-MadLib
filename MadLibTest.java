package madLib;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MadLibTest extends MadLib {
	
	MadLib ml = new MadLib();
	
	ArrayList<String> rawRead = new ArrayList<String>();
	ArrayList<String> test1 = new ArrayList<String>();
	ArrayList<String> test2 = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testReadFile(){
		rawRead = ml.readFile("src/madLib/test.txt");
		test1.add("[name]");
		test1.add("am");
		test1.add("happy");
    	assertEquals(rawRead, test1);
	}
	
	@Test
	public void testAsk() {
		ByteArrayInputStream in = new ByteArrayInputStream("John".getBytes()); 
		System.setIn(in);
    	assertEquals("John", ml.ask("John"));
		System.setIn(System.in);
		
		ByteArrayInputStream in2 = new ByteArrayInputStream("Mary".getBytes()); 
		System.setIn(in2);
    	assertNotEquals("John", ml.ask("Mary"));
		System.setIn(System.in);
	}
	
	@Test
	public void testPlay() {
		rawRead = ml.readFile("src/madLib/test.txt");
		ByteArrayInputStream in3 = new ByteArrayInputStream("Jason".getBytes()); 
		System.setIn(in3);
		ml.play(rawRead);
		test2.add("Jason");
		test2.add("am");
		test2.add("happy");
		System.out.println(ml.finalStory);
		assertEquals(ml.finalStory, test2);
		System.setIn(System.in);
	}
}

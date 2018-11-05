import static org.junit.Assert.*;

import javax.xml.soap.Node;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnitTest {

	
	BST test = new BST();
	Record rec = new Record(1, "Java", "Yohannes", null);
	
	@Test
	
	
	public void testGetRecord() throws Throwable {
		
		Node root = null;
		String keyword = "buildings";
		Record result = (Record) test.getRecords((BST.Node)root, keyword);
		
		assertEquals(result, root);
	}
	

	public void testDeleteMethod() throws Throwable {
		
		String keyword = "database";
		Node focusNode = null;
		Node result = (Node) test.delete(keyword, (BST.Node) focusNode);

		assertEquals(result, focusNode);
		
	
	}
	
	public void testContains() throws Throwable {
		String keyword = "medical";
		Node root = null;
		boolean result = (boolean) test.contains(keyword, (BST.Node) root);
		
		assertEquals(result, root);
	}
	
	
	
	
	
	

}

package TalOL.SpringRest;
import java.net.MalformedURLException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;

import TalOL.SpringRest.TestTools;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for spring App.
 */



public class AppTest extends TestCase {
	

    public AppTest( String testName )
    {
    	
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testGetItemsResponse()
    {
    	final Response response = RestAssured.get(TestTools.API_ROOT);
    	assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    public void testGetCreatedItemByItemNumber() throws MalformedURLException, JsonProcessingException
    {
    	TestTools.getInstance();
    	final Item item = TestTools.createRandomItem();
    	final String location = TestTools.createItemAsUri(item);
    	final Response response = RestAssured.get(location);
    	assertEquals(item.getItemNumber(), (int)response.jsonPath().get("itemNumber"));
    }
    
    public void testNotFound() {
        Response response = RestAssured.get(TestTools.API_ROOT + "/" + 4);
         
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());
    }
    
}

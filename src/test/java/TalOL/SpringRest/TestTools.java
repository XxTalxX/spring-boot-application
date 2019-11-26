package TalOL.SpringRest;
import java.net.MalformedURLException;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import TalOL.SpringRest.Item;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
public class TestTools {
	
	private static TestTools _testTools;
	private static ObjectMapper objectMapper;
	
	public static TestTools getInstance() throws MalformedURLException
	{
		if(_testTools == null)
		{
			_testTools = new TestTools();
		}
		return _testTools;

	}
	@LocalServerPort
	private static int port = 8080;
	
	public static final String API_ROOT = "http://localhost:" + port +"/items";
	public static String itemNames[] = {"steel","wood","ball","box"};
	
	public static Item createRandomItem()
	{		
		final Item item = new Item();
		item.setItemNumber(ThreadLocalRandom.current().nextInt(1,10+1));
		item.setItemName(itemNames[ThreadLocalRandom.current().nextInt(0,itemNames.length)]);
		item.setInventoryCode(ThreadLocalRandom.current().nextInt(11,21+1));
		item.setItemAmount(ThreadLocalRandom.current().nextInt(3,6+1));
		
		return item;
		
	}
	
	public static String createItemAsUri(Item item) throws JsonProcessingException
	{
		objectMapper = new ObjectMapper();
		final Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(objectMapper.writeValueAsString(item)).post(API_ROOT);
		
		return API_ROOT + "/" + response.jsonPath().get("itemNumber");
 	}
	


}

package TalOL.SpringRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class InventoryController {

	
	@Autowired
	private ItemService itemService;

	@RequestMapping(method=RequestMethod.GET, value="/items")
	@ApiOperation(value="Find all items available in the inventory's stock",
	notes = "Only a GET action is required",
	response = Item.class)
	public List<Item> getAllItems()
	{
		return itemService.getAllItems();
	}

	@RequestMapping(method=RequestMethod.POST, value="/items/{itemNumber}")
	@ApiOperation(value="Gets a specific item if it is available",
	notes = "Provide an item number to look up specific item available in the inventory.",
	response = Item.class)
	public Item getItem(@ApiParam(value = "Item number for the item you need to retrieve" , required = true)
	@PathVariable int itemNumber)
	{
		
		return itemService.getItem(itemNumber);
	}

	@RequestMapping(method=RequestMethod.POST, value="/items")
	@ApiOperation(value="Adds a new item to the stock",
	notes = "Provide a json presented in the Models section representing an item",
	response = Item.class)
		public void addItem(@ApiParam(value = "The Item object to be sent via POST method" , required = true)
		@RequestBody Item item)
	{
		itemService.addOrUpdateItem(item);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/items/{itemNumber}")
	@ApiOperation(value="Update an existing item already in the inventory",
	notes = "Provide the new item's credentials and the item's number to edit",
	response = Item.class)
	public void updateItem(@ApiParam(value = "The Item object to be sent via PUT method", required = true) 
			@RequestBody Item item,
			@ApiParam(value = "The item number of the item you want to update" , required = true)
	@PathVariable int itemNumber)
	{
		itemService.addOrUpdateItem(item);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/items/{itemNumber}")
	@ApiOperation(value="Deletes a specific item from the inventory",
	notes = "Provide the item number and a DELETE mapping request",
	response = Item.class)
	public void deleteItem(@ApiParam(value = "the item number of the item you want to delete", required = true)
	@PathVariable int itemNumber)
	{

	 itemService.delateItem(itemNumber);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/items/deposit/{itemNumber}")
	@ApiOperation(value="deposits more quantity of a existing item in the inventory",
notes = "Provide a single integer with JSON header that represents the the item amount you want to increase."
		+ " must be positive",
	response = Item.class)
	public void deposit(
			@ApiParam(value = "the item number representing the existing item in the inventory", required = true) 
			@PathVariable int itemNumber,
			@ApiParam(value = "the integer value representing the item amount you want to increase" ,
			required = true)
			@RequestBody int itemAmount)
	{
		itemService.deposit(itemNumber, itemAmount);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/items/withdrawal/{itemNumber}")
	@ApiOperation(value="WithDrawals certin amount of specific existing item in the inventory if possible",
	notes = "Provide a single integer with JSON header that represents the the item amount you want to withdrawal."
			+ " must be positive and smaller then current item amount in stock",
	response = Item.class)
	public void withDrawal(
			@ApiParam(value = "the item number representing the existing item in the inventory", required = true) 
			@PathVariable int itemNumber,
			@ApiParam(value = "the integer value representing the item amount you want to withdrawal."
					+ " must be less then current item amount" , required = true) 
			@RequestBody int itemAmount)
	{
		itemService.withDrawal(itemNumber, itemAmount);

	}
	

}

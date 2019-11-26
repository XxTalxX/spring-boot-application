package TalOL.SpringRest;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.ExpandVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getAllItems()	
	{
		List<Item> items = new ArrayList<>();
		itemRepository.findAll().forEach(items::add);
		return items;
	}
	
	public Item getItem(int itemNumber)
	{
		if(itemRepository.exists(itemNumber)){
		return itemRepository.findOne(itemNumber);
		}
		else{
			throw new RuntimeException("Item is not exists");
		}
	}
	
	public void addOrUpdateItem(Item item)
	{
		itemRepository.save(item);
	}
	
	public void delateItem(int itemNumber)
	{
		if(itemRepository.exists(itemNumber)){
		itemRepository.delete(itemNumber);
		}else{ throw new RuntimeException("No such Item to delete");}
	}
	
	public void deposit(int itemNumber, int itemAmount)
	{
		if(itemAmount < 1 )
		{
			throw new RuntimeException("Enter positive number to deposit");
		}
		else if(itemRepository.exists(itemNumber)){
		Item item = itemRepository.findOne(itemNumber);
		item.setItemAmount(item.getItemAmount() + itemAmount);
		itemRepository.save(item);
		}
		else{
			throw new RuntimeException("List an item object with this item numebr first");
		}
	}
	
	public void withDrawal(int itemNumber, int itemAmount)
	{
		if(!itemRepository.exists(itemNumber)){
			throw new RuntimeException("List an item object with this item numebr first");
		}
		else {
		Item item = itemRepository.findOne(itemNumber);
		if(item.getItemAmount() < itemAmount)
		{
			throw new RuntimeException("Cant withDrawal " + itemAmount + " items because there are only "
		+ item.getItemAmount() + " items available.");
		}
		else if(itemAmount < 1)
		{
			throw new RuntimeException("Enter positive number to withDrawal");
		}
		else{
		item.setItemAmount(item.getItemAmount() - itemAmount);
		itemRepository.save(item);
		}
	}
}}

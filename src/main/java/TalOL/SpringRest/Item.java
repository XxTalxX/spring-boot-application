package TalOL.SpringRest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "The item specifications")
public class Item {
	
	@Id
	@ApiModelProperty(notes = "The unique item number of the item")
	int itemNumber;
	@Column(nullable = false)
	@ApiModelProperty(notes = "The item name")
	String itemName;
	@Column(nullable = false)
	@ApiModelProperty(notes = "The quantity of this specific item available in stock")
	int itemAmount;
	@Column(nullable = false, unique = true)
	@ApiModelProperty(notes = "The inventory code of the item")
	int inventoryCode;
	
	public Item(){}
	
	public Item(int itemNumber, String itemName, int itemAmount, int inventoryCode) {
		super();
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.itemAmount = itemAmount;
		this.inventoryCode = inventoryCode;
	}
	
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
	public int getInventoryCode() {
		return inventoryCode;
	}
	public void setInventoryCode(int inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

}

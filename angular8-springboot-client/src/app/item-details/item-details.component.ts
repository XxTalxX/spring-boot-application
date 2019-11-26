import { Component, OnInit, Input } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { ItemListComponent } from '../item-list/item-list.component';
import { Router, ActivatedRoute } from '@angular/router';
 
@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})

export class ItemDetailsComponent implements OnInit {
	
	
	itemNumber: number;
	item: Item;

  constructor(private route: ActivatedRoute, private router: Router, private itemService: ItemService) { }

  ngOnInit() {
	  this.item = new Item();
	  this.itemNumber = this.route.snapshot.params['itemNumber'];
	  this.itemService.getItem(this.itemNumber).subscribe(data => {
		  console.log(data)
		  this.item = data;
		}, error => console.log(error));
	}
	
	list()
	{
		this.router.navigate(['items']);
	}
}

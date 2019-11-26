import { Component, OnInit } from '@angular/core';
import { ItemDetailsComponent } from '../item-details/item-details.component';
import { Observable } from 'rxjs';
import { ItemService } from '../item.service';
import { Item } from '../item';
import { Router } from '@angular/router';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
	items: Observable<Item[]>;
	
  constructor(private itemService: ItemService, private router: Router) { }

  ngOnInit() {
	  this.reloadData();
  }
  
  reloadData(){
	  this.items = this.itemService.getItemsList();
  }
  
  deleteItem(itemNumber: number){
	  this.itemService.deleteItem(itemNumber).subscribe(
	  data => {
		  console.log(data);
		  this.reloadData();
	  },
	  error => console.log(error));
	  
  }
  
  itemDetails(itemNumber: number){
	  this.router.navigate(['details',itemNumber]);
  }

}

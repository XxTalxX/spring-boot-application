import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import {ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent implements OnInit {

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

updateItem(){
	this.itemService.updateItem(this.itemNumber,this.item).subscribe(data => console.log(data), error => console.log(error));
	this.item = new Item();
	this.gotoList();
}

onSubmit() {
	this.updateItem();
}

gotoList()
{
	this.router.navigate(['/items']);
}
}

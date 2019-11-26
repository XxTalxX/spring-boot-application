import { Component, OnInit } from '@angular/core';
import { ItemService } from '../item.service';
import { Item } from '../item';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

	item: Item = new Item();
	submitted = false;

  constructor(private itemService: ItemService, private router: Router) { }

  ngOnInit() {
  }

  save() {
	  this.itemService.createItem(this.item).subscribe(data => console.log(data), error => console.log(error));
	  this.item = new Item();
	  this.gotoList();
  }
  
  onSubmit()
  {
	  this.submitted = true;
	  this.save();
  }
  
  gotoList(){
	  this.router.navigate(['/items']);
  }
  
}

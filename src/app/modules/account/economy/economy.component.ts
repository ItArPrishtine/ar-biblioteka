import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-economy',
  templateUrl: './economy.component.html',
  styles: [
    ':host{ display: flex; flex: 1; min-height: 100% }'
  ]
})
export class EconomyComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-biblioteka',
  templateUrl: './biblioteka.component.html',
  styles: [
    ':host{ display: flex; flex: 1; min-height: 100% }'
  ]
})
export class BibliotekaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

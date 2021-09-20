import { Component, Input, OnInit } from '@angular/core';
import { IMAGEURLS } from '../../constants/GeneralConstant';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss']
})
export class LoaderComponent implements OnInit {
  loaderPath = IMAGEURLS.LOADER;
  @Input() showLoader;

  constructor() { }

  ngOnInit(): void {
  }

}

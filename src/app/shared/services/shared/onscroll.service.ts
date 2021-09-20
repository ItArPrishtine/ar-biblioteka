import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class OnscrollService {
  private scrollTimes = 0;
  private onScrollEvent: BehaviorSubject<boolean> = new BehaviorSubject(false);

  triggerScroll() {
    this.scrollTimes +=1;

    if (this.scrollTimes === 2) {
      this.onScrollEvent.next(true)
      this.scrollTimes = 0;
    }
  }

  onScrollTrigger(): Observable<boolean> {

    return this.onScrollEvent.asObservable();
  }
}

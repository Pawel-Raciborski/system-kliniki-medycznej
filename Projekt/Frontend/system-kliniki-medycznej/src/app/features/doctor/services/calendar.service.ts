import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor() { }

  setColor(status: string) {

    if(status === "Umówiono"){
      return "#f3de8a";
    }
    if(status === "Potwierdzono"){
      return "#cae7b9";
    }
    if(status === "Zakończona"){
      return "lightgray";
    }
    return "#eb9486";
  }

  setDarker(status: string) {
    if(status === "Umówiono"){
      return "#e2cd7a";
    }
    if(status === "Potwierdzono"){
      return "#cae7b9";
    }
    if(status === "Zakończona"){
      return "lightgray";
    }
    return "#eb9486";
  }
}

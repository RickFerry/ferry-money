import { Component } from "@angular/core";
import { ToastyConfig } from "ng2-toasty";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {

  constructor(private config: ToastyConfig) {
    this.config.theme = "bootstrap";
  }
}

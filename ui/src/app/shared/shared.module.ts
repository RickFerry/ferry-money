import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MessageComponent } from "./message/message.component";

@NgModule({
  imports: [CommonModule],
  declarations: [MessageComponent],
  exports: [MessageComponent],
})
export class SharedModule {}

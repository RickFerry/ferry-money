import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { HttpModule } from "@angular/http";
import { AppComponent } from "./app.component";
import { CoreModule } from "./core/core.module";
import { LancamentoService } from "./lancamentos/lancamento.service";
import { LancamentosModule } from "./lancamentos/lancamentos.module";
import { PessoaService } from "./pessoas/pessoa.service";
import { PessoasModule } from "./pessoas/pessoas.module";

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    CoreModule,
    LancamentosModule,
    PessoasModule,
  ],
  providers: [LancamentoService, PessoaService],
  bootstrap: [AppComponent],
})
export class AppModule {}

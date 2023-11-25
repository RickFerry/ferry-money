import { LOCALE_ID, NgModule } from "@angular/core";
import { HttpModule } from "@angular/http";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { ToastyModule } from "ng2-toasty";
import { ConfirmDialogModule } from "primeng/components/confirmdialog/confirmdialog";

import { ConfirmationService } from "primeng/components/common/confirmationservice";
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
    ToastyModule.forRoot(),
    ConfirmDialogModule,
    LancamentosModule,
    PessoasModule,
  ],
  providers: [
    LancamentoService,
    PessoaService,
    ConfirmationService,
    { provide: LOCALE_ID, useValue: "pt-BR" },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

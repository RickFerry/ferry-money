import { CommonModule } from "@angular/common";
import { LOCALE_ID, NgModule } from "@angular/core";

import { LancamentoService } from "app/lancamentos/lancamento.service";
import { PessoaService } from "app/pessoas/pessoa.service";
import { ToastyModule } from "ng2-toasty";
import { ConfirmationService } from "primeng/components/common/confirmationservice";
import { ConfirmDialogModule } from "primeng/components/confirmdialog/confirmdialog";
import { ErrorHandlerService } from "./error-handler.service";
import { NavbarComponent } from "./navbar/navbar.component";

@NgModule({
  imports: [CommonModule, ToastyModule.forRoot(), ConfirmDialogModule],
  declarations: [NavbarComponent],
  exports: [NavbarComponent, ToastyModule, ConfirmDialogModule],
  providers: [
    ErrorHandlerService,
    LancamentoService,
    PessoaService,
    ConfirmationService,
    { provide: LOCALE_ID, useValue: "pt-BR" },
  ],
})
export class CoreModule {}

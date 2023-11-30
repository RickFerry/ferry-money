import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { FormsModule } from "@angular/forms";
import { CurrencyMaskModule } from "ng2-currency-mask";
import { ButtonModule } from "primeng/components/button/button";
import { CalendarModule } from "primeng/components/calendar/calendar";
import { DataTableModule } from "primeng/components/datatable/datatable";
import { DropdownModule } from "primeng/components/dropdown/dropdown";
import { InputTextModule } from "primeng/components/inputtext/inputtext";
import { InputTextareaModule } from "primeng/components/inputtextarea/inputtextarea";
import { SelectButtonModule } from "primeng/components/selectbutton/selectbutton";
import { TooltipModule } from "primeng/components/tooltip/tooltip";

import { RouterModule } from "@angular/router";
import { SharedModule } from "app/shared/shared.module";
import { LancamentoCadastroComponent } from "./lancamento-cadastro/lancamento-cadastro.component";
import { LancamentosPesquisaComponent } from "./lancamentos-pesquisa/lancamentos-pesquisa.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    SharedModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    TooltipModule,
    InputTextareaModule,
    CalendarModule,
    SelectButtonModule,
    DropdownModule,
    CurrencyMaskModule,
  ],
  declarations: [LancamentoCadastroComponent, LancamentosPesquisaComponent],
  exports: [],
})
export class LancamentosModule {}

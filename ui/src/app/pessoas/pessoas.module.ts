import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { FormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/components/button/button";
import { DataTableModule } from "primeng/components/datatable/datatable";
import { InputMaskModule } from "primeng/components/inputmask/inputmask";
import { InputTextModule } from "primeng/components/inputtext/inputtext";
import { TooltipModule } from "primeng/components/tooltip/tooltip";

import { PessoaCadastroComponent } from "./pessoa-cadastro/pessoa-cadastro.component";
import { PessoasPesquisaComponent } from "./pessoas-pesquisa/pessoas-pesquisa.component";
import { SharedModule } from "app/shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    TooltipModule,
    InputMaskModule,
  ],
  declarations: [PessoaCadastroComponent, PessoasPesquisaComponent],
  exports: [PessoaCadastroComponent, PessoasPesquisaComponent],
})
export class PessoasModule {}

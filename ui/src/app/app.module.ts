import { NgModule } from "@angular/core";
import { HttpModule } from "@angular/http";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";
import { CoreModule } from "./core/core.module";
import { LancamentoCadastroComponent } from "./lancamentos/lancamento-cadastro/lancamento-cadastro.component";
import { LancamentosPesquisaComponent } from "./lancamentos/lancamentos-pesquisa/lancamentos-pesquisa.component";
import { LancamentosModule } from "./lancamentos/lancamentos.module";
import { PessoasPesquisaComponent } from "./pessoas/pessoas-pesquisa/pessoas-pesquisa.component";
import { PessoasModule } from "./pessoas/pessoas.module";

const routes: Routes = [
  { path: "lancamentos", component: LancamentosPesquisaComponent },
  { path: "lancamentos/novo", component: LancamentoCadastroComponent },
  { path: "lancamentos/:id", component: LancamentoCadastroComponent },
  { path: "pessoas", component: PessoasPesquisaComponent },
];

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    RouterModule.forRoot(routes),
    CoreModule,
    LancamentosModule,
    PessoasModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

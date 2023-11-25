import { Component, ViewChild } from "@angular/core";

import {
  ConfirmationService,
  LazyLoadEvent,
} from "primeng/components/common/api";

import { ErrorHandlerService } from "app/core/error-handler.service";
import { ToastyService } from "ng2-toasty";
import { PessoaFiltro, PessoaService } from "./../pessoa.service";

@Component({
  selector: "app-pessoas-pesquisa",
  templateUrl: "./pessoas-pesquisa.component.html",
  styleUrls: ["./pessoas-pesquisa.component.css"],
})
export class PessoasPesquisaComponent {
  totalRegistros = 0;
  filtro = new PessoaFiltro();
  pessoas = [];
  @ViewChild("tabela") tabela;

  constructor(
    private pessoaService: PessoaService,
    private handler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private toasty: ToastyService
  ) {}

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.pessoaService.pesquisar(this.filtro).then((resultado) => {
      this.totalRegistros = resultado.total;
      this.pessoas = resultado.pessoas;
    });
  }

  excluir(pessoa: any) {
    this.pessoaService
      .excluir(pessoa.id)
      .then(() => {
        if (this.tabela.first === 0) {
          this.pesquisar();
        } else {
          this.tabela.first = 0;
        }
        this.toasty.success("Pesssoa excluída com sucesso!");
      })
      .catch((erro) => this.handler.handle(erro));
  }

  alternarStatus(pessoa: any): void {
    const novoStatus = !pessoa.ativo;

    this.pessoaService
      .mudarStatus(pessoa.id, novoStatus)
      .then(() => {
        const acao = novoStatus ? "ativada" : "desativada";

        pessoa.ativo = novoStatus;
        this.toasty.success(`Pessoa ${acao} com sucesso!`);
      })
      .catch((erro) => this.handler.handle(erro));
  }

  confirmarExclusao(pessoa: any) {
    this.confirmation.confirm({
      message: "Tem certeza que deseja excluir?",
      accept: () => {
        this.excluir(pessoa);
      },
    });
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }
}

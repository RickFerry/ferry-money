import { Component, OnInit, ViewChild } from "@angular/core";
import { Title } from "@angular/platform-browser";

import { ToastyService } from "ng2-toasty";
import {
  ConfirmationService,
  LazyLoadEvent,
} from "primeng/components/common/api";

import { ErrorHandlerService } from "./../../core/error-handler.service";
import { PessoaFiltro, PessoaService } from "./../pessoa.service";

@Component({
  selector: "app-pessoas-pesquisa",
  templateUrl: "./pessoas-pesquisa.component.html",
  styleUrls: ["./pessoas-pesquisa.component.css"],
})
export class PessoasPesquisaComponent implements OnInit {
  totalRegistros = 0;
  filtro = new PessoaFiltro();
  pessoas = [];
  @ViewChild("tabela") grid;

  constructor(
    private pessoaService: PessoaService,
    private errorHandler: ErrorHandlerService,
    private confirmation: ConfirmationService,
    private toasty: ToastyService,
    private title: Title
  ) {}

  ngOnInit() {
    this.title.setTitle("Pesquisa de pessoas");
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.pessoaService
      .pesquisar(this.filtro)
      .then((resultado) => {
        this.totalRegistros = resultado.total;
        this.pessoas = resultado.pessoas;
      })
      .catch((erro) => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(pessoa: any) {
    this.confirmation.confirm({
      message: "Tem certeza que deseja excluir?",
      accept: () => {
        this.excluir(pessoa);
      },
    });
  }

  excluir(pessoa: any) {
    this.pessoaService
      .excluir(pessoa.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
        }

        this.toasty.success("Pesssoa excluída com sucesso!");
      })
      .catch((erro) => this.errorHandler.handle(erro));
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
      .catch((erro) => this.errorHandler.handle(erro));
  }
}

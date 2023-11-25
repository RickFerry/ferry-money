import { Component, ViewChild } from "@angular/core";
import { ErrorHandlerService } from "app/core/error-handler.service";
import { ToastyService } from "ng2-toasty";
import { ConfirmationService } from "primeng/components/common/confirmationservice";
import { LazyLoadEvent } from "primeng/components/common/lazyloadevent";
import { LancamentoFiltro, LancamentoService } from "../lancamento.service";

@Component({
  selector: "app-lancamentos-pesquisa",
  templateUrl: "./lancamentos-pesquisa.component.html",
  styleUrls: ["./lancamentos-pesquisa.component.css"],
})
export class LancamentosPesquisaComponent {
  totalRegistros = 0;
  filtro = new LancamentoFiltro();
  lancamentos = [];
  @ViewChild("tabela") tabela;

  constructor(
    private service: LancamentoService,
    private toasty: ToastyService,
    private confirmation: ConfirmationService,
    private handler: ErrorHandlerService
  ) {}

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.service
      .pesquisar(this.filtro)
      .then((resultado) => {
        this.totalRegistros = resultado.total;
        this.lancamentos = resultado.lancamentos;
      })
      .catch((error) => this.handler.handle(error));
  }

  excluir(lancamento: any) {
    this.service
      .excluir(lancamento.id)
      .then(() => {
        if (this.tabela.first === 0) {
          this.pesquisar();
        } else {
          this.tabela.first = 0;
        }
        this.toasty.success("Lançamento excluído com sucesso!");
      })
      .catch((error) => this.handler.handle(error));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(lancamento: any) {
    this.confirmation.confirm({
      message: "Deseja realmente excluir este lançamento?",
      accept: () => {
        this.excluir(lancamento);
      },
    });
  }
}

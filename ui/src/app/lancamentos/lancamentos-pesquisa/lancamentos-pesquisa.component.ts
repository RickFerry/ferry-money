import { Component, ViewChild } from "@angular/core";
import { LazyLoadEvent } from "primeng/components/common/lazyloadevent";
import { LancamentoFiltro, LancamentoService } from "../lancamento.service";
import { ToastyService } from "ng2-toasty";

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

  constructor(private service: LancamentoService, private toasty: ToastyService) {}

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.service.pesquisar(this.filtro).then((resultado) => {
      this.totalRegistros = resultado.total;
      this.lancamentos = resultado.lancamentos;
    });
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  excluir(lancamento: any) {
    this.service.excluir(lancamento.id).then(() => {
      if (this.tabela.first === 0) {
        this.pesquisar();
      } else {
        this.tabela.first = 0;
      }
      this.toasty.success("Lançamento excluído com sucesso!");
    });
  }
}

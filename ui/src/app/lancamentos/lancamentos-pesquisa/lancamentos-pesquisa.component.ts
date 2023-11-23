import { Component, OnInit } from "@angular/core";
import { LancamentoService } from "../lancamento.service";

@Component({
  selector: "app-lancamentos-pesquisa",
  templateUrl: "./lancamentos-pesquisa.component.html",
  styleUrls: ["./lancamentos-pesquisa.component.css"],
})
export class LancamentosPesquisaComponent implements OnInit {
  descricao: string;
  lancamentos = [];

  constructor(private service: LancamentoService) {}

  ngOnInit(): void {
    this.pesquisar();
  }

  pesquisar() {
    this.service
      .pesquisar({ descricao: this.descricao })
      .then((lanc) => (this.lancamentos = lanc));
  }
}

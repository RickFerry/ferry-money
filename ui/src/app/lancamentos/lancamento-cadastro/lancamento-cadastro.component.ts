import { Component, OnInit } from "@angular/core";
import { CategoriaService } from "app/categorias/categoria.service";
import { ErrorHandlerService } from "app/core/error-handler.service";

@Component({
  selector: "app-lancamento-cadastro",
  templateUrl: "./lancamento-cadastro.component.html",
  styleUrls: ["./lancamento-cadastro.component.css"],
})
export class LancamentoCadastroComponent implements OnInit {
  categorias = [];
  tipos = [
    { label: "Receita", value: "RECEITA" },
    { label: "Despesa", value: "DESPESA" },
  ];
  pessoas = [
    { label: "João da Silva", value: 4 },
    { label: "Sebastião Souza", value: 9 },
    { label: "Maria Abadia", value: 3 },
  ];

  constructor(
    private service: CategoriaService,
    private handle: ErrorHandlerService
  ) {}

  ngOnInit() {
    this.carregarCategorias();
  }

  carregarCategorias() {
    this.service.listarTodas().then((categorias) => {
      this.categorias = categorias.map((c) => ({ label: c.nome, value: c.id }));
    });
  }
}

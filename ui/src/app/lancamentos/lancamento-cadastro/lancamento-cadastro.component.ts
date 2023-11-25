import { Component, OnInit } from "@angular/core";
import { CategoriaService } from "app/categorias/categoria.service";
import { ErrorHandlerService } from "app/core/error-handler.service";
import { PessoaService } from "app/pessoas/pessoa.service";

@Component({
  selector: "app-lancamento-cadastro",
  templateUrl: "./lancamento-cadastro.component.html",
  styleUrls: ["./lancamento-cadastro.component.css"],
})
export class LancamentoCadastroComponent implements OnInit {
  categorias = [];
  pessoas = [];
  tipos = [
    { label: "Receita", value: "RECEITA" },
    { label: "Despesa", value: "DESPESA" },
  ];

  constructor(
    private categoriaService: CategoriaService,
    private pessoaService: PessoaService,
    private handler: ErrorHandlerService
  ) {}

  ngOnInit() {
    this.carregarCategorias();
    this.carregarPessoas();
  }

  carregarCategorias() {
    this.categoriaService
      .listarTodas()
      .then((categorias) => {
        this.categorias = categorias.map((c: { nome: any; id: any }) => ({
          label: c.nome,
          value: c.id,
        }));
      })
      .catch((e) => this.handler.handle(e));
  }

  carregarPessoas() {
    this.pessoaService
      .listarTodas()
      .then((pessoas) => {
        this.pessoas = pessoas.map((p: { nome: any; id: any }) => ({
          label: p.nome,
          value: p.id,
        }));
      })
      .catch((e) => this.handler.handle(e));
  }
}

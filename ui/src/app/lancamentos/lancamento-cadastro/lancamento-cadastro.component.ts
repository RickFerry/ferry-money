import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import { CategoriaService } from "app/categorias/categoria.service";
import { ErrorHandlerService } from "app/core/error-handler.service";
import { Lancamento } from "app/core/model";
import { PessoaService } from "app/pessoas/pessoa.service";
import { ToastyService } from "ng2-toasty";
import { LancamentoService } from "../lancamento.service";

@Component({
  selector: "app-lancamento-cadastro",
  templateUrl: "./lancamento-cadastro.component.html",
  styleUrls: ["./lancamento-cadastro.component.css"],
})
export class LancamentoCadastroComponent implements OnInit {
  categorias = [];
  pessoas = [];
  lancamento = new Lancamento();
  tipos = [
    { label: "Receita", value: "RECEITA" },
    { label: "Despesa", value: "DESPESA" },
  ];

  constructor(
    private categoriaService: CategoriaService,
    private pessoaService: PessoaService,
    private lancamentoService: LancamentoService,
    private toasty: ToastyService,
    private handler: ErrorHandlerService
  ) {}

  ngOnInit() {
    this.carregarCategorias();
    this.carregarPessoas();
  }

  salvar(form: FormControl) {
    this.lancamentoService
      .adicionar(this.lancamento)
      .then(() => {
        this.toasty.success("Lançamento adicionado com sucesso!");

        form.reset();
        this.lancamento = new Lancamento();
      })
      .catch((erro) => this.handler.handle(erro));
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

import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import { CategoriaService } from "app/categorias/categoria.service";
import { ErrorHandlerService } from "app/core/error-handler.service";
import { Lancamento } from "app/core/model";
import { PessoaService } from "app/pessoas/pessoa.service";
import { ToastyService } from "ng2-toasty";
import { LancamentoService } from "../lancamento.service";
import { ActivatedRoute } from "@angular/router";

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
    private handler: ErrorHandlerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const idLancamento = this.route.snapshot.params["id"];
    if (idLancamento) this.carregarLancamento(idLancamento);

    this.carregarCategorias();
    this.carregarPessoas();
  }

  get editando() {
    return Boolean(this.lancamento.id);
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizarLancamento(form);
    } else {
      this.adicionarLancamento(form);
    }
  }

  atualizarLancamento(form: FormControl) {
    this.lancamentoService
      .atualizar(this.lancamento)
      .then((lanc) => {
        this.lancamento = lanc;
        this.toasty.success("Lançamento atualizado com sucesso!");
      })
      .catch((erro) => this.handler.handle(erro));
  }

  adicionarLancamento(form: FormControl) {
    this.lancamentoService
      .adicionar(this.lancamento)
      .then(() => {
        this.toasty.success("Lançamento adicionado com sucesso!");

        form.reset();
        this.lancamento = new Lancamento();
      })
      .catch((erro) => this.handler.handle(erro));
  }

  carregarLancamento(id: number) {
    this.lancamentoService
      .buscarPorCodigo(id)
      .then((lanc) => (this.lancamento = lanc))
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

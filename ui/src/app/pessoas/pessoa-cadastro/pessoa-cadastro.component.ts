import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import { ErrorHandlerService } from "app/core/error-handler.service";
import { Pessoa } from "app/core/model";
import { ToastyService } from "ng2-toasty";
import { PessoaService } from "../pessoa.service";

@Component({
  selector: "app-pessoa-cadastro",
  templateUrl: "./pessoa-cadastro.component.html",
  styleUrls: ["./pessoa-cadastro.component.css"],
})
export class PessoaCadastroComponent implements OnInit {
  pessoa = new Pessoa();

  constructor(
    private pessoaService: PessoaService,
    private toasty: ToastyService,
    private handler: ErrorHandlerService
  ) {}

  ngOnInit() {}

  salvar(form: FormControl) {
    this.pessoaService
      .adicionar(this.pessoa)
      .then(() => {
        this.toasty.success("Pessoa adicionada com sucesso!");

        form.reset();
        this.pessoa = new Pessoa();
      })
      .catch((erro) => this.handler.handle(erro));
  }
}

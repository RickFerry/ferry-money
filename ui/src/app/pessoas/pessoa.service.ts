import { Injectable } from "@angular/core";
import { Headers, Http, URLSearchParams } from "@angular/http";

import "rxjs/add/operator/toPromise";

import { Pessoa } from "./../core/model";

export class PessoaFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable()
export class PessoaService {
  pessoasUrl = "http://localhost:8080/pessoas";

  constructor(private http: Http) {}

  pesquisar(filtro: PessoaFiltro): Promise<any> {
    const params = new URLSearchParams();
    const headers = new Headers();

    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );

    params.set("page", filtro.pagina.toString());
    params.set("size", filtro.itensPorPagina.toString());

    if (filtro.nome) {
      params.set("nome", filtro.nome);
    }

    return this.http
      .get(`${this.pessoasUrl}`, { headers, search: params })
      .toPromise()
      .then((response) => {
        const responseJson = response.json();
        const pessoas = responseJson.content;

        const resultado = {
          pessoas,
          total: responseJson.totalElements,
        };

        return resultado;
      });
  }

  listarTodas(): Promise<any> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );

    return this.http
      .get(this.pessoasUrl, { headers })
      .toPromise()
      .then((response) => response.json().content);
  }

  excluir(id: number): Promise<void> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );

    return this.http
      .delete(`${this.pessoasUrl}/${id}`, { headers })
      .toPromise()
      .then(() => null);
  }

  mudarStatus(id: number, ativo: boolean): Promise<void> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    headers.append("Content-Type", "application/json");

    return this.http
      .put(`${this.pessoasUrl}/${id}/ativo`, ativo, { headers })
      .toPromise()
      .then(() => null);
  }

  adicionar(pessoa: Pessoa): Promise<Pessoa> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    headers.append("Content-Type", "application/json");

    return this.http
      .post(this.pessoasUrl, JSON.stringify(pessoa), { headers })
      .toPromise()
      .then((response) => response.json());
  }

  atualizar(pessoa: Pessoa): Promise<Pessoa> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    headers.append("Content-Type", "application/json");

    return this.http
      .put(`${this.pessoasUrl}/${pessoa.id}`, JSON.stringify(pessoa), {
        headers,
      })
      .toPromise()
      .then((response) => response.json() as Pessoa);
  }

  buscarPorCodigo(id: number): Promise<Pessoa> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );

    return this.http
      .get(`${this.pessoasUrl}/${id}`, { headers })
      .toPromise()
      .then((response) => response.json() as Pessoa);
  }
}

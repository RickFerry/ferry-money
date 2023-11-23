import { Injectable } from "@angular/core";
import { Http, Headers, URLSearchParams } from "@angular/http";

export interface LancamentoFiltro {
  descricao: string;
}

@Injectable()
export class LancamentoService {
  lancamentoUrl = "http://localhost:8080/lancamentos";

  constructor(private http: Http) {}

  pesquisar(filtro: LancamentoFiltro): Promise<any> {
    const params = new URLSearchParams();
    const headers = new Headers();

    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    if (filtro.descricao) {
      params.set("descricao", filtro.descricao);
    }
    return this.http
      .get(`${this.lancamentoUrl}?resumo`, { headers, search: params })
      .toPromise()
      .then((resp) => resp.json().content);
  }
}

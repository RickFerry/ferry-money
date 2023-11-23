import { Injectable } from "@angular/core";
import { Headers, Http, URLSearchParams } from "@angular/http";
import * as moment from "moment";

export class LancamentoFiltro {
  descricao: string;
  dataVencimentoInicio: Date;
  dataVencimentoFim: Date;
  pagina = 0;
  itensPorPagina = 5;
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

    params.set("page", filtro.pagina.toString());
    params.set("size", filtro.itensPorPagina.toString());

    if (filtro.descricao) {
      params.set("descricao", filtro.descricao);
    }
    if (filtro.dataVencimentoInicio) {
      params.set(
        "dataVencimentoDe",
        moment(filtro.dataVencimentoInicio).format("YYYY-MM-DD")
      );
    }
    if (filtro.dataVencimentoFim) {
      params.set(
        "dataVencimentoAte",
        moment(filtro.dataVencimentoFim).format("YYYY-MM-DD")
      );
    }
    return this.http
      .get(`${this.lancamentoUrl}?resumo`, { headers, search: params })
      .toPromise()
      .then((resp) => {
        const respJson = resp.json();
        const lancamentos = respJson.content;

        const resultado = {
          lancamentos,
          total: respJson.totalElements,
        };
        return resultado;
      });
  }
}

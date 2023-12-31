import { Injectable } from "@angular/core";
import { Headers, Http, URLSearchParams } from "@angular/http";
import { Lancamento } from "app/core/model";
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

  excluir(id: number): Promise<void> {
    const headers = new Headers();

    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    return this.http
      .delete(`${this.lancamentoUrl}/${id}`, { headers })
      .toPromise()
      .then(() => null);
  }

  adicionar(lancamento: Lancamento): Promise<Lancamento> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    headers.append("Content-Type", "application/json");

    return this.http
      .post(this.lancamentoUrl, JSON.stringify(lancamento), { headers })
      .toPromise()
      .then((response) => response.json());
  }

  atualizar(lancamento: Lancamento): Promise<Lancamento> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );
    headers.append("Content-Type", "application/json");

    return this.http
      .put(
        `${this.lancamentoUrl}/${lancamento.id}`,
        JSON.stringify(lancamento),
        { headers }
      )
      .toPromise()
      .then((response) => {
        const lancamentoAlterado = response.json() as Lancamento;

        this.converterStringsParaDatas([lancamentoAlterado]);

        return lancamentoAlterado;
      });
  }

  buscarPorCodigo(id: number): Promise<Lancamento> {
    const headers = new Headers();
    headers.append(
      "Authorization",
      "Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg=="
    );

    return this.http
      .get(`${this.lancamentoUrl}/${id}`, { headers })
      .toPromise()
      .then((response) => {
        const lancamento = response.json() as Lancamento;

        this.converterStringsParaDatas([lancamento]);

        return lancamento;
      });
  }

  private converterStringsParaDatas(lancamentos: Lancamento[]) {
    for (const lancamento of lancamentos) {
      lancamento.dataVencimento = moment(
        lancamento.dataVencimento,
        "YYYY-MM-DD"
      ).toDate();

      if (lancamento.dataPagamento) {
        lancamento.dataPagamento = moment(
          lancamento.dataPagamento,
          "YYYY-MM-DD"
        ).toDate();
      }
    }
  }
}

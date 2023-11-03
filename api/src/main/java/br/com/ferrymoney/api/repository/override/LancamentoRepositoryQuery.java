package br.com.ferrymoney.api.repository.override;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {
    List<Lancamento> pesquisar(LancamentoFilter filter);
}

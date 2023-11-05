package br.com.ferrymoney.api.repository.override;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.dto.ResumoLancamentoDto;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {
    Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable page);
    Page<ResumoLancamentoDto> resumir(LancamentoFilter filter, Pageable page);
}

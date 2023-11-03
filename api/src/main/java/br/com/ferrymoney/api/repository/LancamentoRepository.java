package br.com.ferrymoney.api.repository;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.repository.override.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
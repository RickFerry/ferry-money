package br.com.ferrymoney.api.repository.override;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.dto.ResumoLancamentoDto;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Lancamento> pesquisar(LancamentoFilter filter, org.springframework.data.domain.Pageable page) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criaRestricoes(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, page);
        return new PageImpl<>(query.getResultList(), page, total(filter));
    }

    @Override
    public Page<ResumoLancamentoDto> resumir(LancamentoFilter filter, Pageable page) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamentoDto> criteria = builder.createQuery(ResumoLancamentoDto.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(ResumoLancamentoDto.class,
                root.get("id"), root.get("descricao"),
                root.get("dataVencimento"), root.get("dataPagamento"),
                root.get("valor"), root.get("tipo"),
                root.get("categoria").get("nome"),
                root.get("pessoa").get("nome")));

        Predicate[] predicates = criaRestricoes(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<ResumoLancamentoDto> query = entityManager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, page);
        return new PageImpl<>(query.getResultList(), page, total(filter));
    }

    private Predicate[] criaRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (isNotEmpty(filter.getDescricao())) {
            predicates.add(
                    builder.like(builder.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%")
            );
        }
        if (filter.getDataVencimentoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoDe())
            );
        }
        if (filter.getDataVencimentoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoAte())
            );
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable page) {
        int paginaAtual = page.getPageNumber();
        int totalRegistrosPagina = page.getPageSize();
        int primeiraRegistroPagina = paginaAtual * totalRegistrosPagina;

        query.setFirstResult(primeiraRegistroPagina);
        query.setMaxResults(totalRegistrosPagina);
    }

    private Long total(LancamentoFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criaRestricoes(filter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}

package br.com.ferrymoney.api.repository.override;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;

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
    public List<Lancamento> pesquisar(LancamentoFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criaRestricoes(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
        return query.getResultList();
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
}

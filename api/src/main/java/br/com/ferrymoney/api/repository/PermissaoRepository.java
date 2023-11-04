package br.com.ferrymoney.api.repository;

import br.com.ferrymoney.api.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
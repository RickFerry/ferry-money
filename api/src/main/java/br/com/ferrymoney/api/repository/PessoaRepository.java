package br.com.ferrymoney.api.repository;

import br.com.ferrymoney.api.model.Pessoa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByNomeContaining(String nome, Pageable page);
}
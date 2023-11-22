package br.com.ferrymoney.api.repository;

import br.com.ferrymoney.api.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Page<Pessoa> findByNomeContaining(String nome, Pageable page);
}
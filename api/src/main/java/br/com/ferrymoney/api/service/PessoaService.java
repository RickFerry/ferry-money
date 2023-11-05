package br.com.ferrymoney.api.service;

import br.com.ferrymoney.api.model.Pessoa;
import br.com.ferrymoney.api.model.dto.PessoaDto;
import br.com.ferrymoney.api.repository.PessoaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ferrymoney.api.model.Pessoa.toEntity;
import static br.com.ferrymoney.api.model.dto.PessoaDto.toDto;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public Pessoa findByNome(String nome, Pageable page) {
        return pessoaRepository.findByNomeContaining(nome, page);
    }

    @Transactional(readOnly = true)
    public PessoaDto findById(Long id) {
        if (pessoaRepository.findOne(id) != null) {
            return toDto(pessoaRepository.findOne(id));
        } else {
            throw new RuntimeException("Not found!");
        }
    }

    @Transactional
    public PessoaDto create(PessoaDto pessoaDto) {
        return toDto(pessoaRepository.save(toEntity(pessoaDto)));
    }

    @Transactional
    public PessoaDto update(Long id, PessoaDto pessoaDto) {
        Pessoa pessoa = getPessoaById(id);
        copyProperties(pessoaDto, pessoa, "id");
        return toDto(pessoaRepository.save(pessoa));
    }

    public PessoaDto updateAtivo(Long id, Boolean ativo) {
        Pessoa pessoa = getPessoaById(id);
        pessoa.setAtivo(ativo);
        return toDto(pessoaRepository.save(pessoa));
    }

    @Transactional
    public void delete(Long id) {
        if (pessoaRepository.findOne(id) != null) {
            pessoaRepository.delete(pessoaRepository.findOne(id));
        } else {
            throw new RuntimeException("Not found!");
        }
    }

    private Pessoa getPessoaById(Long id) {
        Pessoa pessoa = pessoaRepository.findOne(id);
        if (pessoa == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoa;
    }
}

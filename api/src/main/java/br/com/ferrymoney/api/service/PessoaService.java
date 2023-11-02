package br.com.ferrymoney.api.service;

import br.com.ferrymoney.api.model.Pessoa;
import br.com.ferrymoney.api.model.dto.PessoaDto;
import br.com.ferrymoney.api.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ferrymoney.api.model.Pessoa.toEntity;
import static br.com.ferrymoney.api.model.dto.PessoaDto.toDto;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<PessoaDto> findAll() {
        return toDto(pessoaRepository.findAll());
    }

    @Transactional
    public PessoaDto create(PessoaDto pessoaDto) {
        return toDto(pessoaRepository.save(toEntity(pessoaDto)));
    }
}

package br.com.ferrymoney.api.service;

import br.com.ferrymoney.api.exception.PessoaInexistenteOuInativaException;
import br.com.ferrymoney.api.model.Pessoa;
import br.com.ferrymoney.api.model.dto.LancamentoDto;
import br.com.ferrymoney.api.repository.LancamentoRepository;
import br.com.ferrymoney.api.repository.PessoaRepository;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ferrymoney.api.model.Lancamento.toEntity;
import static br.com.ferrymoney.api.model.dto.LancamentoDto.toDto;

@Service
public class LacamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;

    public LacamentoService(LancamentoRepository lancamentoRepository, PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<LancamentoDto> pesquisar(LancamentoFilter filter) {
        return toDto(lancamentoRepository.pesquisar(filter));
    }

    @Transactional(readOnly = true)
    public LancamentoDto findById(Long id) {
        if (lancamentoRepository.findOne(id) == null) {
            return toDto(lancamentoRepository.findOne(id));
        }
        throw new RuntimeException("Not found!");
    }

    @Transactional
    public LancamentoDto create(LancamentoDto lancamentoDto) {
        Pessoa pessoa = pessoaRepository.findOne(lancamentoDto.getPessoa().getId());
        if (pessoa == null || !pessoa.getAtivo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return toDto(
                lancamentoRepository.save(toEntity(lancamentoDto))
        );
    }
}

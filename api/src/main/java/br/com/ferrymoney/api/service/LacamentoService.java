package br.com.ferrymoney.api.service;

import br.com.ferrymoney.api.exception.PessoaInexistenteOuInativaException;
import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.Pessoa;
import br.com.ferrymoney.api.model.dto.LancamentoDto;
import br.com.ferrymoney.api.model.dto.ResumoLancamentoDto;
import br.com.ferrymoney.api.repository.LancamentoRepository;
import br.com.ferrymoney.api.repository.PessoaRepository;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.ferrymoney.api.model.Lancamento.toEntity;
import static br.com.ferrymoney.api.model.dto.LancamentoDto.toDto;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class LacamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;

    public LacamentoService(LancamentoRepository lancamentoRepository, PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable page) {
        return lancamentoRepository.pesquisar(filter, page);
    }

    @Transactional(readOnly = true)
    public Page<ResumoLancamentoDto> resumir(LancamentoFilter filter, Pageable page) {
        return lancamentoRepository.resumir(filter, page);
    }

    @Transactional(readOnly = true)
    public LancamentoDto findById(Long id) {
        if (lancamentoRepository.findOne(id) == null) {
            throw new RuntimeException("Not found!");
        }
        return toDto(lancamentoRepository.findOne(id));
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

    @Transactional
    public Lancamento update(Long id, Lancamento lancamento) {
        Lancamento lanc = lancamentoRepository.findOne(id);
        if (lanc == null) {
            throw new IllegalArgumentException("Not found!");
        }
        if (!lancamento.getPessoa().equals(lanc.getPessoa())) {
            validarPessoa(lancamento);
        }
        copyProperties(lancamento, lanc, "id");
        return lancamentoRepository.save(lanc);
    }

    private void validarPessoa(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getId() != null) {
            pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
        }
        if (pessoa == null || !pessoa.getAtivo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }
}

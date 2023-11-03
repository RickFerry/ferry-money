package br.com.ferrymoney.api.service;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.dto.LancamentoDto;
import br.com.ferrymoney.api.repository.LancamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ferrymoney.api.model.dto.LancamentoDto.toDto;

@Service
public class LacamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LacamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<LancamentoDto> findAll() {
        return toDto(lancamentoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public LancamentoDto findById(Long id) {
        if (lancamentoRepository.findOne(id) == null) {
            return LancamentoDto.toDto(lancamentoRepository.findOne(id));
        }
        throw new RuntimeException("Not found!");
    }

    @Transactional
    public LancamentoDto create(LancamentoDto lancamentoDto) {
        return LancamentoDto.toDto(
                lancamentoRepository.save(Lancamento.toEntity(lancamentoDto))
        );
    }
}

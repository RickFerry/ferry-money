package br.com.ferrymoney.api.resource;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.dto.LancamentoDto;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;
import br.com.ferrymoney.api.service.LacamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("lancamentos")
public class LancamentoResource {

    private final LacamentoService lacamentoService;

    public LancamentoResource(LacamentoService lacamentoService) {
        this.lacamentoService = lacamentoService;
    }

    @GetMapping
    public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable page) {
        return ResponseEntity.ok(lacamentoService.pesquisar(filter, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lacamentoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> create(@Valid @RequestBody LancamentoDto lancamentoDto,
                                                UriComponentsBuilder uri) {
        LancamentoDto lancamento = lacamentoService.create(lancamentoDto);
        return ResponseEntity.created(
                uri.path("/lancamentos/{id}").buildAndExpand(lancamento.getId()).toUri()).body(lancamento);
    }
}

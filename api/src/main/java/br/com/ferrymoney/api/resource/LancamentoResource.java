package br.com.ferrymoney.api.resource;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.dto.LancamentoDto;
import br.com.ferrymoney.api.model.dto.ResumoLancamentoDto;
import br.com.ferrymoney.api.repository.filter.LancamentoFilter;
import br.com.ferrymoney.api.service.LacamentoService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("lancamentos")
public class LancamentoResource {

    private final LacamentoService lacamentoService;

    public LancamentoResource(LacamentoService lacamentoService) {
        this.lacamentoService = lacamentoService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable page) {
        return ResponseEntity.ok(lacamentoService.pesquisar(filter, page));
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Page<ResumoLancamentoDto>> resumir(LancamentoFilter filter, Pageable page) {
        return ResponseEntity.ok(lacamentoService.resumir(filter, page));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<LancamentoDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lacamentoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<LancamentoDto> create(@Valid @RequestBody LancamentoDto lancamentoDto,
                                                UriComponentsBuilder uri) {
        LancamentoDto lancamento = lacamentoService.create(lancamentoDto);
        return ResponseEntity.created(
                uri.path("/lancamentos/{id}").buildAndExpand(lancamento.getId()).toUri()
        ).body(lancamento);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento) {
        try {
            return ResponseEntity.ok(lacamentoService.update(id, lancamento));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            lacamentoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

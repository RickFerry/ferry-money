package br.com.ferrymoney.api.resource;

import br.com.ferrymoney.api.model.Pessoa;
import br.com.ferrymoney.api.model.dto.PessoaDto;
import br.com.ferrymoney.api.service.PessoaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> findByNome(@RequestParam(required = false, defaultValue = "%") String nome, Pageable page) {
        return ResponseEntity.ok().body(pessoaService.findByNome(nome, page));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') and #oauth2.hasScope('read')")
    public ResponseEntity<PessoaDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pessoaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<PessoaDto> create(@RequestBody @Valid PessoaDto pessoaDto, UriComponentsBuilder uri) {
        PessoaDto dto = pessoaService.create(pessoaDto);
        return ResponseEntity.created(uri.path("/pessoas/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<PessoaDto> update(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
        try {
            return ResponseEntity.ok(pessoaService.update(id, pessoaDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<PessoaDto> updateAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
        return ResponseEntity.ok(pessoaService.updateAtivo(id, ativo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pessoaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

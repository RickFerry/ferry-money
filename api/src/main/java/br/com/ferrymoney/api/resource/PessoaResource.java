package br.com.ferrymoney.api.resource;

import br.com.ferrymoney.api.model.dto.PessoaDto;
import br.com.ferrymoney.api.service.PessoaService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PessoaDto>> findAll() {
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pessoaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PessoaDto> create(@RequestBody @Valid PessoaDto pessoaDto, UriComponentsBuilder uri) {
        PessoaDto dto = pessoaService.create(pessoaDto);
        return ResponseEntity.created(uri.path("/pessoas/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> update(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
        try {
            return ResponseEntity.ok(pessoaService.update(id, pessoaDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativo")
    public ResponseEntity<PessoaDto> updateAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
        return ResponseEntity.ok(pessoaService.updateAtivo(id, ativo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pessoaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

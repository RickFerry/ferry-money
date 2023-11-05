package br.com.ferrymoney.api.resource;

import br.com.ferrymoney.api.model.Categoria;
import br.com.ferrymoney.api.model.dto.CategoriaDto;
import br.com.ferrymoney.api.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER') and #oauth2.hasScope('read')")
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') and #oauth2.hasScope('read')")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<CategoriaDto> create(@Valid @RequestBody CategoriaDto dto,
                                               UriComponentsBuilder uriComponentsBuilder) {
        try {
            CategoriaDto resp = categoriaService.create(dto);
            return ResponseEntity
                    .created(uriComponentsBuilder.path("/{codigo}").buildAndExpand(resp.getId())
                            .toUri()).body(resp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

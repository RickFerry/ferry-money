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

    @PostMapping
    public ResponseEntity<PessoaDto> create(@RequestBody @Valid PessoaDto pessoaDto, UriComponentsBuilder uri) {
        PessoaDto dto = pessoaService.create(pessoaDto);
        return ResponseEntity.created(uri.path("/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }
}

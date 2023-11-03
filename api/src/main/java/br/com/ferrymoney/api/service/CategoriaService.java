package br.com.ferrymoney.api.service;

import br.com.ferrymoney.api.model.Categoria;
import br.com.ferrymoney.api.model.dto.CategoriaDto;
import br.com.ferrymoney.api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ferrymoney.api.model.Categoria.toEntity;
import static br.com.ferrymoney.api.model.dto.CategoriaDto.toDto;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public CategoriaDto create(CategoriaDto dto) {
        return toDto(
                categoriaRepository.save(toEntity(dto))
        );
    }

    @Transactional(readOnly = true)
    public CategoriaDto findById(Long codigo) {
        if (categoriaRepository.findOne(codigo) != null)
            return CategoriaDto.toDto(categoriaRepository.findOne(codigo));
        throw new RuntimeException("Code: " + codigo + " not found!");
    }
}

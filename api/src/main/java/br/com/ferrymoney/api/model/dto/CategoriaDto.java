package br.com.ferrymoney.api.model.dto;

import br.com.ferrymoney.api.model.Categoria;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoriaDto {
    Long id;

    @NotNull
    @Size(min = 3, max = 50)
    String nome;

    public static CategoriaDto toDto(Categoria categoria) {
        return CategoriaDto.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
}

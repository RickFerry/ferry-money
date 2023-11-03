package br.com.ferrymoney.api.model;

import br.com.ferrymoney.api.model.dto.CategoriaDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
@Entity @Table(name = "categoria")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(unique = true)
    String nome;

    public static Categoria toEntity(CategoriaDto dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }
}

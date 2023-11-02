package br.com.ferrymoney.api.model;

import br.com.ferrymoney.api.model.dto.PessoaDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
@Entity @Table(name = "pessoa")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String nome;

    @NotNull
    Boolean ativo;

    @Embedded
    Endereco endereco;

    public static Pessoa toEntity(PessoaDto pessoaDto) {
        return Pessoa.builder()
                .id(pessoaDto.getId())
                .nome(pessoaDto.getNome())
                .ativo(pessoaDto.getAtivo())
                .endereco(pessoaDto.getEndereco())
                .build();
    }
}

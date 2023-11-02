package br.com.ferrymoney.api.model.dto;

import br.com.ferrymoney.api.model.Endereco;
import br.com.ferrymoney.api.model.Pessoa;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PessoaDto {
    Long id;

    @NotNull
    String nome;

    @NotNull
    Boolean ativo;

    Endereco endereco;

    public static PessoaDto toDto(Pessoa dto) {
        return PessoaDto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .ativo(dto.getAtivo())
                .endereco(dto.getEndereco())
                .build();
    }

    public static List<PessoaDto> toDto(List<Pessoa> list) {
        List<PessoaDto> listDto = new ArrayList<>();
        list.forEach(p -> listDto.add(toDto(p)));
        return listDto;
    }
}

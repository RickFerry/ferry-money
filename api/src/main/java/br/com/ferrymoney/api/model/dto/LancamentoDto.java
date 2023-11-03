package br.com.ferrymoney.api.model.dto;

import br.com.ferrymoney.api.model.Lancamento;
import br.com.ferrymoney.api.model.enums.TipoLancamento;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LancamentoDto {
    Long id;

    @NotNull
    String descricao;

    @NotNull
    LocalDate dataVencimento;

    LocalDate dataPagamento;

    @NotNull
    BigDecimal valor;

    String observacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    TipoLancamento tipo;

    @NotNull
    CategoriaDto categoria;

    @NotNull
    PessoaDto pessoa;

    public static LancamentoDto toDto(Lancamento dto) {
        return LancamentoDto.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .dataVencimento(dto.getDataVencimento())
                .dataPagamento(dto.getDataPagamento())
                .valor(dto.getValor())
                .observacao(dto.getObservacao())
                .tipo(dto.getTipo())
                .categoria(CategoriaDto.toDto(dto.getCategoria()))
                .pessoa(PessoaDto.toDto(dto.getPessoa()))
                .build();
    }

    public static List<LancamentoDto> toDto(List<Lancamento> list) {
        List<LancamentoDto> listDto = new ArrayList<>();
        list.forEach(p -> listDto.add(toDto(p)));
        return listDto;
    }
}

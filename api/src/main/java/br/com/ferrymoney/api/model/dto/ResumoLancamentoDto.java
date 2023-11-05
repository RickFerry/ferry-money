package br.com.ferrymoney.api.model.dto;

import br.com.ferrymoney.api.model.enums.TipoLancamento;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResumoLancamentoDto {

    Long id;
    String descricao;
    LocalDate dataVencimento;
    LocalDate dataPagamento;
    BigDecimal valor;
    TipoLancamento tipo;
    String categoria;
    String pessoa;
}

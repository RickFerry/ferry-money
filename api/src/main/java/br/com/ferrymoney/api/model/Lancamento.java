package br.com.ferrymoney.api.model;

import br.com.ferrymoney.api.model.dto.LancamentoDto;
import br.com.ferrymoney.api.model.enums.TipoLancamento;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "lancamento")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    Categoria categoria;

    @ManyToOne
    Pessoa pessoa;

    public static Lancamento toEntity(LancamentoDto lancamentoDto) {
        return Lancamento.builder()
                .id(lancamentoDto.getId())
                .descricao(lancamentoDto.getDescricao())
                .dataVencimento(lancamentoDto.getDataVencimento())
                .dataPagamento(lancamentoDto.getDataPagamento())
                .valor(lancamentoDto.getValor())
                .observacao(lancamentoDto.getObservacao())
                .tipo(lancamentoDto.getTipo())
                .categoria(Categoria.toEntity(lancamentoDto.getCategoria()))
                .pessoa(Pessoa.toEntity(lancamentoDto.getPessoa()))
                .build();
    }
}

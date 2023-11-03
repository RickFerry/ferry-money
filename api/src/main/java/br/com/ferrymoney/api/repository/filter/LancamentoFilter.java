package br.com.ferrymoney.api.repository.filter;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LancamentoFilter {
    String descricao;

    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    LocalDate dataVencimentoDe;

    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    LocalDate dataVencimentoAte;
}

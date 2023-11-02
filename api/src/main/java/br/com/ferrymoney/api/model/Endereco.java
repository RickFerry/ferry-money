package br.com.ferrymoney.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter @Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Endereco {
    String logradouro;
    String numero;
    String complemento;
    String bairro;
    String cep;
    String cidade;
    String estado;
}

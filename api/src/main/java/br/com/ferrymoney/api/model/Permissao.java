package br.com.ferrymoney.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
@Entity @Table(name = "permissao")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String descricao;
}

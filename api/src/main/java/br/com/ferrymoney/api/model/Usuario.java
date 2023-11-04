package br.com.ferrymoney.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "usuario")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Usuario {

    @Id @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter @NotNull
    String nome;

    @Setter @NotNull
    @Column(unique = true)
    String email;

    @Setter @NotNull
    String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    List<Permissao> permissoes = new ArrayList<>();
}

package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @JoinColumn(name = "razao_Social")
    private String razaoSocial;

    private String missao;

    private Long cnpj;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String email;

    private Long telefone;

    @OneToMany
    private List<Produto> produtos;

}

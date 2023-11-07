package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo;

    private String descrisao;

    @JoinColumn(name = "molde_Nome")
    private String moldeNome;

    @JoinColumn(name = "molde_Codigo")
    private String moldeCodigo;

    private Double valor;

    private String imagem;


}

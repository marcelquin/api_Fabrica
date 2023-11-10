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
public class MoldeProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo;

    @OneToMany
    private List<Especificacao> especificacoes;

    private Double valorTotalMateriais;



    public Double calValorTotal()
    {
        Double valor = especificacoes.stream().mapToDouble(item -> item.getMaterial().calValorPorUnitario()).sum();
        return this.valorTotalMateriais = valor;
    }

}

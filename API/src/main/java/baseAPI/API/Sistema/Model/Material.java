package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.UniddeMedida;
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
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo;

    private String descrisao;

    @ManyToOne
    @JoinColumn(name = "material_fornecedor_Id")
    private Fornecedor fornecedor;

    @JoinColumn(name = "valor_Nota")
    private Double valorNota;

    private  Double quantidade;

    @OneToMany
    private List<Especificacao> especificacoes;

    @Enumerated
    private UniddeMedida uniddeMedida;

    @JoinColumn(name = "valor_Por_Unidade")
    private Double valorPorUnitario;

    public double calValorPorUnitario()
    {
        Double valor;
        valor = this.valorNota / this.quantidade;
        this.setValorPorUnitario(valor);
        return this.valorPorUnitario;
    }
}

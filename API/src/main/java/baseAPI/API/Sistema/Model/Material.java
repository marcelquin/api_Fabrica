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
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo; //busca personalizada

    private String descrisao;

    @JoinColumn(name = "fornecedor_Cnpj")
    private String fornecedorNome;

    @JoinColumn(name = "fornecedor_Cnpj")
    private String fornecedorCnpj;

    @JoinColumn(name = "valor_Nota")
    private Double valorNota;

    private  Double quantidade;

    @JoinColumn(name = "valor_Por_Unidade")
    private Double valorPorUnitario; //setado automaticamente

}

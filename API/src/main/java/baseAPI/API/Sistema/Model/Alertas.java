package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.TipoAviso;
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
public class Alertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "produto_Nome")
    private String produtoNome;

    @JoinColumn(name = "produto_Codigo")
    private String produtoCodigo;

    @JoinColumn(name = "fornecedor_Razao_Social")
    private String fornecedorRazaoSocial;

    @JoinColumn(name = "fornecedor_Cnpj")
    private String fornecedorCnpj;

    @JoinColumn(name = "material_Nome")
    private String materialNome;

    @JoinColumn(name = "material_Codigo")
    private String materialCodigo;

    @Enumerated
    private TipoAviso tipoAviso;

    private String mensagem;
}

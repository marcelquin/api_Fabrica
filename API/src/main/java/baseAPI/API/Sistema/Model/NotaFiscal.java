package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.TipoNotaFiscal;
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
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private TipoNotaFiscal tipoNotaFiscal;

    @JoinColumn(name = "nota_Codigo")
    private String notaCodigo;

    private String empresa;

    private Long cnpj;

    private String endereco;

    private Long telefone;

    private Double valor;

    @JoinColumn(name = "produto_Nome")
    private String produtoNome;

   private Double quantidade;


}

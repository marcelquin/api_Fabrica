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
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @ManyToOne
    @JoinColumn(name = "notificacao_material_Id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "notificacao_produto_Id")
    private Produto produto;

    @Enumerated
    private TipoAviso tipoAviso;

    private String mensagem;
}

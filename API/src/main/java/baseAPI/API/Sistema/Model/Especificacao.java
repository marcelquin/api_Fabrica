package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.UniddeMedida;
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
public class Especificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "material_Nome")
    private String materialNome;

    @JoinColumn(name = "material_Codigo")
    private String materialCodigo;

    private Double quantidade;

    @Enumerated
    private UniddeMedida uniddeMedida;


}

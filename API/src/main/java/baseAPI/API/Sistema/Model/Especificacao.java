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

    private String codigo;

    @ManyToOne()
    @JoinColumn(name = "Material_Especificacao_Id")
    private Material material;

    private Double quantidade;

    @Enumerated
    private UniddeMedida uniddeMedida;




}

package baseAPI.API.Sistema.DTO;

import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class MaterialDTO {
    private String nome;

    private String descrisao;

    private Long fornecedorCnpj;

    private Double valorNota;

    private  Double quantidade;

}

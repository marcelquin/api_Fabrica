package baseAPI.API.Sistema.DTO;

import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class EmpresaDTO {

    private String nome;

    private String razaoSocial;

    private String missao;

    private Long cnpj;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String email;

    private Long telefone;
}

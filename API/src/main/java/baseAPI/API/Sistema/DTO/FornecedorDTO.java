package baseAPI.API.Sistema.DTO;

import lombok.Data;

@Data
public class FornecedorDTO {

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

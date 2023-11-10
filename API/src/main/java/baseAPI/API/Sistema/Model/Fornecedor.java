package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.AreaAtuacao;
import baseAPI.API.Sistema.Enum.TempoContrato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @JoinColumn(name = "razao_Social")
    private String razaoSocial;

    private String missao;

    private Long cnpj;

    @Enumerated
    private AreaAtuacao areaAtuacao;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String email;

    private Long telefone;

    private String logoMarca;

    @OneToMany
    @JoinColumn(name = "notas_Fiscais")
    private  List<NotaFiscal> notasFiscais;

    @JoinColumn(name = "inicio_Contrato")
    private LocalDate inicioContrato;

    @Enumerated
    private TempoContrato tempoContrato;
}

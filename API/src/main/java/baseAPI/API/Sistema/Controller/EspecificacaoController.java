package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.DTO.EspecificacaoDTO;
import baseAPI.API.Sistema.Enum.UniddeMedida;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Especificacao;
import baseAPI.API.Sistema.Service.EmpresaService;
import baseAPI.API.Sistema.Service.EspecificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/especificacao")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/especificacao", description = "manipula dados relacionados a entidade" )
public class EspecificacaoController {

    @Autowired
    EspecificacaoService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Especificacao>> listarespecificacoes(){return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarEspecificacaoPorId")
    public ResponseEntity<Especificacao> buscarEspecificacaoPorId(@RequestParam Long id){return service.buscarPorId(id);}

    @Operation(summary = "Busca Registros da Tabela por Cnpj", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarEspecificacaoPorCodigo")
    public ResponseEntity<Especificacao> buscarEspecificacaoPorCodigo(@RequestParam String codigo)
    {return service.buscarPorCodigo(codigo);}

    @Operation(summary = "Salva Registros na Tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping()
    public ResponseEntity<EspecificacaoDTO> NovaEspecificacao(EspecificacaoDTO especificacaoDTO, @RequestParam UniddeMedida uniddeMedida)
    {return service.salvar(especificacaoDTO, uniddeMedida);}

    @Operation(summary = "Edita Registros na Tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping()
    public ResponseEntity<EspecificacaoDTO> editarespecificacao(@RequestParam String codigo, EspecificacaoDTO especificacaoDTO, @RequestParam UniddeMedida uniddeMedida)
    { return  service.editar(codigo, especificacaoDTO, uniddeMedida);}

    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping()
    public ResponseEntity<EspecificacaoDTO> deletarEspecificacao(@RequestParam Long id)
    {return service.deletar(id);}
}

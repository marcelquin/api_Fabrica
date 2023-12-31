package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.MoldeDTO;
import baseAPI.API.Sistema.Model.MoldeProduto;
import baseAPI.API.Sistema.Service.MoldeProdutoService;
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

import java.util.List;

@RestController
@RequestMapping("/api/molde_Produto")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/molde_Produto", description = "manipula dados relacionados a entidade")
public class MoldeProdutoController {

    @Autowired
    MoldeProdutoService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<MoldeProduto>> listarMoldes()
    {return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarMoldePorId")
    public ResponseEntity<MoldeProduto> buscarMoldePorId(@RequestParam Long id)
    {return service.buscarPorId(id);}

    @Operation(summary = "Busca Registros da Tabela por Cnpj", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarMoldePorCodigo")
    public ResponseEntity<MoldeProduto> buscarMoldePorCodigo(@RequestParam String codigo)
    {return service.buscarPorCodigo(codigo);}

    @Operation(summary = "Salva Registros na Tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping()
    public ResponseEntity<MoldeDTO> novoMolde(MoldeDTO moldeDTO, @RequestParam String[] codigosEspecificacoes)
    {return service.salvar(moldeDTO, codigosEspecificacoes);}

    @Operation(summary = "Edita Registros na Tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping()
    public ResponseEntity<MoldeDTO> editarMolde(@RequestParam String Codigo_Molde, @RequestParam String nome, String[] codigosEspecificacoes)
    {return service.editar(Codigo_Molde, nome, codigosEspecificacoes);}

    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping()
    public ResponseEntity<MoldeDTO> deletarMolde(@RequestParam Long id)
    {return service.deletar(id);}


    @Operation(summary = "Deleta Registros na Tabela por codigo", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping("/deletarMoldePorCodigo")
    public ResponseEntity<MoldeDTO> deletarMoldePorCodigo(@RequestParam String codigo)
    {return service.deletarPorCodigo(codigo);}
}

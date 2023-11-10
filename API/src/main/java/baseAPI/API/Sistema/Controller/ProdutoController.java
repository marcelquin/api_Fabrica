package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.ProdutoDTO;
import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Service.ProdutoService;
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
@RequestMapping("/api/Produto")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/Produto", description = "manipula dados relacionados a entidade")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Produto>> listar()
    {return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarProdutoPorId")
    public ResponseEntity<Produto> buscarProdutoPorId(@RequestParam Long id)
    {return service.buscarPorId(id);}

    @Operation(summary = "Busca Registros da Tabela por Codigo", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarProdutoPorCodigo")
    public ResponseEntity<Produto> buscarProdutoPorCodigo(@RequestParam String codigo)
    {return service.buscarPorCodigo(codigo);}

    @Operation(summary = "Salva Registros na Tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoDTO> Salvar(ProdutoDTO produtoDTO, @RequestParam String codigoMolde, @RequestPart MultipartFile file)
    {return service.Salvar(produtoDTO, codigoMolde, file);}

    @Operation(summary = "Fabrica Produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping("/fabricarProduto")
    public ResponseEntity<ProdutoDTO> fabricarProduto(String codigoProduto, Double quantidade)
    {return service.fabricarProduto(codigoProduto, quantidade);}

    @Operation(summary = "Edita Registros na Tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoDTO> editarProduto(@RequestParam String codigoProduto,ProdutoDTO produtoDTO, String codigoMolde, @RequestPart MultipartFile file)
    {return service.editar(codigoProduto, produtoDTO, codigoMolde, file);}

    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping()
    public ResponseEntity<ProdutoDTO> deletar(@RequestParam Long id)
    {return service.deletar(id);}
}

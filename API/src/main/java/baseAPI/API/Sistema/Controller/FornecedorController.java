package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.Enum.AreaAtuacao;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Service.FornecedorService;
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
@RequestMapping("/api/fornecedor")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/fornecedor", description = "manipula dados relacionados a entidade")
public class FornecedorController {

    @Autowired
    FornecedorService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Fornecedor>> listarFornecedores(){ return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarFornecedorPorId")
    public ResponseEntity<Fornecedor> buscarFornecedorPorId(@RequestParam Long id)
    {return service.buscarPorId(id);}

    @Operation(summary = "Busca Registros da Tabela por Cnpj", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarFornecedorPorcnpj")
    public ResponseEntity<Fornecedor> buscarFornecedorPorcnpj(@RequestParam Long cnpj)
    {return service.buscarPorcnpj(cnpj);}

    @Operation(summary = "Salva Registros na Tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FornecedorDTO> salvarFornecedor(@RequestParam AreaAtuacao areaAtuacao, FornecedorDTO fornecedorDTO, @RequestPart MultipartFile Logo_Marca)
    {return service.salvar(areaAtuacao, fornecedorDTO, Logo_Marca);}

    @Operation(summary = "Edita Registros na Tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping()
    public ResponseEntity<FornecedorDTO> editarFornececod(@RequestParam Long cnpj, FornecedorDTO fornecedorDTO)
    {return service.editar(cnpj, fornecedorDTO);}

    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping()
    public ResponseEntity<FornecedorDTO> deletarFornecedor(@RequestParam Long id)
    {return service.deletar(id);}
}

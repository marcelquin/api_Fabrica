package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.MaterialDTO;
import baseAPI.API.Sistema.Enum.UniddeMedida;
import baseAPI.API.Sistema.Model.Material;
import baseAPI.API.Sistema.Service.MaterialService;
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
@RequestMapping("/api/material")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/material", description = "manipula dados relacionados a entidade")
public class MaterialController {

    @Autowired
    MaterialService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Material>> listarMateriais()
    {return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarMaterialPorId")
    public ResponseEntity<Material> buscarMaterialPorId(@RequestParam Long id)
    {return service.buscarPorId(id);}

    @Operation(summary = "Busca Registros da Tabela por Cnpj", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarMaterialPorNome")
    public ResponseEntity<Material> buscarMaterialPorNome(@RequestParam String nome)
    {return service.buscarPorNome(nome);}

    @Operation(summary = "Busca Registros da Tabela por Cnpj", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarMaterialPorCodigo")
    public ResponseEntity<Material> buscarMaterialPorCodigo(@RequestParam String codigo)
    {return service.buscarPorCodigo(codigo);}

    @Operation(summary = "Salva Registros na Tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping()
    public ResponseEntity<MaterialDTO> salvar(MaterialDTO materialDTO,@RequestParam UniddeMedida uniddeMedida)
    {return service.salvar(materialDTO, uniddeMedida);}

    @Operation(summary = "Edita Registros na Tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping()
    public ResponseEntity<MaterialDTO> editarMaterial(@RequestParam String codigo, MaterialDTO materialDTO,
                                                      @RequestParam UniddeMedida uniddeMedida)
    {return service.editar(codigo, materialDTO, uniddeMedida);}

    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping("/materialCodigo")
    public ResponseEntity<MaterialDTO> deletarMateiralPorCodigo(@RequestParam String codigo)
    {return service.deletarPorCodigo(codigo);}

    @Operation(summary = "Deleta Registros na Tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping("/materialId")
    public ResponseEntity<MaterialDTO> deletarMaterial(@RequestParam Long id)
    { return  service.deletar(id);}
}

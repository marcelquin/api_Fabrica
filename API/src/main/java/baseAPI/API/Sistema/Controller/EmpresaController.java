package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Service.EmpresaService;
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
@RequestMapping("/api/empresa")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/empresa", description = "manipula dados relacionados a entidade" )
public class EmpresaController {

    @Autowired
    EmpresaService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Empresa>> listarEmpresas(){return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarEmpresaPorId")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@RequestParam Long id){return service.buscarPorId(id);}

    @Operation(summary = "Busca Registros da Tabela por Cnpj", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarEmpresaPorcnpj")
    public ResponseEntity<Empresa> buscarEmpresaPorcnpj(@RequestParam Long cnpj)
    {return service.buscarPorcnpj(cnpj);}

    @Operation(summary = "Salva Registros na Tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmpresaDTO> salvarEmpresa(EmpresaDTO empresaDTO, @RequestPart MultipartFile Logo_Marca)
    { return service.salvar(empresaDTO, Logo_Marca); }

    @Operation(summary = "Edita Registros na Tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PutMapping()
    public ResponseEntity<EmpresaDTO> editarEmpresa(@RequestParam Long Cnpj, EmpresaDTO empresaDTO)
    { return service.editar(Cnpj,empresaDTO); }

    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping()
    public ResponseEntity<EmpresaDTO> deletarEmpresa(@RequestParam Long id){return service.deletar(id);}
}

package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Notificacao;
import baseAPI.API.Sistema.Service.EmpresaService;
import baseAPI.API.Sistema.Service.NotificacaoService;
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
@RequestMapping("/api/notificacao")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/notificacao", description = "manipula dados relacionados a entidade" )
public class NotificacaoController {

    @Autowired
    NotificacaoService service;

    @Operation(summary = "Lista Registros da Tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Notificacao>> listar(){return service.listar();}

    @Operation(summary = "Busca Registros da Tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @GetMapping("/buscarNotificacaoPorId")
    public ResponseEntity<Notificacao> buscarNotificacaoPorId(@RequestParam Long id)
    { return service.buscarPorId(id);}

    @Operation(summary = "Verifica estoque de produtos e materiais", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @PostMapping()
    public void VerificaEstoques()
    { service.VerificaEstoques();}


    @Operation(summary = "Deleta Registros na Tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo errado"),
    })
    @DeleteMapping()
    public ResponseEntity<Notificacao> deletarNotificacao(@RequestParam Long id)
    { return service.deletar(id);}



}

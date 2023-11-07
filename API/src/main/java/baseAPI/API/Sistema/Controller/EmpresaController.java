package baseAPI.API.Sistema.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empresa")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "api/empresa", description = "manipula dados relacionados a entidade")
public class EmpresaController {
}
package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Empresa findBynome(String nome);

    Empresa findBycnpj(Long cnpj);
}

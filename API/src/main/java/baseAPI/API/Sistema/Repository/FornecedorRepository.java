package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Fornecedor findBynome(String nome);

    Fornecedor findBycnpj(Long cnpj);
}

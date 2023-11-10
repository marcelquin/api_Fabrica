package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Model.Verificador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificadorRepository  extends JpaRepository<Verificador, Long> {
    Verificador findBycodigo(String codigo);
}

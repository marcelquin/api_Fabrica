package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Especificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecificacaoRepository extends JpaRepository<Especificacao, Long> {
    Especificacao findBycodigo(String codigo);
}

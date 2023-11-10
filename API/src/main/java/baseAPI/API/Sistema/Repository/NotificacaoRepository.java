package baseAPI.API.Sistema.Repository;


import baseAPI.API.Sistema.Model.Material;
import baseAPI.API.Sistema.Model.Notificacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    Notificacao findBycodigo(String codigo);
}

package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.MoldeProduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoldeProdutoRepository extends JpaRepository<MoldeProduto, Long> {

     MoldeProduto findBynome(String Nome);

     MoldeProduto findBycodigo(String codigo);
}

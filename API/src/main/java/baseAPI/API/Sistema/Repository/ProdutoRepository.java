package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

     Produto findBynome(String Nome);

    Produto findBycodigo(String codigo);
}

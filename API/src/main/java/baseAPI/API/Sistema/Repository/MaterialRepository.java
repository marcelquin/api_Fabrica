package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

     Material findBynome(String Nome);

     Material findBycodigo(String codigo);
}

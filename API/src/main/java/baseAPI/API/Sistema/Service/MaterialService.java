package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.DTO.MaterialDTO;
import baseAPI.API.Sistema.Enum.UniddeMedida;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Model.Material;
import baseAPI.API.Sistema.Repository.FornecedorRepository;
import baseAPI.API.Sistema.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository repository;
    @Autowired
    FornecedorRepository Frepository;

    public ResponseEntity<List<Material>> listar()
    {
        try{
            return new ResponseEntity<>(repository.findAll(), OK);
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado na busca");
            e.getStackTrace();
        }
        return null;
    }


    public ResponseEntity<Material> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<Material> entidade = repository.findById(id);
                return new ResponseEntity<Material>(entidade.get(), ACCEPTED);
            }else {
                ResponseEntity responseEntity = new ResponseEntity<Empresa>(BAD_GATEWAY);
                return responseEntity;
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado na busca");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<Material> buscarPorNome(String nome)
    {
        try{
            if(nome != null)
            {
                Optional<Material> entidadeteste = Optional.ofNullable(repository.findBynome(nome));
                return new ResponseEntity<Material>(entidadeteste.get(), ACCEPTED);
            }else {
                ResponseEntity responseEntity = new ResponseEntity<Empresa>(BAD_GATEWAY);
                return responseEntity;
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado na busca");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<Material> buscarPorCodigo(String codigo)
    {
        try{
            if(codigo != null)
            {
                Optional<Material> entidadeteste = Optional.ofNullable(repository.findBycodigo(codigo));
                return new ResponseEntity<Material>(entidadeteste.get(), ACCEPTED);
            }else {
                ResponseEntity responseEntity = new ResponseEntity<Empresa>(BAD_GATEWAY);
                return responseEntity;
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado na busca");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<MaterialDTO> salvar(MaterialDTO materialDTO, UniddeMedida uniddeMedida)
    {
        try{
            if(materialDTO != null)
            {
                Material material = new Material();
                material.setNome(materialDTO.getNome());
                material.setDescrisao(materialDTO.getDescrisao());
                int cod = (int) (1001 + Math.random() * 8999);
                String codigo = "Mtr_"+cod;
                material.setCodigo(codigo);
                material.setValorNota(materialDTO.getValorNota());
                material.setQuantidade(materialDTO.getQuantidade());
                if(materialDTO.getFornecedorCnpj() != null)
                {
                    Fornecedor fornecedor = Frepository.findBycnpj(materialDTO.getFornecedorCnpj());
                    if(fornecedor != null)
                    {
                        material.setFornecedorNome(fornecedor.getNome());
                        material.setFornecedorCnpj(fornecedor.getCnpj());
                    }
                    if(uniddeMedida != null)
                    {
                        material.setUniddeMedida(uniddeMedida);
                    }
                }
                material.setValorPorUnitario(material.calValorPorUnitario());
                repository.save(material);
                return new ResponseEntity<>(CREATED);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<MaterialDTO> editar(String codigo, MaterialDTO materialDTO, UniddeMedida uniddeMedida)
    {
        try{
            if(codigo != null)
            {
                Material material = repository.findBycodigo(codigo);
                Fornecedor fornecedor = new Fornecedor();
                if(material!= null)
                {
                    if(materialDTO.getNome() != null)
                    {
                       material.setNome(materialDTO.getNome());
                    }
                    if(materialDTO.getDescrisao() != null)
                    {
                        material.setDescrisao(materialDTO.getDescrisao());
                    }
                    if(materialDTO.getValorNota() != null)
                    {
                        material.setValorNota(materialDTO.getValorNota());
                    }
                    if(materialDTO.getFornecedorCnpj() != null)
                    {
                        fornecedor = Frepository.findBycnpj(materialDTO.getFornecedorCnpj());
                        if (fornecedor != null)
                        {
                            material.setFornecedorNome(fornecedor.getNome());
                            material.setFornecedorCnpj(fornecedor.getCnpj());
                        }
                    }
                    if(materialDTO.getQuantidade() != null)
                    {
                        material.setQuantidade(materialDTO.getQuantidade());
                    }
                    if(uniddeMedida != null)
                    {
                        material.setUniddeMedida(uniddeMedida);
                    }
                    repository.save(material);
                    List<Material> materials = new ArrayList<>();
                    materials.add(material);
                    fornecedor.setMateriais(materials);
                    Frepository.save(fornecedor);
                }
                return new ResponseEntity<>(OK);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
        return null;
    }


    public ResponseEntity<MaterialDTO> deletar(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                repository.deleteById(id);
                return new ResponseEntity<>(OK);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e){
            e.getMessage();
            System.out.println("ops algo deu errado ao deletar");
        }
        return null;
    }

    public ResponseEntity<MaterialDTO> deletarPorCodigo(String codigo)
    {
        try{
            if(codigo != null)
            {
                Material material = repository.findBycodigo(codigo);
                repository.deleteById(material.getId());
                return new ResponseEntity<>(OK);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e){
            e.getMessage();
            System.out.println("ops algo deu errado ao deletar");
        }
        return null;
    }
}

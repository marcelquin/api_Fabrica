package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.DTO.EspecificacaoDTO;
import baseAPI.API.Sistema.Enum.UniddeMedida;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Especificacao;
import baseAPI.API.Sistema.Model.Material;
import baseAPI.API.Sistema.Repository.EspecificacaoRepository;
import baseAPI.API.Sistema.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@Service
public class EspecificacaoService {

    @Autowired
    EspecificacaoRepository repository;

    @Autowired
    MaterialRepository Mrepository;


    public ResponseEntity<List<Especificacao>> listar()
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

    public ResponseEntity<Especificacao> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<Especificacao> entidade = repository.findById(id);
                return new ResponseEntity<Especificacao>(entidade.get(), ACCEPTED);
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


    public ResponseEntity<Especificacao> buscarPorCodigo(String codigo)
    {
        try{
            if(codigo != null)
            {
                Optional<Especificacao> entidade = Optional.ofNullable(repository.findBycodigo(codigo));
                return new ResponseEntity<Especificacao>(entidade.get(), ACCEPTED);
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

    public ResponseEntity<EspecificacaoDTO> salvar(EspecificacaoDTO especificacaoDTO, UniddeMedida uniddeMedida)
    {
        try{
            if(especificacaoDTO != null)
            {
                Especificacao especificacao = new Especificacao();
                Material material = new Material();
                List<Especificacao> especificacaos = new ArrayList<>();
                int cod = (int) (1001 + Math.random() * 8999);
                String codigo = "EsMt_"+cod;
                especificacao.setCodigo(codigo);
                if(especificacaoDTO.getMaterialCodigo() != null)
                {
                    material = Mrepository.findBycodigo(especificacaoDTO.getMaterialCodigo());
                    if(material != null)
                    {
                        especificacao.setMaterial(material);
                    }
                }
                especificacao.setQuantidade(especificacaoDTO.getQuantidade());
                if(uniddeMedida != null)
                {
                    especificacao.setUniddeMedida(uniddeMedida);
                }
                repository.save(especificacao);
                especificacaos.add(especificacao);
                material.setEspecificacoes(especificacaos);
                Mrepository.save(material);
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

    public ResponseEntity<EspecificacaoDTO> editar(String codigo, EspecificacaoDTO especificacaoDTO, UniddeMedida uniddeMedida)
    {
        try{
            if(codigo != null)
            {
                Especificacao especificacao = repository.findBycodigo(codigo);
                Material material = new Material();
                List<Especificacao> especificacaos = new ArrayList<>();
                if(especificacao != null)
                {
                    if(especificacaoDTO.getMaterialCodigo() != null)
                    {
                        material = Mrepository.findBycodigo(especificacaoDTO.getMaterialCodigo());
                        if(material != null)
                        {
                            especificacao.setMaterial(material);
                        }
                    }
                    especificacao.setQuantidade(especificacaoDTO.getQuantidade());
                    if(uniddeMedida != null)
                    {
                        if(uniddeMedida == especificacao.getMaterial().getUniddeMedida())
                        {
                            especificacao.setUniddeMedida(uniddeMedida);
                        }
                    }
                    repository.save(especificacao);
                    especificacaos.add(especificacao);
                    material.setEspecificacoes(especificacaos);
                    Mrepository.save(material);
                    return new ResponseEntity<>(CREATED);
                }
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


    public ResponseEntity<EspecificacaoDTO> deletar(Long id)
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

}

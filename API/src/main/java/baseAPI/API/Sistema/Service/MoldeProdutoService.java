package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.DTO.MoldeDTO;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Especificacao;
import baseAPI.API.Sistema.Model.Material;
import baseAPI.API.Sistema.Model.MoldeProduto;
import baseAPI.API.Sistema.Repository.EspecificacaoRepository;
import baseAPI.API.Sistema.Repository.MaterialRepository;
import baseAPI.API.Sistema.Repository.MoldeProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class MoldeProdutoService {

    @Autowired
    MoldeProdutoRepository repository;
    @Autowired
    EspecificacaoRepository Erepositry;
    @Autowired
    MaterialRepository Mrepository;

    public ResponseEntity<List<MoldeProduto>> listar()
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


    public ResponseEntity<MoldeProduto> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<MoldeProduto> entidade = repository.findById(id);
                return new ResponseEntity<MoldeProduto>(entidade.get(), ACCEPTED);
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

    public ResponseEntity<MoldeProduto> buscarPorCodigo(String codigo)
    {
        try{
            if(codigo != null)
            {
                Optional<MoldeProduto> entidade = Optional.ofNullable(repository.findBycodigo(codigo));
                return new ResponseEntity<MoldeProduto>(entidade.get(), ACCEPTED);
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



    // salvar
    public ResponseEntity<MoldeDTO> salvar(MoldeDTO moldeDTO, String[] codigosEspecificacoes)
    {
        try{
            if(moldeDTO != null)
            {
                MoldeProduto moldeProduto = new MoldeProduto();
                BeanUtils.copyProperties(moldeDTO,moldeProduto);
                int cod = (int) (1001 + Math.random() * 8999);
                String codigo = "MD_"+cod;
                moldeProduto.setCodigo(codigo);
                List<Especificacao> lista = new ArrayList<>();
                for(String codigoE: codigosEspecificacoes)
                {
                    Especificacao especificacao = Erepositry.findBycodigo(codigoE);
                    lista.add(especificacao);
                }
                moldeProduto.setEspecificacoes(lista);
                moldeProduto.setValorTotalMateriais(moldeProduto.calValorTotal());
                repository.save(moldeProduto);
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


    //editar
    public ResponseEntity<MoldeDTO> editar(String codigo, String nome, String[] codigosEspecificacoes)
    {
        try{
            if(codigo != null)
            {
                MoldeProduto moldeProduto = repository.findBycodigo(codigo);
                if(moldeProduto != null)
                {
                    if (nome != null)
                    {
                        moldeProduto.setNome(nome);
                    }
                    List<Especificacao> lista = new ArrayList<>();
                    for(String codigoE: codigosEspecificacoes)
                    {
                        Especificacao especificacao = Erepositry.findBycodigo(codigoE);
                        lista.add(especificacao);
                    }
                    moldeProduto.setEspecificacoes(lista);
                    repository.save(moldeProduto);
                }
                repository.save(moldeProduto);
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

    public ResponseEntity<MoldeDTO> deletar(Long id)
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

    public ResponseEntity<MoldeDTO> deletarPorCodigo(String codigo)
    {
        try{
            if(codigo != null)
            {
                MoldeProduto moldeProduto = repository.findBycodigo(codigo);
                if(moldeProduto != null)
                {
                    repository.deleteById(moldeProduto.getId());
                }
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

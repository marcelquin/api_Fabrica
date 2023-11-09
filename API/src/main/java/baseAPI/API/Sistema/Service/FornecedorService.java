package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.Enum.AreaAtuacao;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Repository.EmpresaRepository;
import baseAPI.API.Sistema.Repository.FornecedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository repository;

    private static String caminhoImagem = "D:\\PROJETOS JAVA\\PROJETOS\\api_Fabrica\\API\\Upload\\Empresa\\";
    public ResponseEntity<List<Fornecedor>> listar()
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


    public ResponseEntity<Fornecedor> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<Fornecedor> entidade = repository.findById(id);
                return new ResponseEntity<Fornecedor>(entidade.get(), ACCEPTED);
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

    public ResponseEntity<Fornecedor> buscarPorcnpj(Long cnpj)
    {
        try{
            if(cnpj != null)
            {
                Optional<Fornecedor> entidade = Optional.ofNullable(repository.findBycnpj(cnpj));
                return new ResponseEntity<Fornecedor>(entidade.get(), ACCEPTED);
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

    public ResponseEntity<FornecedorDTO> salvar(AreaAtuacao areaAtuacao, FornecedorDTO fornecedorDTO, MultipartFile file)
    {
        try{
            if(fornecedorDTO != null)
            {
                Fornecedor fornecedor = new Fornecedor();
                BeanUtils.copyProperties(fornecedorDTO, fornecedor);
                if(areaAtuacao != null)
                {
                    if(areaAtuacao == AreaAtuacao.AUDIO)
                    {
                       fornecedor.setAreaAtuacao(AreaAtuacao.AUDIO);
                    }
                    if(areaAtuacao == AreaAtuacao.DISPLAY)
                    {
                        fornecedor.setAreaAtuacao(AreaAtuacao.DISPLAY);
                    }
                    if(areaAtuacao == AreaAtuacao.METALURGICA)
                    {
                        fornecedor.setAreaAtuacao(AreaAtuacao.METALURGICA);
                    }
                    if(areaAtuacao == AreaAtuacao.COSTURA_PERSONALIZACAO)
                    {
                        fornecedor.setAreaAtuacao(AreaAtuacao.COSTURA_PERSONALIZACAO);
                    }
                }
                if (file != null)
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+fornecedor.getCnpj()+"_"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                    fornecedor.setLogoMarca(fornecedor.getCnpj()+"_"+file.getOriginalFilename());
                }
                    repository.save(fornecedor);
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

    public ResponseEntity<FornecedorDTO> editar(Long cnpj, FornecedorDTO fornecedorDTO)
    {
        try{
            if(cnpj != null)
            {
                Fornecedor fornecedor = repository.findBycnpj(cnpj);
                if(fornecedor != null) {
                    BeanUtils.copyProperties(fornecedorDTO, fornecedor);
                    repository.save(fornecedor);
                    return new ResponseEntity<>(OK);
                }
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<FornecedorDTO> deletar(Long id)
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

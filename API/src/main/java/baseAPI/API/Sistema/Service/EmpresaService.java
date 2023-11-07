package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EmpresaDTO;
import baseAPI.API.Sistema.Model.Empresa;
import baseAPI.API.Sistema.Repository.EmpresaRepository;
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
public class EmpresaService {

    @Autowired
    EmpresaRepository repository;

    private static String caminhoImagem = "D:\\PROJETOS JAVA\\PROJETOS\\api_Fabrica\\API\\Upload\\Empresa\\";

    public ResponseEntity<List<Empresa>> listar()
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


    public ResponseEntity<Empresa> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<Empresa> entidadeteste = repository.findById(id);
                return new ResponseEntity<Empresa>(entidadeteste.get(), ACCEPTED);
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

    public ResponseEntity<Empresa> buscarPorcnpj(Long cnpj)
    {
        try{
            if(cnpj != null)
            {
                Optional<Empresa> entidadeteste = Optional.ofNullable(repository.findBycnpj(cnpj));
                return new ResponseEntity<Empresa>(entidadeteste.get(), ACCEPTED);
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

    public ResponseEntity<EmpresaDTO> salvar(EmpresaDTO empresaDTO, MultipartFile file)
    {
        try{
            if(empresaDTO != null)
            {
                Empresa empresa = new Empresa();
                BeanUtils.copyProperties(empresaDTO, empresa);
                if (file != null)
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+empresa.getCnpj()+"_"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                    empresa.setLogoMarca(empresa.getCnpj()+"_"+file.getOriginalFilename());
                }
                repository.save(empresa);
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

    public ResponseEntity<EmpresaDTO> editar(Long cnpj,EmpresaDTO empresaDTO)
    {
        try{
            if(empresaDTO != null && cnpj != null)
            {
                Empresa empresa = repository.findBycnpj(cnpj);
                if(empresa != null) {
                    BeanUtils.copyProperties(empresaDTO, empresa);
                    repository.save(empresa);
                    return new ResponseEntity<>(OK);
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

    public ResponseEntity<EmpresaDTO> deletar(Long id)
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

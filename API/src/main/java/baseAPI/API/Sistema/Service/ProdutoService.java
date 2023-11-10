package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ProdutoDTO;
import baseAPI.API.Sistema.Model.*;
import baseAPI.API.Sistema.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    MoldeProdutoRepository Mrepository;

    @Autowired
    EmpresaRepository Erepository;

    @Autowired
    MaterialRepository MTrepository;

    @Autowired
    VerificadorRepository Vrepository;

    private static String caminhoImagem = "D:\\PROJETOS JAVA\\PROJETOS\\api_Fabrica\\API\\Upload\\Produto\\";



    public ResponseEntity<List<Produto>> listar()
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

    public ResponseEntity<Produto> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<Produto> entidade = repository.findById(id);
                return new ResponseEntity<Produto>(entidade.get(), ACCEPTED);
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


    public ResponseEntity<Produto> buscarPorCodigo(String codigo)
    {
        try{
            if(codigo != null)
            {
                Optional<Produto> entidade = Optional.ofNullable(repository.findBycodigo(codigo));
                return new ResponseEntity<Produto>(entidade.get(), ACCEPTED);
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


    public ResponseEntity<ProdutoDTO> Salvar(ProdutoDTO produtoDTO, String codigoMolde, MultipartFile file)
    {
        try{

            if(produtoDTO != null)
            {
                Produto produto = new Produto();
                produto.setNome(produtoDTO.getNome());
                produto.setDescrisao(produtoDTO.getDescrisao());
                Empresa empresa = new Empresa();
                List<Produto> produtos = new ArrayList<>();
                Double valorTotal;
                Double porcentagem = 40.0/100;
                int cd = (int) (100001 + Math.random() * 899999);;
                String codigo = "prod_"+cd;
                produto.setCodigo(codigo);
                if(codigoMolde != null)
                {
                    MoldeProduto moldeProduto = Mrepository.findBycodigo(codigoMolde);
                    if(moldeProduto != null)
                    {
                        valorTotal = (moldeProduto.getValorTotalMateriais() * porcentagem) + moldeProduto.getValorTotalMateriais();
                        produto.setMoldeNome(moldeProduto.getNome());
                        produto.setMoldeCodigo(moldeProduto.getCodigo());
                        produto.setValor(valorTotal);
                    }
                }
                if(!file.isEmpty())
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+"prod_"+codigo+"_"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                    produto.setImagem("prod_"+codigo+"_"+file.getOriginalFilename());
                }
                repository.save(produto);
                produtos.add(produto);
                Verificador verificador = Vrepository.findBycodigo("VFD_183965");
                if(verificador != null) {
                    verificador.getProdutos().add(produto);
                    Vrepository.save(verificador);
                }
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


    public ResponseEntity<ProdutoDTO> editar(String codigoProduto,ProdutoDTO produtoDTO, String codigoMolde, MultipartFile file)
    {
        try{
            if(codigoProduto != null)
            {
                Produto produto = repository.findBycodigo(codigoProduto);
                Double valorTotal;
                Double porcentagem = 40.0/100;
                if(produto != null)
                {
                    BeanUtils.copyProperties(produtoDTO,produto);
                    if(codigoMolde != null)
                    {
                        MoldeProduto moldeProduto = Mrepository.findBycodigo(codigoMolde);
                        if(moldeProduto != null)
                        {
                            valorTotal = (moldeProduto.getValorTotalMateriais() * porcentagem) + moldeProduto.getValorTotalMateriais();
                            produto.setNome(moldeProduto.getNome());
                            produto.setMoldeCodigo(moldeProduto.getCodigo());
                            produto.setValor(valorTotal);
                        }
                    }
                    {
                        byte[] bytes = file.getBytes();
                        Path caminho = Paths.get(caminhoImagem+"prod_"+produto.getCodigo()+"_"+file.getOriginalFilename());
                        Files.write(caminho, bytes);
                        produto.setImagem("prod_"+produto.getCodigo()+"_"+file.getOriginalFilename());
                    }
                }
                repository.save(produto);
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


    public ResponseEntity<ProdutoDTO> fabricarProduto(String codigoProduto, Double quantidade)
    {
        try{
            if(codigoProduto != null)
            {
                Produto produto = repository.findBycodigo(codigoProduto);
                produto.setEstoque(quantidade);
                if(produto != null)
                {
                    System.out.println(".");
                 MoldeProduto moldeProduto = Mrepository.findBycodigo(produto.getMoldeCodigo());
                 if(moldeProduto != null)
                 {
                     System.out.println(".");
                 }
                    System.out.println(".");
                    baixaMaterial(codigoProduto);
                    //verificador

                    //
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

    public void baixaMaterial(String codigoProduto)
    {
        try{
            if(codigoProduto != null)
                System.out.println("baixa material entrou");
            {
                Produto produto = repository.findBycodigo(codigoProduto);
                if(produto != null)
                {
                    MoldeProduto moldeProduto = Mrepository.findBycodigo(produto.getMoldeCodigo());
                    if(moldeProduto != null)
                    {
                        for(Especificacao item : moldeProduto.getEspecificacoes())
                        {
                            System.out.println("baixa material entrou no for");
                            Material material = MTrepository.findBycodigo(item.getMaterial().getCodigo());
                            if(material != null)
                            {
                                Double quantidade = item.getQuantidade() * produto.getEstoque();
                                material.setQuantidade( material.getQuantidade() - quantidade);
                                MTrepository.save(material);
                            }
                        }
                    }
                }
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
    }


    public ResponseEntity<ProdutoDTO> deletar(Long id)
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

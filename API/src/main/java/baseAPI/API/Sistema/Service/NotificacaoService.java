package baseAPI.API.Sistema.Service;


import baseAPI.API.Sistema.Enum.TipoAviso;
import baseAPI.API.Sistema.Model.*;

import baseAPI.API.Sistema.Repository.MaterialRepository;
import baseAPI.API.Sistema.Repository.NotificacaoRepository;
import baseAPI.API.Sistema.Repository.ProdutoRepository;
import baseAPI.API.Sistema.Repository.VerificadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@Service
public class NotificacaoService {

    @Autowired
    VerificadorRepository Vrepository;
    @Autowired
    NotificacaoRepository repository;

    @Autowired
    ProdutoRepository Prepository;

    @Autowired
    MaterialRepository Mrepository;

    public ResponseEntity<List<Notificacao>> listar()
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


    public ResponseEntity<Notificacao> buscarPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                Optional<Notificacao> entidade = repository.findById(id);
                return new ResponseEntity<Notificacao>(entidade.get(), ACCEPTED);
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


    public void VerificaEstoques()
    {
        try{
            Verificador verificador = Vrepository.findBycodigo("VFD_183965");
            if(verificador != null)
            {
                for(Produto item : verificador.getProdutos())
                {
                    estoqueProdutoBaixo(item.getCodigo());
                }
                for(Material item : verificador.getMateriais())
                {
                    estoqueMaterialBaixo(item.getCodigo());
                }
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
    }

    public void estoqueProdutoBaixo(String codigoProduto)
    {
        try{
            if(codigoProduto != null)
            {
                Produto produto = Prepository.findBycodigo(codigoProduto);
                if(produto != null)
                {
                    if(produto.getEstoque() < 30)
                    {
                        Notificacao notificacao = new Notificacao();
                        int cod = (int) (100001 + Math.random() * 899999);
                        String codigo = "Nt_"+cod;
                        notificacao.setCodigo(codigo);
                        notificacao.setProduto(produto);
                        notificacao.setTipoAviso(TipoAviso.Estoque_Produto_Baixo);
                        notificacao.setMensagem("o produto "+produto.getNome()+" do codigo: "+produto.getCodigo()+
                                                " Esta abaixo do estoque pré definido");
                        repository.save(notificacao);
                    }

                }

            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
    }

    public void estoqueMaterialBaixo(String codigomaterial)
    {
        try{

            if(codigomaterial != null)
            {
                Material material = Mrepository.findBycodigo(codigomaterial);
                if(material != null)
                {
                    if(material.getQuantidade() < 50)
                    {
                        Notificacao notificacao = new Notificacao();
                        int cod = (int) (100001 + Math.random() * 899999);
                        String codigo = "Nt_"+cod;
                        notificacao.setCodigo(codigo);
                        notificacao.setMaterial(material);
                        notificacao.setTipoAviso(TipoAviso.Estoque_Material_Baixo);
                        notificacao.setMensagem("a materia prima "+material.getNome()+" do codigo: "+material.getCodigo()+
                                " Esta abaixo do estoque pré definido");
                        repository.save(notificacao);
                    }
                    }
                }

        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
    }


    public ResponseEntity<Notificacao> deletar(Long id)
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

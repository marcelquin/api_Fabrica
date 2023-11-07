package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Repository.MoldeProdutoRepository;
import baseAPI.API.Sistema.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;
}

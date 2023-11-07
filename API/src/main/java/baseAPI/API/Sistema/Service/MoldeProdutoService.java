package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Repository.MaterialRepository;
import baseAPI.API.Sistema.Repository.MoldeProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoldeProdutoService {

    @Autowired
    MoldeProdutoRepository repository;
}

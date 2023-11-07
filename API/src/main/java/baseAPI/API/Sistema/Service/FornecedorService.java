package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Repository.EmpresaRepository;
import baseAPI.API.Sistema.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository repository;
}

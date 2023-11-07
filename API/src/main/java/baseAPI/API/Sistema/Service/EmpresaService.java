package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository repository;
}

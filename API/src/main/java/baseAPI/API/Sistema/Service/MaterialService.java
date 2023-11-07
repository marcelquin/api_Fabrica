package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository repository;
}

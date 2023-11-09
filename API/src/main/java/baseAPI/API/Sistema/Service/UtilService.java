package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Model.Material;
import baseAPI.API.Sistema.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    @Autowired
    MaterialRepository repository;

    public void baixaMaterial(String codigoMaterial, Double quantidade)
    {
        try{
            if(codigoMaterial != null && quantidade != null)
            {
                Material material = repository.findBycodigo(codigoMaterial);
                material.setQuantidade(material.getQuantidade() - quantidade);
                repository.save(material);
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
    }




}

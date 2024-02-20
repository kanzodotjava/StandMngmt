package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Model;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    public List<Model> getModels() {
        return modelRepository.findAll();
    }

    public Model updateModel(long id, Model updatedModel) {
        if (modelRepository.existsById(id)) {
            Model existingModel = modelRepository.findById(id).orElse(null);
            if (existingModel != null) {
                existingModel.setName(updatedModel.getName());
                existingModel.setId(id);
                updatedModel.setBrand(existingModel.getBrand());
                return modelRepository.save(existingModel);
            }
        }
        return null;
    }
}
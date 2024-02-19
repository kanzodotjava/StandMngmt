package pt.upskill.webapi.StandMngmt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.webapi.StandMngmt.Models.Car;
import pt.upskill.webapi.StandMngmt.Models.Model;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public Model createModel(Model model) {
        return modelRepository.save(model);
    }
}
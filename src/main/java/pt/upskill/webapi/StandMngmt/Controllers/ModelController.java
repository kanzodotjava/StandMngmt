package pt.upskill.webapi.StandMngmt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.webapi.StandMngmt.Models.Model;
import pt.upskill.webapi.StandMngmt.Services.BrandService;
import pt.upskill.webapi.StandMngmt.Services.ModelService;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable long id, @RequestBody Model model) {
        Model updatedModel = modelService.updateModel(id, model);
        return new ResponseEntity<>(updatedModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Model>> getModels() {
        List<Model> models = modelService.getModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model createdModel = modelService.createModel(model);
        return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
    }

}

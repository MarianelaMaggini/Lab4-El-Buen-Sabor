package com.example.buensabor.controllers.rubro;

import com.example.buensabor.controllers.base.BaseController;
import com.example.buensabor.dtos.RubroDto;
import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.services.rubro.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rubros")
public class RubroController extends BaseController<Rubro, RubroDto, RubroService> {

    @Autowired
    public RubroController(RubroService service) {
        super(service);
    }

    @GetMapping("/todosActivos")
    public List<Rubro> getRubrosSinFechaDeBaja() {
        return service.getRubrosSinFechaDeBaja() ;
    }


    @GetMapping("/rubro")
    public List<Rubro> getRubroByIdNotInsumo(@RequestParam("id") Long id) {
        return service.getRubroByIdArticuloInsumo(id);
    }
}

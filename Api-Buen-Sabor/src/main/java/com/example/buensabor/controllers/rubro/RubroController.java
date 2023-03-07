package com.example.buensabor.controllers.rubro;

import com.example.buensabor.controllers.base.BaseController;
import com.example.buensabor.dtos.RubroDto;
import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.services.rubro.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rubros")
public class RubroController extends BaseController<Rubro, RubroDto, RubroService> {

    @Autowired
    public RubroController(RubroService service) {
        super(service);
    }

    @GetMapping("/todosActivos")
    public ResponseEntity<?> getRubrosSinFechaDeBaja() {
        return ResponseEntity.ok(service.getRubrosSinFechaDeBaja());
    }


    @GetMapping("/rubro")
    public ResponseEntity<?> getRubroByIdNotInsumo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.getRubroByIdArticuloInsumo(id));
    }
}

package com.example.buensabor.controllers.reportes;

import com.example.buensabor.entities.reportes.CantidadPedidos;
import com.example.buensabor.entities.reportes.Ingresos;
import com.example.buensabor.entities.reportes.MontoGanancia;
import com.example.buensabor.entities.reportes.RankingComidas;
import com.example.buensabor.services.reportes.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RestController
@RequestMapping("/reportes") // Ruta principal
public class ReportesController {

    @Autowired
    ReportesService reportesService;

    @GetMapping("/cantidadPedidos")
    public List<CantidadPedidos> getCantidadPedidos(@RequestParam("fechaDesde") String fechaDesde, @RequestParam("fechaHasta") String fechaHasta) {
        return reportesService.getCantidadPedidos(fechaDesde, fechaHasta);
    }

    @GetMapping("/rankingComidas")
    public List<RankingComidas> getRankingComidas(@RequestParam("fechaDesde") String fechaDesde, @RequestParam("fechaHasta") String fechaHasta) {
        return reportesService.getRankingComidas(fechaDesde, fechaHasta);
    }

    @GetMapping("/montoGanancia")
    public MontoGanancia getMontoGanancia(@RequestParam("fechaDesde") String fechaDesde, @RequestParam("fechaHasta") String fechaHasta) {
        return reportesService.getMontoGanancia(fechaDesde, fechaHasta);
    }

    @GetMapping("/ingresosDiarios")
    public List<Ingresos> getIngresosDiarios() {
        return reportesService.getIngresosDiarios();
    }

    @GetMapping("/ingresosMensuales")
    public List<Ingresos> getIngresosMensuales() {
        return reportesService.getIngresosMensuales();
    }

}

package com.example.buensabor.services.reportes;

import com.example.buensabor.repositories.reportes.ReportesRepository;
import com.example.buensabor.entities.reportes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportesService {

    @Autowired
    ReportesRepository reportesRepository;

    public List<CantidadPedidos> getCantidadPedidos(String fechaDesde, String fechaHasta) {
        return reportesRepository.getCantidadPedidos(fechaDesde, fechaHasta);
    }

    public List<RankingComidas> getRankingComidas(String fechaDesde, String fechaHasta) {
        return reportesRepository.getRankingComidas(fechaDesde, fechaHasta);
    }

    public MontoGanancia getMontoGanancia(String fechaDesde, String fechaHasta) {
        return reportesRepository.getMontoGanancia(fechaDesde, fechaHasta);
    }

    public List<Ingresos> getIngresosDiarios() {
        return reportesRepository.getIngresosDiarios();
    }

    public List<Ingresos> getIngresosMensuales() {
        return reportesRepository.getIngresosMensuales();
    }

}

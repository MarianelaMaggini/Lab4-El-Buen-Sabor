package com.example.buensabor.services.tiempo;

import com.example.buensabor.utils.Tiempo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class TiempoService {
    public Tiempo getTiempo(){
        Tiempo tiempo = new Tiempo();
        tiempo.setHora(LocalTime.now().getHour());
        tiempo.setMinuto(LocalTime.now().getMinute());
        tiempo.setDiaNumero(LocalDate.now().getDayOfWeek().getValue());
        tiempo.setDiaNombre(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
        tiempo.setZona(ZonedDateTime.now().getZone().getId());
        return tiempo;
    }
}

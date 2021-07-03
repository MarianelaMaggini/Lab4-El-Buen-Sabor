package com.example.buensabor.entities.archivos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Archivo tiene los atributos de name y url
 * Se utiliza anotaciones lombok para constructores, getters and setters
 * @author Maggini - Panella - Tarditi
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archivo {
    private String name;
    private String url;
}

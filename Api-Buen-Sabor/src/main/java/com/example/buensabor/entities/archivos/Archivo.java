package com.example.buensabor.entities.archivos;

import lombok.*;

/**
 * Clase Archivo tiene los atributos de name y url
 * Se utiliza anotaciones lombok para constructores, getters and setters
 * @author Maggini - Panella - Tarditi
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Archivo {
    private String name;
    private String url;
}

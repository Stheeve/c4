package org.example.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AntService {

    @Cacheable(value = "puntosLicencia", key = "#cedula")
    public String consultarPuntos(String cedula, String placa) {
        System.out.println("--- CONSULTANDO A LA ANT (Simulado) ---");
        try {
            // 1. Simulamos lentitud del sistema ANT (1.5 segundos)
            Thread.sleep(1500);

            // 2. Simulamos puntos variables según el último dígito de la cédula
            // Así parecerá real: diferentes cédulas tienen diferentes puntos.
            char ultimoDigito = cedula.length() > 0 ? cedula.charAt(cedula.length() - 1) : '0';
            int basePuntos = 15;

            // Cálculo simple: Si termina en 9 tiene 30, si termina en 1 tiene 17, etc.
            int puntosCalculados = basePuntos + Character.getNumericValue(ultimoDigito);
            if(puntosCalculados > 30) puntosCalculados = 30; // Máximo 30 puntos

            return puntosCalculados + " Puntos (Licencia Tipo B)";

        } catch (Exception e) {
            return "Error al conectar con ANT";
        }
    }
}
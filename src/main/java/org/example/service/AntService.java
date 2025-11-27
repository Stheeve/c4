package org.example.service; // <--- AJUSTADO

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AntService {

    @Cacheable(value = "puntosLicencia", key = "#cedula")
    public String obtenerPuntos(String cedula, String placa) {
        System.out.println("--- CONSULTANDO A LA ANT (Simulando lentitud) ---");
        try {
            Thread.sleep(2000); // Simulamos la demora
            return "30 Puntos (Licencia Tipo B)";
        } catch (InterruptedException e) {
            return "Error de conexión ANT";
        }
    }
}
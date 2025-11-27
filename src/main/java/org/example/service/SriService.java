package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class SriService {
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean esContribuyente(String ruc) {
        try {
            String url = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/existePorNumeroRuc?numeroRuc=" + ruc;
            return Boolean.TRUE.equals(restTemplate.getForObject(url, Boolean.class));
        } catch (Exception e) { return false; }
    }

    public Map<String, Object> obtenerDatosPersona(String ruc) {
        try {
            String url = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?&ruc=" + ruc;
            Object[] respuesta = restTemplate.getForObject(url, Object[].class);
            if (respuesta != null && respuesta.length > 0) {
                return (Map<String, Object>) respuesta[0];
            }
        } catch (Exception e) { e.printStackTrace(); }
        return new HashMap<>();
    }

    public Object obtenerVehiculo(String placa) {
        try {
            String url = "https://srienlinea.sri.gob.ec/sri-matriculacion-vehicular-recaudacion-servicio-internet/rest/BaseVehiculo/obtenerPorNumeroPlacaOPorNumeroCampvOPorNumeroCpn?numeroPlacaCampvCpn=" + placa;
            return restTemplate.getForObject(url, Object.class);
        } catch (Exception e) { return "Vehículo no encontrado"; }
    }
}
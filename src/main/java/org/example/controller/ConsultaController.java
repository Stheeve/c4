package org.example.controller; // <--- AJUSTADO

import org.example.service.AntService;
import org.example.service.SriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired private SriService sriService;
    @Autowired private AntService antService;

    @GetMapping("/validar-ruc/{ruc}")
    public Map<String, Object> validarRuc(@PathVariable String ruc) {
        Map<String, Object> response = new HashMap<>();
        boolean existe = sriService.existeRuc(ruc);

        if (existe) {
            response.put("valido", true);
            response.put("datos", sriService.getDatosPersona(ruc));
        } else {
            response.put("valido", false);
        }
        return response;
    }

    @GetMapping("/info-completa")
    public Map<String, Object> obtenerInfo(@RequestParam String placa, @RequestParam String ruc) {
        Map<String, Object> response = new HashMap<>();
        response.put("vehiculo", sriService.getVehiculo(placa));

        String cedula = ruc.length() >= 10 ? ruc.substring(0, 10) : ruc;
        response.put("puntos", antService.obtenerPuntos(cedula, placa));

        return response;
    }
}
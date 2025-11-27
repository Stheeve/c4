package org.example.controller;

import org.example.service.AntService;
import org.example.service.SriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ConsultaController {

    @Autowired private SriService sriService;
    @Autowired private AntService antService;

    @GetMapping("/validar-ruc/{ruc}")
    public Map<String, Object> validar(@PathVariable String ruc) {
        Map<String, Object> resp = new HashMap<>();
        if (sriService.esContribuyente(ruc)) {
            resp.put("existe", true);
            resp.put("datos", sriService.obtenerDatosPersona(ruc));
        } else {
            resp.put("existe", false);
        }
        return resp;
    }

    @GetMapping("/info-vehicular")
    public Map<String, Object> info(@RequestParam String placa, @RequestParam String ruc) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("vehiculo", sriService.obtenerVehiculo(placa));

        // Extraemos la cédula del RUC (primeros 10 dígitos)
        String cedula = ruc.length() >= 10 ? ruc.substring(0, 10) : ruc;
        resp.put("puntos", antService.consultarPuntos(cedula, placa));

        return resp;
    }
}
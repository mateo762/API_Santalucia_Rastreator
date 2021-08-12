package com.globalzepp.santalucia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalzepp.santalucia.api.controller.beans.request.DecesosPeticion;
import com.globalzepp.santalucia.api.controller.beans.request.HogarPeticion;
import com.globalzepp.santalucia.api.controller.beans.response.DecesosRespuesta;
import com.globalzepp.santalucia.api.controller.beans.response.HogarRespuesta;
import com.globalzepp.santalucia.api.service.SearchService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/busqueda")
public class SearchController {

	@Autowired
	SearchService searchService;
	
	@PostMapping("/decesos")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Búsqueda de Decesos") })
	public DecesosRespuesta decesos(@RequestBody DecesosPeticion decesosPeticion) {
		return searchService.searchDecesos(decesosPeticion);
	}
	
	@PostMapping("/hogar")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Búsqueda de Hogar") })
	public HogarRespuesta hogar(@RequestBody HogarPeticion hogarPeticion) {
		return searchService.searchHogar(hogarPeticion);
	}
}

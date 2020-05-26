package com.csipl.lpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csipl.lpg.service.JasperReportService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "jasperReport/")

public class JasperReportController {
   @Autowired
   private JasperReportService jasperReportService;
	
	
	@GetMapping("/download")
	public String empReport() {
		return jasperReportService.generateReport();
	}

}

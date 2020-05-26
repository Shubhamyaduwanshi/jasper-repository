package com.csipl.lpg.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csipl.lpg.model.Employee;
import com.csipl.lpg.model.LatterPad;
import com.csipl.lpg.service.LatterPadService;
import com.csipl.lpg.util.ExcelGenerator;
import com.csipl.lpg.util.GeneratePdfReport;
import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "latterpad/")
public class LatterPadController {

	@Autowired
	private LatterPadService latterpadService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	final ModelAndView model = new ModelAndView();

	@PostMapping(value = "addLatterPad")
	public void addDetails(@RequestBody LatterPad latterpad) {
		latterpadService.addDetals(latterpad);
	}

	@GetMapping(value = "getlatterpad")
	public List<LatterPad> getAllLatterPad() {
		return latterpadService.getAllLatterPad();
	}

	@GetMapping(value = "pdf/{id}/latterpad.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getLatterPad(@PathVariable long id) throws DocumentException {
		LatterPad latterpadObj = latterpadService.getLatterPad(id);
		ByteArrayInputStream bis = GeneratePdfReport.laterpadReport(latterpadObj);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=latterpad.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@GetMapping(value = "download/latterpad.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
		List<LatterPad> latterpadObj = (List<LatterPad>) latterpadService.findAllLatterPad();

		ByteArrayInputStream in = ExcelGenerator.latterpadToExcel(latterpadObj);
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	

}

package com.csipl.lpg.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.csipl.lpg.model.Employee;
import com.csipl.lpg.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class JasperReportServiceImp  implements JasperReportService{
	Logger log=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Override
	public String generateReport() {
        String path="E:\\jasperReport\\";
		List<Employee> employees = new ArrayList<>();
		
		employees = (List<Employee>) employeeRepository.findAll();
		log.info("Employee:"+employees);
		try {
			File file = ResourceUtils.getFile("../Latterpad_Generator/src/main/resources/templates/employee-rpt.jrxml");
			InputStream input = new FileInputStream(file);
			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			// Get your data source
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);
			// Add parameters
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", " Computronics india Pvt Ltd");
			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"Employee.pdf");
			log.info("PDF File Generated !!");
			JasperExportManager.exportReportToXmlFile(jasperPrint, path+"Employee.xml", true);
			log.info("XML File Generated !!");
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"Employee.html");
			log.info("HTML Generated");
			xlsx(jasperPrint);
			csv(jasperPrint);
			return "Report successfully generated @path= " + path;

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	private void csv(JasperPrint jasperPrint) throws JRException {
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput("E:\\jasperReport\\Employee.csv"));
		SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
		configuration.setFieldDelimiter(",");
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}


	// https://www.programcreek.com/java-api-examples/?class=net.sf.jasperreports.export.SimpleXlsxReportConfiguration&method=setOnePagePerSheet
	private void xlsx(JasperPrint jasperPrint) throws JRException {
		// Exports a JasperReports document to XLSX format. It has character output type
		// and exports the document to a grid-based layout.
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("E:\\jasperReport\\Employee.xlsx"));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setRemoveEmptySpaceBetweenColumns(true);
		configuration.setDetectCellType(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}


	
}

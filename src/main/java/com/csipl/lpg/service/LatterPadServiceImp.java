package com.csipl.lpg.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.csipl.lpg.model.Employee;
import com.csipl.lpg.model.LatterPad;
import com.csipl.lpg.repository.EmployeeRepository;
import com.csipl.lpg.repository.LatterPadRepository;

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
public class LatterPadServiceImp implements LatterPadService {
     
	Logger log=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LatterPadRepository latterPadRepository;

	
	@Override
	public Object generateLatterPad() {
		System.out.println("Service called:" + latterPadRepository.count());

		return null;
	}

	@Override
	public List<LatterPad> getAllLatterPad() {
		return (List<LatterPad>) latterPadRepository.findAll();
	}

	@Override
	public void addDetals(LatterPad latterpad) {
		latterPadRepository.save(latterpad);
	}

	@Override
	public LatterPad getLatterPad(long id) {
		Optional<LatterPad> latterpad = latterPadRepository.findById(id);
		if (latterpad.isPresent()) {
			return latterpad.get();
		}
		return null;
	}

	@Override
	public List<LatterPad> findAllLatterPad() {
		// TODO Auto-generated method stub
		return null;
	}

}

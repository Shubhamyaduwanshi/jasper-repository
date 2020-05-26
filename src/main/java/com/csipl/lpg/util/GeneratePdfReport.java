package com.csipl.lpg.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csipl.lpg.model.LatterPad;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class GeneratePdfReport {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

	public static ByteArrayInputStream laterpadReport(LatterPad latterpadObj) throws DocumentException {

		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new CMYKColor(255, 200, 0, 0));
		Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(255, 200, 0, 0));
		Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(255, 200, 0, 0));

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);
		document.open();

		Paragraph title = new Paragraph(latterpadObj.getTital(), blueFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		Paragraph address = new Paragraph(latterpadObj.getAddress(), redFont);
		address.setAlignment(Element.ALIGN_CENTER);
		document.add(address);

		Paragraph mobile = new Paragraph("mobile:" +latterpadObj.getMobile(), yellowFont);
		mobile.setAlignment(Element.ALIGN_CENTER);
		mobile.setSpacingAfter(10);
		document.add(mobile);

		LineSeparator sep = new LineSeparator();
		sep.setLineWidth(1);
		document.add(sep);


		Paragraph date = new Paragraph("Date:"+latterpadObj.getDate(), yellowFont);
		date.setAlignment(Element.ALIGN_RIGHT);
		date.setSpacingAfter(10);
		document.add(date);

		try {
			Image image = Image.getInstance("target/logo2.png");
			image.scaleAbsolute(110f, 50f);
			image.setAbsolutePosition(490, 0);
			document.add(image);
		} catch (MalformedURLException e) {
			logger.error("Error occurred: {0}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error occurred: {0}", e);
			e.printStackTrace();
		}

		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
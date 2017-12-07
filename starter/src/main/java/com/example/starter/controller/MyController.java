package com.example.starter.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class MyController {

	@RequestMapping("/test")
	public void example(HttpServletRequest request, HttpServletResponse response) {
		Document document = new Document();
	    try {
			PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
			document.open();
		    document.setPageSize(PageSize.A4);
		    Paragraph p = new Paragraph();
	        p.add("This is my paragraph 1");
	        p.setAlignment(Element.ALIGN_CENTER);
	        document.add(p);
	    //sample data
	    List<InvoiceLine> lines = new ArrayList<InvoiceLine>();
	    InvoiceLine line1 = new InvoiceLine();
	    line1.setCustomer("mark");
	    line1.setProduct("SKU1");
	    line1.setInvoiceNumber("CV10000");
	    line1.setLineamount(100);
	    InvoiceLine line2 = new InvoiceLine();
	    line2.setCustomer("mark");
	    line2.setProduct("SKU2");
	    line2.setInvoiceNumber("CV10001");
	    line2.setLineamount(100);
	    InvoiceLine line3 = new InvoiceLine();
	    line3.setCustomer("john");
	    line3.setProduct("SKU3");
	    line3.setInvoiceNumber("CV10002");
	    line3.setLineamount(101);
	    InvoiceLine line4 = new InvoiceLine();
	    line4.setCustomer("john");
	    line4.setProduct("SKU4");
	    line4.setInvoiceNumber("CV10003");
	    line4.setLineamount(102);
	    lines.add(line1);
	    lines.add(line2);
	    lines.add(line3);
	    lines.add(line4);
	    System.out.println("-----------------");
	    System.out.println(lines.toString());
	    //data end
	    List<String> customers = new ArrayList<String>();
	    for (InvoiceLine line : lines) {
			if(!customers.contains(line.getCustomer()))
				customers.add(line.getCustomer());
		}
	    Map<String, List<InvoiceLine>> map= new HashMap<>();
	    for (String cust : customers) {
	    	List<InvoiceLine> custlinelist = new ArrayList<InvoiceLine>();
			for (InvoiceLine line : lines) {
				if(line.getCustomer().equalsIgnoreCase(cust))
					custlinelist.add(line);
			}
			map.put(cust, custlinelist);
		}
	    for (String custname : map.keySet()) {
	    	System.out.println("----------------- customer");
	    	List<InvoiceLine> custlinelist = map.get(custname);
	    	System.out.println(custlinelist.toString());
	    	PdfPTable table = new PdfPTable(3);
	    	PdfPCell celll = new PdfPCell(new Phrase(custname,new Font()));
		    celll.setColspan(3);
		    celll.setHorizontalAlignment(Element.ALIGN_LEFT);
		    table.addCell(celll);
	    	table.addCell("Invoice number");
	    	table.addCell("Product name");
	    	table.addCell("Amount");
	    	Long total=0l;
	    	for (InvoiceLine invoiceLine : custlinelist) {
	    		table.addCell(invoiceLine.getInvoiceNumber());
	    		table.addCell(invoiceLine.getProduct());
	    		table.addCell(invoiceLine.getLineamount()+"");
	    		total+=invoiceLine.getLineamount();
			}
	    	PdfPCell cell = new PdfPCell(new Phrase("Total",new Font()));
		    cell.setColspan(2);
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    table.addCell(cell);
	    	PdfPCell cell1 = new PdfPCell(new Phrase(total+"",new Font()));
		    cell1.setColspan(1);
		    cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    table.addCell(cell1);
		    document.add(table);
		}
	      document.close();
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        System.out.println("Done");
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream inputStream = null;
        try {
          File file = new File("test.pdf");
          if (file.exists()) {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            byteArrayOutputStream = new ByteArrayOutputStream();
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
              byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            OutputStream os = response.getOutputStream();
            byteArrayOutputStream.writeTo(os);
          }
        } catch (Exception e) {
        } finally {
          try {
            if (null != inputStream)
              inputStream.close();
            if (null != byteArrayOutputStream) {
              byteArrayOutputStream.flush();
              byteArrayOutputStream.close();
            }
          } catch (Exception e) {
          }
        }
	}
}

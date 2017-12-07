package com.example.starter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@SpringBootApplication
public class StarterApplication {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		SpringApplication.run(StarterApplication.class, args);
	}
}

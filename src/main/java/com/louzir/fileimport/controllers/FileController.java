package com.louzir.fileimport.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@RestController
@RequestMapping("/api/files")
public class FileController {

@PostMapping("/csv") 
public String parseCSV(@RequestParam MultipartFile file) throws IOException, CsvException {
  Reader reader = new InputStreamReader(file.getInputStream()); 
  // Parse CSV data
  CSVReader csvReader = new CSVReaderBuilder(reader).build();
  List<String[]> rows = csvReader.readAll();
  // Analyze data...
  return "Processed " + rows.size() + " rows!";
}

@PostMapping("/excel")
public String parseExcel(@RequestParam MultipartFile file) throws IOException {
  XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
  // Loop through sheets
  for(Sheet sheet : workbook) {
    System.out.println(sheet.getSheetName());
    // Loop through rows
    for(Row row : sheet) {
      // Process cell datas
    }
  }

  return "Processed Excel with " + workbook.getNumberOfSheets() + " sheets!";
}

}
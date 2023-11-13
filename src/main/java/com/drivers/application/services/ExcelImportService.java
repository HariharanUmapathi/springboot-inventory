package com.drivers.application.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivers.application.dao.ServiceAreaRepo;
import com.drivers.application.model.ServiceArea;

@Service
public class ExcelImportService {
    @Autowired
    ServiceAreaRepo serviceAreaRepo;

    public XSSFWorkbook getWorkBookFromFile(String filename) throws IOException {
        // obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(filename));
        // creating workbook instance that
        // refers to .xls file

        XSSFWorkbook wb = new XSSFWorkbook(fis); // creating a Sheet object to retrieve the object
        fis.close();
        System.out.println("Workbook Reference Closed");
        return wb;
    }

    public String doNothing() {
        System.out.println("Read Done ");
        return "Read Done ";
    }

    public void importServiceAreas() {
        try {
            XSSFWorkbook wb = getWorkBookFromFile(
                    "H:\\spring-boot-workspace\\springboot-inventory\\src\\main\\resources\\worldcities.xlsx");

            XSSFSheet sheet = wb.getSheetAt(0);
            // evaluating cell type
            int i = 0;
            for (Row row : sheet) // iteration over row using for each loop
            {
                if (row.getRowNum() == 0)
                    continue;
                ServiceArea serviceArea = new ServiceArea();
                try {
                    int serviceId = (int) row.getCell(10).getNumericCellValue();
                    serviceArea.setCity(row.getCell(0).getStringCellValue());
                    serviceArea.setCityAscii(row.getCell(1).getStringCellValue());
                    serviceArea.setLat(row.getCell(2).getNumericCellValue());
                    serviceArea.setLng(row.getCell(3).getNumericCellValue());
                    serviceArea.setCountry(row.getCell(4).getStringCellValue());
                    serviceArea.setIso2(row.getCell(5).getStringCellValue());
                    serviceArea.setIso3(row.getCell(6).getStringCellValue());
                    serviceArea.setAdminName(row.getCell(7).getStringCellValue());
                    serviceArea.setCapital(
                            row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                    serviceArea.setPopulation((int) row.getCell(9).getNumericCellValue());
                    serviceArea.setSheetId(serviceId);
                    serviceAreaRepo.save(serviceArea);
                    System.out.println(i++);
                } catch (NullPointerException npe) {
                    System.out.println(npe.getMessage());
                }

            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    ExcelImportService() {

    }

}

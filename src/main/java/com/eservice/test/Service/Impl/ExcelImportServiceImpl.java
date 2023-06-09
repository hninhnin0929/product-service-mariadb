package com.eservice.test.Service.Impl;

import com.eservice.test.Entity.*;
import com.eservice.test.Repository.CategoryRepository;
import com.eservice.test.Repository.HashCodeRepository;
import com.eservice.test.Service.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("excelImportService")
public class ExcelImportServiceImpl implements ExcelImportService {

    @Autowired
    private HashCodeRepository hashCodeRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private HashCodeService hashCodeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReviewService reviewService;

    private XSSFSheet getSheetFromFile(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    public Response importHashCode() {
        XSSFSheet sheet = getSheetFromFile("C:\\Users\\Public\\hashcode.xlsx");
        if (sheet == null) return new Response(false, "File not found.");

        for (int row = 1; row < 500; row++) {
            HashCode hashCode = new HashCode(sheet.getRow(row));
            hashCodeService.save(hashCode);
        }
        return new Response(true, "Success");
    }

    public Response importCategory() {
        XSSFSheet sheet = getSheetFromFile("C:\\Users\\Public\\product.xlsx");
        if (sheet == null) return new Response(false, "File not found.");

        for (int row = 1; row <= 500; row++) {
            String rawCategory = sheet.getRow(row).getCell(12) + "";
            String[] categories = rawCategory.split("\\&");
            for (String eachCategory : categories) {
                Category existingCategory = categoryRepository.findByNameAndEntityStatus(eachCategory.trim(), EntityStatus.ACTIVE);
                if (existingCategory != null) continue;

                Category category = new Category(eachCategory.trim());
                categoryService.save(category);
            }
        }
        return new Response(true, "Success");
    }

    private List<Review> getReviewList(Row row) {
        List<Review> reviewList = new ArrayList<Review>();
        String[] reviewRaws = row.getCell(14).toString().split("\\|");
        for (String reviewRaw : reviewRaws) {
            if (reviewRaw.length() < 1) continue;
            Review review = new Review(reviewRaw);
            reviewService.save(review);
            reviewList.add(review);
        }
        System.out.println("review   ....." + reviewList.size());
        return reviewList;
    }

    private List<Category> getCategoryList(Row row, Product product) {
        List<Category> categoryList = new ArrayList<>();
        String rawCategory = row.getCell(12) + "";
        String[] categories = rawCategory.split("\\&");
        for (String eachCategory : categories) {
            Category category = categoryRepository.findByNameAndEntityStatus(eachCategory.trim(), EntityStatus.ACTIVE);
            if (category != null) {
                category.getProductList().add(product);
                categoryList.add(category);
            }
        }
        System.out.println("category  ....." + categoryList.size());
        return categoryList;
    }

    public Response importProduct() {
        XSSFSheet sheet = getSheetFromFile("C:\\Users\\Public\\product.xlsx");
        if (sheet == null) return new Response(false, "File not found.");

        int rowCount = 0;
        sheet.removeRow(sheet.getRow(0));
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Product product = new Product(row);
            HashCode hashCode = hashCodeRepository.findByHashcodeNoAndEntityStatus(row.getCell(13) + "", EntityStatus.ACTIVE);
            if (hashCode != null) product.setHashcode(hashCode);

            product.getCategoryList().addAll(getCategoryList(row, product));
            product.getReviewList().addAll(getReviewList(row));
            productService.save(product);
            rowCount++;
            System.out.println("Count: ................" + rowCount + "........................");
        }
        return new Response(true, "Success");
    }
}

package com.example.intermove.Entities.CandidatesAndCourses;
import java.io.IOException;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class PdfToTextConverter {

    public String convertPdfToText(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        return text;
    }

}

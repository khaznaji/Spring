package com.example.intermove.Controllers.Candidacy;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.intermove.Entities.CandidatesAndCourses.Candidacy;
import com.example.intermove.Entities.CandidatesAndCourses.PdfToTextConverter;
import com.example.intermove.Services.Candidacy.CandidacyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.annotation.MultipartConfig;

@RestController
@MultipartConfig
public class PdfToTextController {

    @Autowired
    CandidacyServices candidacyServices;
    @PostMapping("/convert")
    public double convertPdfToText(@RequestParam("file") MultipartFile file,@RequestParam("idc") int idc) throws IOException {
        Candidacy candidacy=candidacyServices.getCandidatureById(idc);


        PdfToTextConverter converter = new PdfToTextConverter();
        File pdfFile = convertMultipartFileToFile(file);
        String text = converter.convertPdfToText(pdfFile);
        pdfFile.delete();

        String[] keywords= candidacyServices.getOfferT(idc).toArray(new String[0]);


        return (double) countMatchingUniqueKeywords(text , keywords) / keywords.length *100;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }

    public int countMatchingUniqueKeywords(String text, String[] keywords) {
        Set<String> uniqueKeywords = new HashSet<>(Arrays.asList(keywords));
        int count = 0;
        for (String word : text.split("\\s+")) {
            if (uniqueKeywords.contains(word)) {
                uniqueKeywords.remove(word);
                count++;
                if (uniqueKeywords.isEmpty()) {
                    break;
                }
            }
        }
        return count;
    }
}

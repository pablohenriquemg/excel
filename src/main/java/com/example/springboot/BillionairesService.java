package com.example.springboot;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class BillionairesService {

    private final BillionairesRepository repository;

    BillionairesService(BillionairesRepository repository) {
        this.repository = repository;
    }

    public ByteArrayInputStream load() {
        List<Billionaires> billionairesList = repository.findAll();
        ByteArrayInputStream in = billinairesToCsv(billionairesList);
        return in;
    }

    public static ByteArrayInputStream billinairesToCsv(List<Billionaires> billionairesList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Billionaires billionary : billionairesList) {
                List<String> data = Arrays.asList(
                        String.valueOf(billionary.getId()),
                        billionary.getFirst_name(),
                        billionary.getLast_name(),
                        billionary.getCareer()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}

package com.medicine.application;


import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//public class CsvUtils {
//    private static final CsvMapper mapper = new CsvMapper();
//    public static <T, CsvSchema> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
//        CsvSchema schema = (CsvSchema) mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
//        ObjectReader reader = mapper.readerFor(clazz).with((Base64Variant) schema);
//        return reader.<T>readValues(stream).readAll();
//    }
//}

public class CsvUtils {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "c_name","c_batch_no","d_expiry_date","n_balance_qty","c_packaging","c_unique_code","c_schemes","n_mrp","c_manufacturer","hsn_code"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Medicine> csvToMedicines(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Medicine> medicines = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                for(String name : HEADERs)
                {
                    System.out.print(csvRecord.get(name) + " ");
                }
                System.out.println();
                Medicine medicine = new Medicine(
                        csvRecord.get(HEADERs[0]),
                        csvRecord.get(HEADERs[1]),
                        csvRecord.get(HEADERs[2]),
                        Integer.parseInt(csvRecord.get(HEADERs[3])),
                        csvRecord.get(HEADERs[4]),
                        Integer.parseInt(csvRecord.get(HEADERs[5])),
                        csvRecord.get(HEADERs[6]),
                        csvRecord.get(HEADERs[7]),
                        csvRecord.get(HEADERs[8]),
                        Integer.parseInt(csvRecord.get(HEADERs[9]))
                        );

                medicines.add(medicine);
            }

            return medicines;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}

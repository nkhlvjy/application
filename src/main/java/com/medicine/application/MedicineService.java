package com.medicine.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    private Object CSVHelper;

    public List<Medicine> listAllMedicines(){
        return medicineRepository.findAll();
    }

    public void saveMedicine(Medicine medicine){
        medicineRepository.save(medicine);
    }

    public void deleteMedicine(String id){
        medicineRepository.deleteById(id);
    }

    public Medicine getMedicine(String id){
        return medicineRepository.findById(id).get();
    }

//    public void saveAll(Iterable<Medicine> medicines){
//        medicineRepository.saveAll(medicines);
//    }

    public void saveAll(MultipartFile file) {
        try {
            List<Medicine> medicines = CsvUtils.csvToMedicines(file.getInputStream());
            medicineRepository.saveAll(medicines);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}

package com.medicine.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderSpecRepository orderSpecRepository;

//    @PostMapping(value = "/uploadCSV", consumes = "text/csv")
//    public void uploadCSV(@RequestBody InputStream body) throws IOException {
//        medicineService.saveAll(CsvUtils.read(Medicine.class, body));
//    }
//
//    @PostMapping(value = "/uploadCSV", consumes = "multipart/form-data")
//    public void uploadCSV1(@RequestParam("file") MultipartFile file) throws IOException {
//        medicineService.saveAll(CsvUtils.read(Medicine.class, file.getInputStream()));
//    }

    @PostMapping("/uploadCSV")
    public void uploadFile(@RequestParam("file") MultipartFile file) {

        if (CsvUtils.hasCSVFormat(file)) {
            medicineService.saveAll(file);
        }

//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/searchMedicine")
    public List<String> searchMedicine(@RequestParam("c_name") String name) {
        List<Medicine> medicines = medicineService.listAllMedicines();
        List<String> retrieveNames = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getC_name().contains(name)) {
                retrieveNames.add(medicine.getC_name());
            }
        }
        return retrieveNames;
    }

    @GetMapping("/getMedicineDetails")
    public List<Medicine> getMedicineDetails(@RequestParam("c_unique_code") int code) {
        List<Medicine> medicines = medicineService.listAllMedicines();
        List<Medicine> retrieveMedicine = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getC_unique_code() == code) {
                retrieveMedicine.add(medicine);
            }
        }
        return retrieveMedicine;
    }

    @PostMapping("/placeOrder")
    public int placeOrder(@RequestBody List<OrderSpec> orderSpecs) {
        List<Medicine> medicines = medicineService.listAllMedicines();

        List<OrderSpec> specToBeSaved = new ArrayList<>();
        for(OrderSpec orderSpec:orderSpecs) {
            for (Medicine medicine : medicines) {
                if (orderSpec.getC_unique_id() == medicine.getC_unique_code() && orderSpec.getC_name().equals(medicine.getC_name())) {
                    if (orderSpec.getQuantity() <= medicine.getN_balance_qty()) {
                        medicine.setN_balance_qty(medicine.getN_balance_qty() - orderSpec.getQuantity());
                        medicineService.saveMedicine(medicine);
                        specToBeSaved.add(orderSpec);
                        break;
                    }
                }
            }
        }
        if(specToBeSaved.isEmpty()) {
            return -1;
        }

        Order order = new Order();
        orderRepository.save(order);

        specToBeSaved.forEach(orderSpec -> {orderSpec.setOrder(order.getOrder_id());});
        orderSpecRepository.saveAll(specToBeSaved);
        return order.getOrder_id();
    }
}

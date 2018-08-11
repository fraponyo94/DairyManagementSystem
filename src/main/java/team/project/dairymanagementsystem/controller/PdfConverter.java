/*package team.project.dairymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.service.ContractService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class PdfConverter {
    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {
        List<Contract> contracts = (List<Contract>) contractService.getAllContracts();

        ByteArrayInputStream bis = Generate
    }
}
*/

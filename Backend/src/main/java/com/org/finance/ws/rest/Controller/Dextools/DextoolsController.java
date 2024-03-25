package com.org.finance.ws.rest.Controller.Dextools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.finance.Model.main.DextoolsInfo;
import com.org.finance.Service.dextools.IDextoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/dextools")
public class DextoolsController {

    @Autowired
    IDextoolsService iDextoolsService;

    @GetMapping("/saveData/{params}")
    public ResponseEntity<Boolean> getData(
        @PathVariable("params") String params
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DextoolsInfo entity = mapper.readValue(params, DextoolsInfo.class);
        if (entity.getCreatedDate() != null) {
            iDextoolsService.isGreaterThanSpecificHour(entity.getCreatedDate(), 2);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

package com.org.finance.ws.rest.Controller.Dextools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/dextools")
public class DextoolsController {

    @Autowired
    IDextoolsService iDextoolsService;

    @GetMapping("/saveData/{params}")
    public void getData(
        @PathVariable("params") String params
    ) throws Exception {
        try {
            DextoolsInfo savedObject = iDextoolsService.save(new ObjectMapper().readValue(
                params,
                DextoolsInfo.class
            ));
            // TODO: Find Name In Another Json Key
            if (savedObject != null) {
                System.out.println("Save At : " + new Timestamp(System.currentTimeMillis()) + " => This Object" + params);
            } else {
                System.out.println("Object Isn't Valid.");
            }
        } catch (Exception e) {
            System.out.println("Exception Occur On : " + e.getMessage());
            // TODO: Handle This => (exception : JSONObject["holderAnalysis"] not found.)
        }
    }
}

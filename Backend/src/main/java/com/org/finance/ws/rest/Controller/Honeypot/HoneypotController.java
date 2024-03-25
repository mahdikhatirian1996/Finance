package com.org.finance.ws.rest.Controller.Honeypot;

import com.org.finance.Service.honeypot.IHoneypotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/honeypot")
public class HoneypotController {

    @Autowired
    IHoneypotService iHoneypotService;

    @GetMapping("/getData/{contractAddress}")
    public ResponseEntity<Boolean> getData(
        @PathVariable("contractAddress") String contractAddress
    ) throws IOException {
        iHoneypotService.mapJSONObjectOnHoneypotInfo(contractAddress);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

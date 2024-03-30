package com.org.finance.ws.rest.Controller.Honeypot;

import com.org.finance.Model.Main.HoneypotInfo;
import com.org.finance.Service.Honeypot.IHoneypotService;
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
    public ResponseEntity<HoneypotInfo> getData(
        @PathVariable("contractAddress") String contractAddress
    ) throws IOException {
        return new ResponseEntity<>(iHoneypotService.mapJSONObjectOnHoneypotInfo(contractAddress), HttpStatus.OK);
    }
}

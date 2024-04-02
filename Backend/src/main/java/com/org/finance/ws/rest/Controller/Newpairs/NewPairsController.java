package com.org.finance.ws.rest.Controller.Newpairs;

import com.org.finance.Dto.Newpairs.NewpairDto;
import com.org.finance.Service.Newpairs.INewPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/newpair")
public class NewPairsController {

    @Autowired
    private INewPairsService newPairsService;

    @GetMapping("/getAll")
    public ResponseEntity<List<NewpairDto>> getAll() {
        return new ResponseEntity<>(newPairsService.getAll(0, 10), HttpStatus.OK);
    }
}

package com.org.finance.ws.rest.Controller.Newpairs;

import com.org.finance.Dto.Newpairs.NewpairDto;
import com.org.finance.Service.Newpairs.INewPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/newpair")
public class NewPairsController {

    @Autowired
    private INewPairsService newPairsService;

    @GetMapping("/getAll")
    public ResponseEntity<HashMap<String, Object>> getAll(
            @RequestParam Integer currentPage, @RequestParam Integer pageSize
    ) {
        return new ResponseEntity<>(
                newPairsService.getAll(currentPage,pageSize),
                HttpStatus.OK
        );
    }

    @GetMapping("/getAll/withoutHoneypot")
    public ResponseEntity<HashMap<String, Object>> getAllWithoutHoneypot(
            @RequestParam Integer currentPage, @RequestParam Integer pageSize
    ) {
        return new ResponseEntity<>(
                newPairsService.getAllWithoutHoneypot(currentPage,pageSize),
                HttpStatus.OK
        );
    }
}

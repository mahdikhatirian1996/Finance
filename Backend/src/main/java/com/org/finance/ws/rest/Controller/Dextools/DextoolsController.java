package com.org.finance.ws.rest.Controller.Dextools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.finance.Dto.Dextools.DextoolsInfoDto;
import com.org.finance.Model.Enum.CurrencyType;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/dextools")
public class DextoolsController {

    @Autowired
    IDextoolsService iDextoolsService;

    @GetMapping("/saveData/{params}")
    public void saveData(
            @PathVariable("params") String params
    ) throws Exception {
        try {
            DextoolsInfo savedObject = iDextoolsService.save(
                    new ObjectMapper().readValue(
                            params,
                            DextoolsInfo.class
                    )
            );
            if (savedObject.getContractAddress() != null) {
                System.out.println("Save At : " + new Timestamp(System.currentTimeMillis()) + " => This Object" + params);
            } else {
                System.out.println("Object Isn't Valid Cause : " + savedObject.getErrorType().getEnglishTitle());
            }
        } catch (Exception e) {
            System.out.println("Exception Occur On : " + e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/saveData/solana")
    public void saveSolanaData(@RequestBody DextoolsInfoDto dto) {
        try {
            // Exception Occur On : Cannot invoke "java.sql.Timestamp.getDate()" because "objectTs" is null
            DextoolsInfo savedObject = iDextoolsService.saveWithoutHoneypot(
                    iDextoolsService.mapDtoToEntity(dto, CurrencyType.SOLANA)
            );
            if (savedObject.getContractAddress() != null) {
                System.out.println("Save At : " + new Timestamp(System.currentTimeMillis()));
            } else {
                System.out.println("Object Isn't Valid Cause : " + savedObject.getErrorType().getEnglishTitle());
            }
        } catch (Exception e) {
            System.out.println("Exception Occur On : " + e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/saveData/base")
    public void saveBaseData(@RequestBody DextoolsInfoDto dto) {
        try {
            DextoolsInfo savedObject = iDextoolsService.saveWithoutHoneypot(
                    iDextoolsService.mapDtoToEntity(dto, CurrencyType.BASE)
            );
            if (savedObject.getContractAddress() != null) {
                System.out.println("Save At : " + new Timestamp(System.currentTimeMillis()));
            } else {
                System.out.println("Object Isn't Valid Cause : " + savedObject.getErrorType().getEnglishTitle());
            }
        } catch (Exception e) {
            System.out.println("Exception Occur On : " + e.getMessage());
        }
    }

}

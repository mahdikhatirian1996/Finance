package com.org.finance.Service.honeypot;

import com.org.finance.Model.main.HoneypotInfo;
import org.json.JSONObject;

import java.io.IOException;

public interface IHoneypotService {
    HoneypotInfo findByContractAddress(String contractAddress);
    JSONObject getDextoolsValidationInformationFromHoneypot(String contractAddress) throws IOException;
    String convertPureObject(JSONObject pureObject);
    HoneypotInfo mapJSONObjectOnHoneypotInfo(String contractAddress) throws IOException;
}

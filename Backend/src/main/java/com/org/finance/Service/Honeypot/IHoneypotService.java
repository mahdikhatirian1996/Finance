package com.org.finance.Service.Honeypot;

import com.org.finance.Model.Main.HoneypotInfo;
import org.json.JSONObject;

import java.io.IOException;

public interface IHoneypotService {
    HoneypotInfo findByContractAddress(String contractAddress);
    JSONObject getDextoolsValidationInformationFromHoneypot(String contractAddress) throws IOException;
    String convertPureObject(JSONObject pureObject);
    HoneypotInfo mapJSONObjectOnHoneypotInfo(String contractAddress) throws IOException;
    HoneypotInfo save(String contractAddress) throws IOException;
}

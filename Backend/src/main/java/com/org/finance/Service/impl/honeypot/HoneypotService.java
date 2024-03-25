package com.org.finance.Service.impl.honeypot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.finance.Dao.honeypot.IHoneypotRepository;
import com.org.finance.Model.main.HoneypotInfo;
import com.org.finance.Service.honeypot.IHoneypotService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HoneypotService implements IHoneypotService {
    private static final String HONEYPOT_GET = "https://api.honeypot.is/v2/IsHoneypot";

    @Autowired
    private IHoneypotRepository iHoneypotRepository;

    @Override
    public HoneypotInfo findByContractAddress(String contractAddress) {
        return iHoneypotRepository.findByContractAddress(contractAddress);
    }

    @Override
    public JSONObject getDextoolsValidationInformationFromHoneypot(String contractAddress) throws IOException {
        JSONObject outputJson = null;
        URL url = new URL(HONEYPOT_GET + "?address=" + contractAddress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader((connection.getInputStream()))
            );
            try {
                outputJson = new JSONObject(reader.readLine());
                connection.disconnect();
            } catch (Exception err) {
                connection.disconnect();
                return outputJson;
            }
        }
        return outputJson;
    }

    @Override
    public String convertPureObject(JSONObject pureObject) {
        StringBuilder params = new StringBuilder(" {") ;
        JSONObject chainJson = pureObject.getJSONObject("chain");
        JSONObject tokenJson = pureObject.getJSONObject("token");
        JSONObject contractCodeJson = pureObject.getJSONObject("contractCode");
        JSONObject honeypotResultJson = pureObject.getJSONObject("honeypotResult");
        JSONObject holderAnalysisJson = pureObject.getJSONObject("holderAnalysis");
        JSONObject simulationResultJson = pureObject.getJSONObject("simulationResult");
        JSONObject pairJson = pureObject.getJSONObject("pair");
        params.append("'currencyType'").append(":'").append(chainJson.getString("currency")).append("', ");
        params.append("'contractAddress'").append(":'").append(tokenJson.getString("address")).append("', ");
        params.append("isOpensource").append(":").append(contractCodeJson.getBoolean("openSource")).append(", ");
        params.append("isHoneypot").append(":").append(honeypotResultJson.getBoolean("isHoneypot")).append(", ");
        params.append("'createdDate'").append(":'").append(pairJson.getString("createdAtTimestamp") + "000").append("', ");
        params.append("'liquidity'").append(":'").append(pairJson.getDouble("liquidity")).append("', ");
        params.append("'averageGas'").append(":'").append(holderAnalysisJson.getDouble("averageGas")).append("', ");
        params.append("'averageTax'").append(":'").append(holderAnalysisJson.getDouble("averageTax")).append("', ");
        params.append("'holders'").append(":'").append(holderAnalysisJson.getString("holders")).append("', ");
        params.append("'sellGas'").append(":'").append(simulationResultJson.getString("sellGas")).append("', ");
        params.append("'buyGas'").append(":'").append(simulationResultJson.getString("buyGas")).append("', ");
        params.append("'sellTax'").append(":'").append(simulationResultJson.getDouble("sellTax")).append("', ");
        params.append("'buyTax'").append(":'").append(simulationResultJson.getDouble("buyTax")).append("', ");
        params.append("'transferTax'").append(":'").append(simulationResultJson.getDouble("transferTax")).append("'} ");
        return params.toString();
    }

    @Override
    public HoneypotInfo mapJSONObjectOnHoneypotInfo(String contractAddress) throws IOException {
        JSONObject pureObject = getDextoolsValidationInformationFromHoneypot(contractAddress);
        String originObject = convertPureObject(pureObject);
        return new ObjectMapper().readValue(originObject, HoneypotInfo.class);
    }
}

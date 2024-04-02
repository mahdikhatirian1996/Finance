package com.org.finance.Service.Impl.Honeypot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.finance.Dao.Honeypot.IHoneypotRepository;
import com.org.finance.Model.Enum.CurrencyType;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;
import com.org.finance.Service.Honeypot.IHoneypotService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    public String convertPureObject(JSONObject pureObject, String contractAddrress) {
        StringBuilder params = new StringBuilder(" {") ;
        Boolean haveHolderAnalysisJson = pureObject.getJSONObject("holderAnalysis").isEmpty();

        JSONObject pairJson = pureObject.getJSONObject("pair");
        JSONObject chainJson = pureObject.getJSONObject("chain");
        JSONObject tokenJson = pureObject.getJSONObject("token");
        JSONObject contractCodeJson = pureObject.getJSONObject("contractCode");
        JSONObject honeypotResultJson = pureObject.getJSONObject("honeypotResult");
        JSONObject simulationResultJson = pureObject.getJSONObject("simulationResult");
        String currencyTypeName = CurrencyType.getEnumNameFromTitle(chainJson.getString("currency"));


        params.append("\"currencyType\"").append(":\"").append(currencyTypeName).append("\", ")
        .append("\"name\"").append(":\"").append(tokenJson.getString("name")).append("\", ")
        .append("\"contractAddress\"").append(":\"").append(contractAddrress).append("\", ")
        .append("\"isOpensource\"").append(":\"").append(contractCodeJson.getBoolean("openSource")).append("\", ")
        .append("\"isHoneypot\"").append(":\"").append(honeypotResultJson.getBoolean("isHoneypot")).append("\", ")
        .append("\"liquidity\"").append(":\"").append(pairJson.getDouble("liquidity")).append("\", ")
        .append("\"createdDate\"").append(":\"").append(pairJson.getString("createdAtTimestamp")).append("000").append("\", ")
        .append("\"buyGas\"").append(":\"").append(simulationResultJson.getString("buyGas")).append("\", ")
        .append("\"buyTax\"").append(":\"").append(simulationResultJson.getDouble("buyTax")).append("\", ")
        .append("\"sellGas\"").append(":\"").append(simulationResultJson.getString("sellGas")).append("\", ")
        .append("\"sellTax\"").append(":\"").append(simulationResultJson.getDouble("sellTax")).append("\", ")
        .append("\"transferTax\"").append(":\"").append(simulationResultJson.getDouble("transferTax")).append("\"");

        if (!haveHolderAnalysisJson) {
            JSONObject holderAnalysisJson = pureObject.getJSONObject("holderAnalysis");
            params.append(", \"holders\"").append(":\"").append(holderAnalysisJson.getString("holders")).append("\", ")
            .append("\"averageGas\"").append(":\"").append(holderAnalysisJson.getDouble("averageGas")).append("\", ")
            .append("\"averageTax\"").append(":\"").append(holderAnalysisJson.getDouble("averageTax")).append("\"} ");
        } else {
            params.append("}");
        }

        return params.toString();
    }

    @Override
    public HoneypotInfo mapJSONObjectOnHoneypotInfo(String contractAddress) throws IOException {
        JSONObject pureObject = getDextoolsValidationInformationFromHoneypot(contractAddress);
        String originObject = convertPureObject(pureObject, contractAddress);
        return new ObjectMapper().readValue(originObject, HoneypotInfo.class);
    }

    @Override
    public HoneypotInfo save(String contractAddress) throws IOException {
        HoneypotInfo entity = iHoneypotRepository.save(mapJSONObjectOnHoneypotInfo(contractAddress));
        return iHoneypotRepository.save(entity);
    }

    @Override
    public List<HoneypotInfo> getListByDextoolsContractAddress(List<DextoolsInfo> dextoolsInfos) {
            List<HoneypotInfo> honeypotInfos = new ArrayList<>();
            for (DextoolsInfo object : dextoolsInfos) {
                honeypotInfos.add(iHoneypotRepository.findByContractAddress(object.getContractAddress()));
            }
            return honeypotInfos;
    }

}

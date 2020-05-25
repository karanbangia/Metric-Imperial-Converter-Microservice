package com.RestAPI.MetricConverter.Controller;

import com.RestAPI.MetricConverter.Model.MetricResponseModel;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import org.decimal4j.util.DoubleRounder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/api/convert")
public class HomeController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getResponse(@RequestParam(value = "input", defaultValue = "1") String input) throws JSONException {
        if (input.length() == 1 && input.charAt(0) == '1') {
            JSONObject entity = new JSONObject();
            entity.put("error", "invalid number and unit");
            return new ResponseEntity<Object>(entity.toString(), HttpStatus.OK);
        }
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.toString(input.charAt(i)).matches("^[a-zA-Z]*$")) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            JSONObject entity = new JSONObject();
            entity.put("error", "no unit");
            return new ResponseEntity<Object>(entity.toString(), HttpStatus.OK);
        }
        String inputNumStr = input.substring(0, index);
        Expression e = new ExpressionBuilder(inputNumStr).build();
        ValidationResult res = e.validate();
        String inputUnit = input.substring(index).toLowerCase();
        boolean isNumber = checkNumber(res);
        boolean isUnit = checkUnit(inputUnit);
        if (isNumber == false && isUnit == false) {
            JSONObject entity = new JSONObject();
            entity.put("error", "invalid number and unit");
            return new ResponseEntity<Object>(entity.toString(), HttpStatus.OK);
        }
        if (isNumber == false) {
            JSONObject entity = new JSONObject();
            entity.put("error", "invalid number");
            return new ResponseEntity<Object>(entity.toString(), HttpStatus.OK);
        }
        if (isUnit == false) {
            JSONObject entity = new JSONObject();
            entity.put("error", "invalid unit");
            return new ResponseEntity<Object>(entity.toString(), HttpStatus.OK);
        }
        double inputNum = e.evaluate();
        double outputNum = 0;
        String outputUnit = "";
        String string = "";
        switch (inputUnit) {
            case "gal":
                outputNum = inputNum * 3.78541;
                outputUnit = "l";
                break;
            case "lbs":
                outputNum = inputNum * 0.453592;
                outputUnit = "kg";
                break;
            case "mi":
                outputNum = inputNum * 1.60934;
                outputUnit = "km";
                break;
            case "l":
                outputNum = inputNum * 0.26417;
                outputUnit = "gal";
                break;
            case "kg":
                outputNum = inputNum * 2.20462;
                outputUnit = "lbs";
                break;
            case "km":
                outputNum = inputNum * 0.62137;
                outputUnit = "mi";
                break;
        }
        outputNum = DoubleRounder.round(outputNum, 5);
        string = inputNum + " " + inputUnit + " converts to " + outputNum + " " + outputUnit;
        MetricResponseModel metricResponseModel = new MetricResponseModel();
        metricResponseModel.setInitNum(inputNum);
        metricResponseModel.setInitUnit(inputUnit);
        metricResponseModel.setReturnNum(outputNum);
        metricResponseModel.setReturnUnit(outputUnit);
        metricResponseModel.setString(string);
        return new ResponseEntity<>(metricResponseModel, HttpStatus.CREATED);
    }

    private boolean checkUnit(String inputUnit) {
        HashSet<String> hs = new HashSet<>();
        hs.add("kg");
        hs.add("gal");
        hs.add("l");
        hs.add("km");
        hs.add("lbs");
        hs.add("mi");
        return hs.contains(inputUnit);
    }

    private boolean checkNumber(ValidationResult res) {
        if (!res.isValid()) return false;
        return true;
    }

}

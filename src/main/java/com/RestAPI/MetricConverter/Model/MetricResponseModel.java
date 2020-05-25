package com.RestAPI.MetricConverter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"initNum", "initUnit", "returnNum", "returnUnit", "string"})
public class MetricResponseModel {
    @JsonProperty("initNum")
    private double initNum;
    @JsonProperty("initUnit")
    private String initUnit;
    @JsonProperty("returnNum")
    private double returnNum;
    @JsonProperty("returnUnit")
    private String returnUnit;
    @JsonProperty("string")
    private String string;

    public double getInitNum() {
        return initNum;
    }

    public void setInitNum(double initNum) {
        this.initNum = initNum;
    }

    public String getInitUnit() {
        return initUnit;
    }

    public void setInitUnit(String initUnit) {
        this.initUnit = initUnit;
    }

    public double getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(double returnNum) {
        this.returnNum = returnNum;
    }

    public String getReturnUnit() {
        return returnUnit;
    }

    public void setReturnUnit(String returnUnit) {
        this.returnUnit = returnUnit;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}

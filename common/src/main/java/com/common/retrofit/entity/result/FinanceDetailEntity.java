package com.common.retrofit.entity.result;

/**
 *
 * 财务管理详情
 * Created by little_bug on 2018/8/31.
 */
public class FinanceDetailEntity {
    private String money;
    private String grade;
    private String xindou;

    private String proportion;
    private String appreciation;
    private String ratio;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getXindou() {
        return xindou;
    }

    public void setXindou(String xindou) {
        this.xindou = xindou;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "FinanceDetailEntity{" +
                "money='" + money + '\'' +
                ", grade='" + grade + '\'' +
                ", xindou='" + xindou + '\'' +
                ", proportion='" + proportion + '\'' +
                ", appreciation='" + appreciation + '\'' +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}

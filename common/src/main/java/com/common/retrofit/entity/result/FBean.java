package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/6/11.
 */
public class FBean {
//    private String money;
//    private String time;
//    private String xindou;
//    private String proportion;
//    private String appreciation;
//    private String ratio;
//    private String week;


    private String xindou;
    private String grade;
    private String proportion;
    private String appreciation;
    private String ratio;
    private String money;
    private String week;



    public void setXindou(String xindou) {
        this.xindou = xindou;
    }

    public String getXindou() {
        return xindou;
    }


    public String getMoney() {

        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * 让利比例
     *
     * @return
     */
    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    /**
     * 升值比例
     *
     * @return
     */
    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getRatio() {
        return ratio;
    }

    /**
     * 换算比
     *
     * @param ratio
     */
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "FBean{" +
                "xindou='" + xindou + '\'' +
                ", grade='" + grade + '\'' +
                ", proportion='" + proportion + '\'' +
                ", appreciation='" + appreciation + '\'' +
                ", ratio='" + ratio + '\'' +
                ", money='" + money + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}

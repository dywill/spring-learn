package com.dy.bean_field;

import java.util.*;

public class MyBean {

    private byte b;

    private short sh;

    private int i;

    private long longnum;

    private float flo;

    private double dou;

    private boolean bool;

    private char ch;


    private String str;

    private Person person;

    private String[] strs;

    private List<String> list;

    private Set<String> set;

    private Map<String,Person> map;

    private Properties prop;

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public short getSh() {
        return sh;
    }

    public void setSh(short sh) {
        this.sh = sh;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getLongnum() {
        return longnum;
    }

    public void setLongnum(long longnum) {
        this.longnum = longnum;
    }

    public float getFlo() {
        return flo;
    }

    public void setFlo(float flo) {
        this.flo = flo;
    }

    public double getDou() {
        return dou;
    }

    public void setDou(double dou) {
        this.dou = dou;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Person> getMap() {
        return map;
    }

    public void setMap(Map<String, Person> map) {
        this.map = map;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "b=" + b +
                ", sh=" + sh +
                ", i=" + i +
                ", longnum=" + longnum +
                ", flo=" + flo +
                ", dou=" + dou +
                ", bool=" + bool +
                ", ch=" + ch +
                ", str='" + str + '\'' +
                ", person=" + person +
                ", strs=" + Arrays.toString(strs) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", prop=" + prop +
                '}';
    }
}

package com.common.event;

/**
 * Created by little_bug on 2018/10/10.
 */
public class BaseEvent {
    protected int tag;
    protected String s;
    protected int i;
    protected float f;
    protected boolean b;
    protected Object o;

    public BaseEvent(int tag) {
        this.tag = tag;
    }

    public BaseEvent(int tag, Object o) {
        this.tag = tag;
        this.o = o;
    }

    public BaseEvent(int tag, boolean b) {
        this.tag = tag;
        this.b = b;
    }

    public BaseEvent(int tag, float f) {
        this.tag = tag;
        this.f = f;
    }

    public BaseEvent(int tag, int i) {
        this.tag = tag;
        this.i = i;
    }

    public BaseEvent(int tag, String s) {
        this.tag = tag;
        this.s = s;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
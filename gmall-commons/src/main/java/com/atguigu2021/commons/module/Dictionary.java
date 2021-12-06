package com.atguigu2021.commons.module;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 16:43
 */
public class Dictionary {
    private String label;
    private Object value;

    public Dictionary() {
    }

    public Dictionary(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

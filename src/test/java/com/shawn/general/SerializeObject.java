package com.shawn.general;

import com.google.common.base.Objects;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;

/**
 * @author Shawn Cao
 */
public class SerializeObject implements Serializable {
    private static final long serialVersionUID = -3565931004808582297L;
    private List<String> list;
    transient private String str;
    private boolean is;

    public SerializeObject(List<String> list, String str, boolean is) {
        this.list = list;
        this.str = str;
        this.is = is;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean is() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("list", list)
                .add("str", str)
                .add("is", is)
                .toString();
    }
}
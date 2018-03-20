package com.lifeStudy.algorith;

public class ArrarTypes {
    private int max_left;
    private int max_right;
    private int max_sum;

    @Override
    public String toString() {
        return "ArrarTypes:  max_left=" + max_left + "  max_right=" + max_right + "   max_sum=" + max_sum;
    }

    public ArrarTypes(int max_left, int max_right, int max_sum) {
        this.max_left = max_left;
        this.max_right = max_right;
        this.max_sum = max_sum;
    }

    public void setMax_left(int max_left) {
        this.max_left = max_left;
    }

    public void setMax_right(int max_right) {
        this.max_right = max_right;
    }

    public void setMax_sum(int max_sum) {
        this.max_sum = max_sum;
    }

    public int getMax_left() {
        return max_left;
    }

    public int getMax_right() {
        return max_right;
    }

    public int getMax_sum() {
        return max_sum;
    }
}

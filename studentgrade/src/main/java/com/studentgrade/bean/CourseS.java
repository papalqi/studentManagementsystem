package com.studentgrade.bean;

import java.math.BigDecimal;

public class CourseS extends CourseSKey {
    private BigDecimal iallscore;

    private BigDecimal iscore2;

    private BigDecimal iscore1;

    public BigDecimal getIallscore() {
        return iallscore;
    }

    public void setIallscore(BigDecimal iallscore) {
        this.iallscore = iallscore;
    }

    public BigDecimal getIscore2() {
        return iscore2;
    }

    public void setIscore2(BigDecimal iscore2) {
        this.iscore2 = iscore2;
    }

    public BigDecimal getIscore1() {
        return iscore1;
    }

    public void setIscore1(BigDecimal iscore1) {
        this.iscore1 = iscore1;
    }
}
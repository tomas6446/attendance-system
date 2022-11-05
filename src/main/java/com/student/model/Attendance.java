package com.student.model;

import java.time.LocalDate;

/**
 * @author Tomas Kozakas
 */
public class Attendance {
    private LocalDate date;
    private boolean present;
    private String subject;

    public Attendance(LocalDate date, boolean present, String subject) {
        this.date = date;
        this.present = present;
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "date='" + date + '\'' +
                ", present='" + present + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

package com.touch.fish.entity.bookSetting;

import javax.swing.*;

public class Ini4jFileVo {

    private String filePath;
    private String bookName;
    private  String currentPage;

    @Override
    public String toString() {
        return "Ini4jFileVo{" +
                "filePath='" + filePath + '\'' +
                ", bookName='" + bookName + '\'' +
                ", currentPage='" + currentPage + '\'' +
                '}';
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

package com.example.demo.xmlParser.saxReflect;

public class Book {
    private String id;
    private String bookcategory;
    private String amount;
    private String remain;
    private String discount;
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private String price;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBookcategory() {
        return bookcategory;
    }
    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getRemain() {
        return remain;
    }
    public void setRemain(String remain) {
        this.remain = remain;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Book [id=" + id + ", bookcategory=" + bookcategory + ", amount=" + amount + ", remain=" + remain
                + ", discount=" + discount + ", title=" + title + ", author=" + author + ", publisher=" + publisher
                + ", ISBN=" + ISBN + ", price=" + price + "]";
    }
}

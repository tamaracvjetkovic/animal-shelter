package dtos;

public class PostDTO {
    private String picture;
    private String name;
    private String breed;
    private String color;
    private String date;
    private String status;

    public PostDTO(String picture, String name, String breed, String color, String date, String status) {
        this.picture = picture;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.date = date;
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

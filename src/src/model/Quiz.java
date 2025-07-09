package model;

public class Quiz {
    private String id;
    private String title;
    private String description;
    private String creatorId;
    private boolean isRandomOrdered;
    private boolean isOnePage;
    private boolean isImmediateCorrection;
    private boolean isPractice;

    public Quiz(String id, String title, String description, String creatorId, boolean isRandomOrdered, boolean isOnePage, boolean isImmediateCorrection, boolean isPractice) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.isRandomOrdered = isRandomOrdered;
        this.isOnePage = isOnePage;
        this.isImmediateCorrection = isImmediateCorrection;
        this.isPractice = isPractice;
    }

    public Quiz(String title, String description, String creatorId, boolean isRandomOrdered, boolean isOnePage, boolean isImmediateCorrection, boolean isPractice) {
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.isRandomOrdered = isRandomOrdered;
        this.isOnePage = isOnePage;
        this.isImmediateCorrection = isImmediateCorrection;
        this.isPractice = isPractice;
    }

    public Quiz() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isRandomOrdered() {
        return isRandomOrdered;
    }

    public void setRandomOrdered(boolean randomOrdered) {
        isRandomOrdered = randomOrdered;
    }

    public boolean isOnePage() {
        return isOnePage;
    }

    public void setOnePage(boolean onePage) {
        isOnePage = onePage;
    }

    public boolean isImmediateCorrection() {
        return isImmediateCorrection;
    }

    public void setImmediateCorrection(boolean immediateCorrection) {
        isImmediateCorrection = immediateCorrection;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", isRandomOrdered=" + isRandomOrdered +
                ", isOnePage=" + isOnePage +
                ", isImmediateCorrection=" + isImmediateCorrection +
                ", isPractice=" + isPractice +
                '}';
    }
}

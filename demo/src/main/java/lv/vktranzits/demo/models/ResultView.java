package lv.vktranzits.demo.models;

public class ResultView {

    public int isFullNameEnabled;

    public int isTitleEnabled;
    
    public int isRatingEnabled;

    public int isDateEnabled;

    public int getIsFullNameEnabled() {
        return isFullNameEnabled;
    }

    public void setIsFullNameEnabled(int isFullNameEnabled) {
        this.isFullNameEnabled = isFullNameEnabled;
    }

    public int getIsTitleEnabled() {
        return isTitleEnabled;
    }

    public void setIsTitleEnabled(int isTitleEnabled) {
        this.isTitleEnabled = isTitleEnabled;
    }

    public int getIsRatingEnabled() {
        return isRatingEnabled;
    }

    public void setIsRatingEnabled(int isRatingEnabled) {
        this.isRatingEnabled = isRatingEnabled;
    }

    public int getIsDateEnabled() {
        return isDateEnabled;
    }

    public void setIsDateEnabled(int isDateEnabled) {
        this.isDateEnabled = isDateEnabled;
    }

    @Override
    public String toString() {
        return "ResultView [isFullNameEnabled=" + isFullNameEnabled + ", isTitleEnabled=" + isTitleEnabled
                + ", isRatingEnabled=" + isRatingEnabled + ", isDateEnabled=" + isDateEnabled + "]";
    }

    
}

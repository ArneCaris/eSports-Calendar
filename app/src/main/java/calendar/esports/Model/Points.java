package calendar.esports.Model;

public class Points {

    public String achievementValue = "0";

    public void setAchievementValue(String achievementValue) {
        this.achievementValue = achievementValue ;
    }

    public String getAchievementValue() {
        return achievementValue;
    }

    public void incrementPoints() {
        achievementValue.valueOf(Integer.parseInt(achievementValue)+1);
    }

}

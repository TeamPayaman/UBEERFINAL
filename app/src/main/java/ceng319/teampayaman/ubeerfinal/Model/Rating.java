package ceng319.teampayaman.ubeerfinal.Model;

  public class Rating {
      private String userPhone;
      private String drinkId;
      private String rateValue;
      private String comment;

      public Rating(String userPhone, String drinkId, String rateValue, String comment) {
          this.userPhone = userPhone;
          this.drinkId = drinkId;
          this.rateValue = rateValue;
          this.comment = comment;
      }

      public String getUserPhone() {
          return userPhone;
      }
      public void setUserPhone(String userPhone) {
          this.userPhone = userPhone;
      }
      public String getDrinkId() {
          return drinkId;
      }
      public void setDrinkId(String drinkId) {
          this.drinkId = drinkId;
      }
      public String getRateValue() {
          return rateValue;
      }
      public void setRateValue(String rateValue) {
          this.rateValue = rateValue;
      }
      public String getComment() {
          return comment;
      }
      public void setComment(String comment) {
          this.comment = comment;
      }
  }
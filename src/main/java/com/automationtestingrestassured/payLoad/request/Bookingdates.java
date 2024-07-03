
  package com.automationtestingrestassured.payLoad.request;
  
  import java.util.LinkedHashMap; import java.util.Map; import
  com.fasterxml.jackson.annotation.JsonAnyGetter; import
  com.fasterxml.jackson.annotation.JsonAnySetter; import
  com.fasterxml.jackson.annotation.JsonIgnore; import
  com.fasterxml.jackson.annotation.JsonInclude; import
  com.fasterxml.jackson.annotation.JsonProperty; import
  com.fasterxml.jackson.annotation.JsonPropertyOrder;
  
  @JsonInclude(JsonInclude.Include.NON_NULL)
  
  @JsonPropertyOrder({ "checkin", "checkout" })
  
  public class Bookingdates {
  
  @JsonProperty("checkin") private String checkin;
  
  @JsonProperty("checkout") private String checkout;
  
  
  @JsonProperty("checkin") public String getCheckin() { return checkin; }
  
  @JsonProperty("checkin") public void setCheckin(String checkin) {
  this.checkin = checkin; }
  
  @JsonProperty("checkout") public String getCheckout() { return checkout; }
  
  @JsonProperty("checkout") public void setCheckout(String checkout) {
  this.checkout = checkout; }
  
  
  }
 
package model;

public class Rooms {

    private String roomName, roomType, capacity, roomSize, servicesIncluded, reservationDate, checkoutDate;
    private double pricePerNight;
    private String[] extraServices;

    public Rooms(String roomName, String roomType, String capacity, String roomSize, String servicesIncluded, String reservationDate, String checkoutDate, double pricePerNight, boolean availability, String[] extraServices) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.capacity = capacity;
        this.roomSize = roomSize;
        this.servicesIncluded = servicesIncluded;
        this.reservationDate = reservationDate;
        this.checkoutDate = checkoutDate;
        this.pricePerNight = pricePerNight;
        this.extraServices = extraServices;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getServicesIncluded() {
        return servicesIncluded;
    }

    public void setServicesIncluded(String servicesIncluded) {
        this.servicesIncluded = servicesIncluded;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String[] getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(String[] extraServices) {
        this.extraServices = extraServices;
    }

}

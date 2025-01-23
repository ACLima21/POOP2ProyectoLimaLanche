package model;

public class Rooms {

    private String roomName, dateRangeReservations, extraServices, roomSize, roomType, servicesIncluded, capacity;
    private boolean availability;
    private double pricePerNight;

    // Constructor
    public Rooms(String roomName, boolean availability, String capacity, String dateRangeReservations,
            String extraServices, double pricePerNight, String roomSize, String roomType, String servicesIncluded) {
        this.roomName = roomName;
        this.availability = availability;
        this.capacity = capacity;
        this.dateRangeReservations = dateRangeReservations;
        this.extraServices = extraServices;
        this.pricePerNight = pricePerNight;
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.servicesIncluded = servicesIncluded;
    }

    // Getters and Setters
    public String getRoomName() {
        return roomName;
    }

    public boolean getAvailability() {
        return availability;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getDateRangeReservations() {
        return dateRangeReservations;
    }

    public String getExtraServices() {
        return extraServices;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getServicesIncluded() {
        return servicesIncluded;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setDateRangeReservations(String dateRangeReservations) {
        this.dateRangeReservations = dateRangeReservations;
    }

    public void setExtraServices(String extraServices) {
        this.extraServices = extraServices;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setServicesIncluded(String servicesIncluded) {
        this.servicesIncluded = servicesIncluded;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

}

package model;

public class Rooms {

    private String roomName, roomType, capacity, roomSize, servicesIncluded;
    private double pricePerNight;
    private boolean availability;
    private String[] extraServices;

    public Rooms(String roomName, String roomType, String capacity, String roomSize, String servicesIncluded, double pricePerNight, boolean availability, String[] extraServices) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.capacity = capacity;
        this.roomSize = roomSize;
        this.servicesIncluded = servicesIncluded;
        this.pricePerNight = pricePerNight;
        this.availability = availability;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String[] getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(String[] extraServices) {
        this.extraServices = extraServices;
    }

}

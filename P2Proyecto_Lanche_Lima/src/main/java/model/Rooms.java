package model;

public class Rooms {

    private String roomName, dateRangeReservations, extraServices, roomSize, roomType, servicesIncluded;
    private boolean availability;
    private int capacity;
    private double pricePerNight;

    // Constructor
    public Rooms(String roomName, boolean availability, int capacity, String dateRangeReservations,
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

    public boolean isAvailability() {
        return availability;
    }

    public int getCapacity() {
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
}

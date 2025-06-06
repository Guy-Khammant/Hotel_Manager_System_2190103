public abstract class Room{
    private double price;
    private String roomNumber;
    private boolean isBooked;
    private Customer customer;

    public Room(double price, String roomNumber) {
        this.price = price;
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }
    
    public abstract String getType();

    public void book() { 
        isBooked = true; 
    }

    public boolean isBooked() { 
        return isBooked; 
    }

    public double getPrice() { 
        return price; 
    }

    public String getRoomNumber() { 
        return roomNumber; 
    }

    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    }

    public Customer getCustomer() { 
        return customer; 
    }
}
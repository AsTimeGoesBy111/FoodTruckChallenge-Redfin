import java.util.Objects;

public class FoodTruck {
    private final String name;
    private final String location;

    /* equals method which checks on the name and location */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodTruck foodTruck = (FoodTruck) o;
        return Objects.equals(name, foodTruck.name) &&
                Objects.equals(location, foodTruck.location);
    }

    /* hashcode method */
    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    /* constructor that takes in name and location */
    public FoodTruck(String name, String location)
    {
        this.name=name;
        this.location=location;
    }

    /* getters for name and location */
    public String getName()
    {
        return name;
    }

    public String getLocation()
    {
        return location;
    }
}

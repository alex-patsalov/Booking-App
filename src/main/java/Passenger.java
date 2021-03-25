import java.io.Serializable;

public class Passenger implements Serializable {
   final String name;
   final String surname;

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

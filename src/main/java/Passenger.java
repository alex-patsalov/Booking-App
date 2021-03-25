import java.io.Serializable;

public class Passenger implements Serializable {
   final String name;
   final String surname;

    @Override
    public String toString() {
        return "Пассажир: " +
                "имя: " + name  +
                ", фамилия: " + surname + '\n';
    }

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

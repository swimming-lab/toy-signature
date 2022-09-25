package swm.toy.signature.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Phone {

    @Column(name = "phone", nullable = false, unique = true)
    private String number;

    public Phone(String number) {
        this.number = number.replaceAll("[^0-9]", "");
    }

    protected Phone() {}

    @Override
    public String toString() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return number.equals(phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

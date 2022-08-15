package swm.toy.signature.domain.user;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserName {

    @Column(name = "name", nullable = false)
    private String name;

    public UserName(String name) {
        this.name = name;
    }

    protected UserName() {}

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var userName = (UserName) o;
        return name.equals(userName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

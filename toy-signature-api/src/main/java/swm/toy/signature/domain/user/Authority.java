package swm.toy.signature.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "authority")
@Entity
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    static Authority of(String authorityName) {
        return new Authority(authorityName);
    }

    private Authority(String authorityName) {
        this.authorityName = authorityName;
    }

    protected Authority() {}

    public String getAuthorityName() {
        return authorityName;
    }
}

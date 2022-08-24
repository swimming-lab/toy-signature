package swm.toy.signature.domain.user;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import swm.toy.signature.domain.agreement.Agreement;
import swm.toy.signature.domain.agreement.AgreementContents;
import swm.toy.signature.domain.agreement.AgreementUpdateRequest;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.item.Item;
import swm.toy.signature.domain.item.ItemContents;
import swm.toy.signature.domain.item.ItemUpdateRequest;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private Email email;

    @Embedded private Profile profile;

    @Embedded private Password password;

    @Convert(converter = UserStatusConverter.class)
    private UserStatus status = UserStatus.USED;

    @JoinTable(
            name = "user_followings",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "id"))
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<User> followingUsers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "authority_name", referencedColumnName = "authority_name")
            })
    private Set<Authority> authorities = new HashSet<>();

    static User of(Email email, UserName name, Password password) {
        return new User(email, new Profile(name), password, null);
    }

    static User of(Email email, UserName name, Password password, Authority authority) {
        return new User(email, new Profile(name), password, authority);
    }

    private User(Email email, Profile profile, Password password, Authority authority) {
        this.email = email;
        this.profile = profile;
        this.password = password;
        if (authority != null) {
            this.authorities.add(authority);
        }
    }

    protected User() {}

    public Item createItem(ItemContents contents, ItemType itemType, ItemBrand itemBrand) {
        return Item.of(this, contents, itemType, itemBrand);
    }

    public Item updateItem(Item item, ItemUpdateRequest request) {
        if (item.getAuthor() != this) {
            throw new IllegalAccessError("Not authorized to update this item");
        }
        item.updateItem(request);
        return item;
    }

    public Agreement createAgreement(AgreementContents contents) {
        return Agreement.of(this, contents);
    }

    public Agreement updateAgreement(Agreement agreement, AgreementUpdateRequest request) {
        if (agreement.getAuthor() != this) {
            throw new IllegalAccessError("Not authorized to update this agreement");
        }
        agreement.updateAgreement(request);
        return agreement;
    }

    User followUser(User followee) {
        followingUsers.add(followee);
        return this;
    }

    User unfollowUser(User followee) {
        followingUsers.remove(followee);
        return this;
    }

    Profile viewProfile(User user) {
        return user.profile.withFollowing(followingUsers.contains(user));
    }

    public Profile getProfile() {
        return profile;
    }

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return password.matchesPassword(rawPassword, passwordEncoder);
    }

    void changeEmail(Email email) {
        this.email = email;
    }

    void changePassword(Password password) {
        this.password = password;
    }

    void changeName(UserName userName) {
        profile.changeUserName(userName);
    }

    void changeImage(Image image) {
        profile.changeImage(image);
    }

    void changeStatus(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getName() {
        return profile.getUserName();
    }

    Image getImage() {
        return profile.getImage();
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public UserStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

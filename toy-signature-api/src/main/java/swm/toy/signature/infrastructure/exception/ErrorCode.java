package swm.toy.signature.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    DUPLICATED_USER("there is duplicated user information", HttpStatus.UNPROCESSABLE_ENTITY),
    DUPLICATED_ITEM("there is duplicated item information", HttpStatus.UNPROCESSABLE_ENTITY),
    LOGIN_INFO_INVALID("login information is invalid", HttpStatus.UNPROCESSABLE_ENTITY),
    ALREADY_FOLLOWED_USER("already followed user", HttpStatus.UNPROCESSABLE_ENTITY),
    ALREADY_FAVORITED_ARTICLE("already followed user", HttpStatus.UNPROCESSABLE_ENTITY),

    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    FOLLOW_NOT_FOUND("such follow not found", HttpStatus.NOT_FOUND),
    ARTICLE_NOT_FOUND("article not found", HttpStatus.NOT_FOUND),
    FAVORITE_NOT_FOUND("favorite not found", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND("comment not found", HttpStatus.NOT_FOUND),
    EQUIP_NOT_FOUND("equip not found", HttpStatus.NOT_FOUND),
    EQUIP_TYPE_NOT_FOUND("equip type not found", HttpStatus.NOT_FOUND),
    EQUIP_BRAND_NOT_FOUND("equip brand not found", HttpStatus.NOT_FOUND),
    ;

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}

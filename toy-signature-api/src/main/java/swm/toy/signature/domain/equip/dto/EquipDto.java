package swm.toy.signature.domain.equip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EquipDto {
    private Integer equipCd;           //'보유장비코드'
    private String equipModel;         //'보유장비모델'
    private String equipNm;            //'장비명'
    private Integer seq;               //'순번'
    private String licensePlate;       //'차량번호'
    private String insuranceYn;        //'보험가입여부'
    private String routineYn;          //'정기검사여부'
    private Author author;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        @JsonProperty("username")
        private String name;
        private String bio;
        private String image;
        private Boolean following;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleEquip<T> {
        private T equip;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleArticle {
        private List<EquipDto> equips;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String title;
        private String description;
        private String body;
    }
}

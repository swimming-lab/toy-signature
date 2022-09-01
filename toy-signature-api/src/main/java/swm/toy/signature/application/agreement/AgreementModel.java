package swm.toy.signature.application.agreement;

import lombok.Value;
import swm.toy.signature.application.user.ProfileModel;
import swm.toy.signature.domain.agreement.Agreement;
import swm.toy.signature.domain.agreement.item.AgreementItem;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Value
public class AgreementModel {

    Long lessorId;
    String lessorName;
    String lessorAddr;
    String lessorTelNo;
    Long lesseeId;
    String lesseeName;
    String lesseeAddr;
    String lesseeTelNo;
    ZonedDateTime startDate;
    ZonedDateTime endDate;
    Long amount;
    Long overAmount;
    String etc;
    String status;
    Set<AgreementItem> items;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
    ProfileModel author;

    public static AgreementModel from(Agreement agreement) {
        final var contents = agreement.getContents();
        return new AgreementModel(
                contents.getLessor().getLessorId(),
                contents.getLessor().getLessorName(),
                contents.getLessor().getLessorTelNo(),
                contents.getLessor().getLessorAddr(),
                contents.getLessee().getLesseeId(),
                contents.getLessee().getLesseeName(),
                contents.getLessee().getLesseeTelNo(),
                contents.getLessee().getLesseeAddr(),
                agreement.getContents().getStartDate().atZone(ZoneId.of("Asia/Seoul")),
                agreement.getContents().getEndDate().atZone(ZoneId.of("Asia/Seoul")),
                contents.getAmount(),
                contents.getOverAmount(),
                contents.getEtc(),
                agreement.getStatus().getValue(),
                agreement.getAgreementItems(),
                agreement.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")),
                agreement.getUpdatedAt().atZone(ZoneId.of("Asia/Seoul")),
                ProfileModel.from(agreement.getAuthor().getProfile()));
    }
}

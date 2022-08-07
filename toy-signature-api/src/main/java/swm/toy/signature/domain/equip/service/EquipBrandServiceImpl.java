package swm.toy.signature.domain.equip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.base.exception.AppException;
import swm.toy.signature.base.exception.Error;
import swm.toy.signature.domain.equip.dto.EquipBrandDto;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
import swm.toy.signature.domain.equip.repository.EquipBrandRepository;
import swm.toy.signature.domain.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipBrandServiceImpl implements EquipBrandService {
    private final EquipBrandRepository equipBrandRepository;

    @Transactional
    @Override
    public EquipBrandDto createEquipBrand(EquipBrandDto equipBrand, UserDto.Auth authUser) {
        EquipBrandEntity entity = EquipBrandEntity.builder()
                .brandName(equipBrand.getBrandName())
                .build();

        equipBrandRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional
    @Override
    public EquipBrandDto updateEquipBrand(EquipBrandDto.Update equipBrand, UserDto.Auth authUser) {
        EquipBrandEntity entity = equipBrandRepository.findById(equipBrand.getId()).orElseThrow(() -> new AppException(Error.EQUIP_BRAND_NOT_FOUND));

        if (equipBrand.getBrandName() != null) {
            entity.setBrandName(equipBrand.getBrandName());
        }

        equipBrandRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EquipBrandDto> getEquipBrand() {
//        Sort.by(Sort.Direction.DESC, "vDate");
        return equipBrandRepository.findAll().stream().map(entity -> {
            return convertEntityToDto(entity);
        }).collect(Collectors.toList());
    }

    private EquipBrandDto convertEntityToDto(EquipBrandEntity entity) {
        return EquipBrandDto.builder()
                .id(entity.getId())
                .brandName(entity.getBrandName())
                .build();
    }
}

package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.SpouseDto;
import com.hotpotato.blueroof.model.information.Spouse;
import com.hotpotato.blueroof.model.type.Flag;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.repository.SpouseRepository;
import com.hotpotato.blueroof.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SpouseService {

    private final UserRepository userRepository;
    private final SpouseRepository spouseRepository;

    // 배우자 정보 저장/수정/삭제
    @Transactional
    public SpouseDto createSpouse(User user, SpouseDto spouseDto) {

        // 회원 혼인 여부 수정
        if(!user.getMarriage().equals(spouseDto.getMarriageFlag())) {
            user.setMarriage(spouseDto.getMarriageFlag());
            userRepository.save(user);
        }

        // 배우자 정보 삭제인 경우
        if(spouseDto.getMarriageFlag().equals(Flag.N)) spouseRepository.deleteByUserId(user.getId());

        // 배우자 정보 저장/수정
        else {
            Spouse spouse = spouseRepository.findByUserId(user.getId());
            // 배우자 정보 수정인 경우
            if(spouse != null) {
                spouse.setSpouseName(spouseDto.getSpouseName());
                spouse.setForeign(spouseDto.getForeignFlag());
                spouse.setSpouseBirthday(spouseDto.getSpouseBirthday());
                spouse.setMarriageDate(spouseDto.getMarriageDate());
                spouse.setWin(spouseDto.getWinFlag());
                spouse.setWinDate(spouseDto.getWinDate());
                spouse.setSpouseIncome(spouseDto.getSpouseIncome());
                spouse.setSeparate(spouseDto.getSeparateFlag());
                spouse.setSpouseAddress(spouseDto.getSpouseAddress());
                spouse.setOwner(spouseDto.getOwnerFlag());
                spouse.setCondition(spouseDto.getConditionFlag());
            }
            // 배우자 정보 저장인 경우
            else {
                spouse = Spouse.builder()
                        .user(user)
                        .spouseName(spouseDto.getSpouseName())
                        .foreign(spouseDto.getForeignFlag())
                        .spouseBirthday(spouseDto.getSpouseBirthday())
                        .marriageDate(spouseDto.getMarriageDate())
                        .win(spouseDto.getWinFlag())
                        .winDate(spouseDto.getWinDate())
                        .spouseIncome(spouseDto.getSpouseIncome())
                        .separate(spouseDto.getSeparateFlag())
                        .spouseAddress(spouseDto.getSpouseAddress())
                        .owner(spouseDto.getOwnerFlag())
                        .condition(spouseDto.getConditionFlag())
                        .build();
            }
            spouseRepository.save(spouse);
        }

        return spouseDto;
    }

    // 배우자 정보 조회
    public SpouseDto getSpouse(User user) {
        // 혼인 여부 조회
        Flag marriage = user.getMarriage();
        SpouseDto spouseDto = SpouseDto.builder().spouseIncome(-1).marriageFlag(marriage).build();
        if(marriage.equals(Flag.Y)) {
            // 배우자 정보 조회
            Spouse spouse = spouseRepository.findByUserId(user.getId());
            if(spouse != null) {
                spouseDto.setSpouseName(spouse.getSpouseName());
                spouseDto.setForeignFlag(spouse.getForeign());
                spouseDto.setSpouseBirthday(spouse.getSpouseBirthday());
                spouseDto.setMarriageDate(spouse.getMarriageDate());
                spouseDto.setWinFlag(spouse.getWin());
                spouseDto.setWinDate(spouse.getWinDate());
                spouseDto.setSpouseIncome(spouse.getSpouseIncome());
                spouseDto.setSeparateFlag(spouse.getSeparate());
                spouseDto.setSpouseAddress(spouse.getSpouseAddress());
                spouseDto.setOwnerFlag(spouse.getOwner());
                spouseDto.setConditionFlag(spouse.getCondition());
            }
        }

        return spouseDto;
    }

}

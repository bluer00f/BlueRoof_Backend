package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.*;
import com.hotpotato.blueroof.model.information.Baby;
import com.hotpotato.blueroof.model.information.Member;
import com.hotpotato.blueroof.model.type.Flag;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.repository.BabyRepository;
import com.hotpotato.blueroof.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BabyRepository babyRepository;
    private final SpouseService spouseService;

    // 세대 구성원(+태아) 정보 저장/수정/삭제
    @Transactional
    public AllMemberDto createAllMember(User user, AllMemberDto allMemberDto) {

        // 배우자 정보 저장/수정/삭제
        spouseService.createSpouse(user, allMemberDto.getSpouseDto());

        // 세대 구성원 정보 삭제 후 저장
        memberRepository.deleteAllByUserId(user.getId());

        // 태아 정보 조회
        Baby baby = babyRepository.findByUserId(user.getId());

        // 태아 정보 수정
        if (baby != null) {
            baby.setPregnant(allMemberDto.getPregnantFlag());
            baby.setBabyCount(allMemberDto.getBabyCount());
            babyRepository.save(baby);
        } else {
            // 태아 정보 저장
            baby = Baby.builder().user(user).pregnant(allMemberDto.getPregnantFlag()).babyCount(allMemberDto.getBabyCount()).build();
            babyRepository.save(baby);
        }

        // 세대 구성원 정보 등록
        for (MemberDto memberDto : allMemberDto.getMemberDtoList()) {
            Member member = Member.builder()
                    .user(user)
                    .memberName(memberDto.getMemberName())
                    .memberBirthday(memberDto.getMemberBirthday())
                    .relation(memberDto.getRelation())
                    .owner(memberDto.getOwnerFlag())
                    .yearIncome(memberDto.getMemberIncome())
                    .house(memberDto.getHouseFlag())
                    .win(memberDto.getWinFlag())
                    .winDate(memberDto.getWinDate())
                    .build();
            memberRepository.save(member);
        }

        return allMemberDto;
    }

    // 세대 구성원(+태아) 정보 조회
    @Transactional
    public AllMemberDto getAllMember(User user) {

        // 배우자 정보 조회
        SpouseDto spouseDto = spouseService.getSpouse(user);

        AllMemberDto allMemberDto = AllMemberDto.builder()
                .spouseDto(spouseDto).build();

        // 세대 구성원 정보 조회
        List<Member> memberList = memberRepository.findAllByUserId(user.getId());
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (Member member : memberList) {
            MemberDto memberDto = MemberDto.builder()
                    .memberName(member.getMemberName())
                    .memberBirthday(member.getMemberBirthday())
                    .relation(member.getRelation())
                    .ownerFlag(member.getOwner())
                    .memberIncome(member.getYearIncome())
                    .houseFlag(member.getHouse())
                    .winFlag(member.getWin())
                    .winDate(member.getWinDate())
                    .build();
            memberDtoList.add(memberDto);
        }

        if (!memberDtoList.isEmpty()) {
            allMemberDto.setMemberDtoList(memberDtoList);
        }

        // 태아 정보 조회
        Baby baby = babyRepository.findByUserId(user.getId());
        if (baby != null) {
            allMemberDto.setPregnantFlag(baby.getPregnant());
            if (baby.getPregnant().equals(Flag.Y)) {
                allMemberDto.setBabyCount(baby.getBabyCount());
            }
        }

        return allMemberDto;
    }
}

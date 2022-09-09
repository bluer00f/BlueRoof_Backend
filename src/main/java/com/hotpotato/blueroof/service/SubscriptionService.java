package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.BankBookDto;
import com.hotpotato.blueroof.dto.SubscriptionDto;
import com.hotpotato.blueroof.model.information.BankBook;
import com.hotpotato.blueroof.model.information.Subscription;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.repository.BankBookRepository;
import com.hotpotato.blueroof.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final BankBookRepository bankBookRepository;

    // 청약 정보 저장/수정/삭제
    @Transactional
    public SubscriptionDto createSubscription(User user, SubscriptionDto subscriptionDto) {

        // 청약 정보 조회
        Subscription subscription = subscriptionRepository.findByUserId(user.getId());

        // 청약 통장 정보 삭제 후 저장
        bankBookRepository.deleteAllByUserId(user.getId());

        // 청약 정보 저장
        if (subscription == null) {

            // 청약 정보 저장
            subscription = Subscription.builder()
                    .user(user)
                    .specialRank(subscriptionDto.getSpecialRank())
                    .generalRank(subscriptionDto.getGeneralRank())
                    .win(subscriptionDto.getWinFlag())
                    .winDate(subscriptionDto.getWinDate())
                    .winRegion(subscriptionDto.getWinRegion())
                    .winZipcode(subscriptionDto.getWinZipcode())
                    .winName(subscriptionDto.getWinName())
                    .build();

            subscriptionRepository.save(subscription);
        }

        // 청약 정보 수정
        else {

            // 청약 정보 수정
            subscription.setSpecialRank(subscriptionDto.getSpecialRank());
            subscription.setGeneralRank(subscriptionDto.getGeneralRank());
            subscription.setWin(subscriptionDto.getWinFlag());
            subscription.setWinDate(subscriptionDto.getWinDate());
            subscription.setWinName(subscriptionDto.getWinName());
            subscription.setWinZipcode(subscriptionDto.getWinZipcode());

            subscriptionRepository.save(subscription);

        }

        // 청약 통장 정보 등록
        for (BankBookDto bankBookDto : subscriptionDto.getBankBookList()) {
            BankBook bankBook = BankBook.builder()
                    .user(user)
                    .bankBookType(bankBookDto.getBankBookType())
                    .bankBookDate(bankBookDto.getBankBookDate())
                    .teenCount(bankBookDto.getTeenCount())
                    .teenPrice(bankBookDto.getTeenPrice())
                    .teenSumPrice(bankBookDto.getTeenSumPrice())
                    .totalCount(bankBookDto.getTotalCount())
                    .totalPrice(bankBookDto.getTotalPrice())
                    .build();
            bankBookRepository.save(bankBook);
        }

        return subscriptionDto;
    }

    // 청약 정보 조회
    @Transactional
    public SubscriptionDto getSubscription(User user) {

        SubscriptionDto subscriptionDto = SubscriptionDto.builder().specialRank(-1).generalRank(-1).build();
        // 청약 정보 조회
        Subscription subscription = subscriptionRepository.findByUserId(user.getId());
        if (subscription != null) {
            subscriptionDto.setSpecialRank(subscription.getSpecialRank());
            subscriptionDto.setGeneralRank(subscription.getGeneralRank());
            subscriptionDto.setWinFlag(subscription.getWin());
            subscriptionDto.setWinDate(subscription.getWinDate());
            subscriptionDto.setWinRegion(subscription.getWinRegion());
            subscriptionDto.setWinZipcode(subscription.getWinZipcode());
            subscriptionDto.setWinName(subscription.getWinName());
        }

        // 청약 통장 정보 조회
        List<BankBook> bankBookList = bankBookRepository.findAllByUserId(user.getId());
        List<BankBookDto> bankBookDtoList = new ArrayList<>();

        for (BankBook bankBook : bankBookList) {
            BankBookDto bankBookDto = BankBookDto.builder()
                    .bankBookType(bankBook.getBankBookType())
                    .bankBookDate(bankBook.getBankBookDate())
                    .teenCount(bankBook.getTeenCount())
                    .teenPrice(bankBook.getTeenPrice())
                    .teenSumPrice(bankBook.getTeenSumPrice())
                    .totalPrice(bankBook.getTotalPrice())
                    .totalCount(bankBook.getTotalCount())
                    .build();

            bankBookDtoList.add(bankBookDto);
        }

        if (!bankBookDtoList.isEmpty()) {
            subscriptionDto.setBankBookList(bankBookDtoList);
        }

        return subscriptionDto;
    }
}

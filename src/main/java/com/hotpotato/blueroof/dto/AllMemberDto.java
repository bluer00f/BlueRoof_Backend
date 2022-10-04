package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.service.model.type.Flag;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllMemberDto {

    private SpouseDto spouseDto;

    private List<MemberDto> memberDtoList;

    private Flag pregnantFlag;

    private int babyCount;
}

package com.hotpotato.blueroof.repository;

import java.util.Map;

public interface HouseRepository {
    //open API 서버로부터 전달받는 JSON 데이터
    // 조건에 맞는 주택정보 가져오기
    Map<String, Object> getAccStatForJSON() throws Exception;
}

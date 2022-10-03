package test.hotpotato.blueroof_test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.hotpotato.blueroof_test.model.user.User;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/postalcode")
@Api(tags = {"PostalCode API"})
public class PostalController {

    @ApiOperation(value = "user 우편 번호로 지역 찾기")
    @GetMapping("/PostalCodetoArea")
    public String postaltoArea(User user) {
        String userPostal = user.getZipcode();
        String userPostalHead2 = userPostal.substring(0,2);    //user의 지역 우편번호 앞2자리





        return "";
    }
}

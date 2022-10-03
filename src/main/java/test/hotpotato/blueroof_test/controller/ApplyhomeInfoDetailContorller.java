package test.hotpotato.blueroof_test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api/ApplyhomeInfoDetailSvc/v1")
@Api(tags = {"ApplyHome REST API"})
public class ApplyhomeInfoDetailContorller {

    @ApiOperation(value = "APT 분양정보 상세조회")
    @GetMapping("/getAPTLttotPblancDetail")
    public String getAPTLttotPblancDetail(@RequestParam(value = "subscrpt_area", required = false)String subcrpt_area,
                                          @RequestParam(value = "rcpt_date_gte", required = false)String rcpt_date_gte){
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = "http://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getAPTLttotPblancDetail" +
                    "?page=1" +         //page index
                    "&perPage=10" +     //page size
                    "&serviceKey=0945tWP987oA0xsvvbJq8tRwW2TzkLukSY6a%2FiP%2Bkvn9QHH%2F45sXWwP33MnWlgoAezS%2BKZFKesfv7jvBesGP5w%3D%3D";

            if (subcrpt_area == null) {
                if (rcpt_date_gte == null) {
                } else {
                    urlStr = urlStr +
                            "&cond%5BRCRIT_PBLANC_DE%3A%3AGTE%5D=" + rcpt_date_gte;   //모집공고일 GTE(GreaTer or Equal)
                    }

            } else {
                if (rcpt_date_gte == null) {
                    urlStr = urlStr +
                            "&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=" + subcrpt_area;    //공급지역명
                } else {
                    urlStr = urlStr +
                            "&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=" + subcrpt_area +    //공급지역명
                            "&cond%5BRCRIT_PBLANC_DE%3A%3AGTE%5D=" +  rcpt_date_gte;  //모집공고일 GTE(GreaTer or Equal)
                    }
            }

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
                result.append("\n");
            }
            br.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @ApiOperation(value = "오피스텔/도시형/민간임대 분양정보 상세조회")
    @GetMapping("/getUrbtyOfctlLttotPblancDetail")
    public String getUrbtyOfctlLttotPblancDetail(@RequestParam(value = "subscrpt_area", required = false)String subcrpt_area,
                                                 @RequestParam(value = "rcpt_date_gte", required = false)String rcpt_date_gte){
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = "http://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getUrbtyOfctlLttotPblancDetail" +
                    "?page=1" +         //page index
                    "&perPage=10" +     //page size
                    "&serviceKey=0945tWP987oA0xsvvbJq8tRwW2TzkLukSY6a%2FiP%2Bkvn9QHH%2F45sXWwP33MnWlgoAezS%2BKZFKesfv7jvBesGP5w%3D%3D";

            if (subcrpt_area == null) {
                if (rcpt_date_gte == null) {
                } else {
                    urlStr = urlStr +
                            "&cond%5BRCRIT_PBLANC_DE%3A%3AGTE%5D=" + rcpt_date_gte;   //모집공고일 GTE(GreaTer or Equal)
                }

            } else {
                if (rcpt_date_gte == null) {
                    urlStr = urlStr +
                            "&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=" + subcrpt_area;    //공급지역명
                } else {
                    urlStr = urlStr +
                            "&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=" + subcrpt_area +    //공급지역명
                            "&cond%5BRCRIT_PBLANC_DE%3A%3AGTE%5D=" +  rcpt_date_gte;  //모집공고일 GTE(GreaTer or Equal)
                }
            }

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
                result.append("\n");
            }
            br.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    @ApiOperation(value = "APT 무순위/잔여세대 분양정보 상세조회")
    @GetMapping("/getRemndrLttotPblancDetail")
    public String getRemndrLttotPblancDetail(){
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = "http://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getRemndrLttotPblancDetail" +
                    "?page=1" +
                    "&perPage=10" +
                    "&serviceKey=0945tWP987oA0xsvvbJq8tRwW2TzkLukSY6a%2FiP%2Bkvn9QHH%2F45sXWwP33MnWlgoAezS%2BKZFKesfv7jvBesGP5w%3D%3D";

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
                result.append("\n");
            }
            br.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    @ApiOperation(value = "APT 분양정보 주택형별 상세조회")
    @GetMapping("/getAPTLttotPblancMdl")
    public String getAPTLttotPblancMdl(){
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = "http://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getAPTLttotPblancMdl" +
                    "?page=1" +
                    "&perPage=10" +
                    "&serviceKey=0945tWP987oA0xsvvbJq8tRwW2TzkLukSY6a%2FiP%2Bkvn9QHH%2F45sXWwP33MnWlgoAezS%2BKZFKesfv7jvBesGP5w%3D%3D";

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
                result.append("\n");
            }
            br.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    @ApiOperation(value = "오피스텔/도시형/민간임대 분양정보 주택형별 상세조회")
    @GetMapping("/getUrbtyOfctlLttotPblancMdl")
    public String getUrbtyOfctlLttotPblancMdl(){
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = "http://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getUrbtyOfctlLttotPblancMdl" +
                    "?page=1" +
                    "&perPage=10" +
                    "&serviceKey=0945tWP987oA0xsvvbJq8tRwW2TzkLukSY6a%2FiP%2Bkvn9QHH%2F45sXWwP33MnWlgoAezS%2BKZFKesfv7jvBesGP5w%3D%3D";

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
                result.append("\n");
            }
            br.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @ApiOperation(value = "APT 무순위/잔여세대 분양정보 주택형별 상세조회")
    @GetMapping("/getRemndrLttotPblancMdl")
    public String getRemndrLttotPblancMdl(){
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = "http://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getUrbtyOfctlLttotPblancMdl" +
                    "?page=1" +         //page index
                    "&perPage=10" +     //page size
                    "&cond%5BHOUSE_MANAGE_NO%3A%3AEQ%5D=2022000248" +               //주택 관리번호
                    "&cond%5BPBLANC_NO%3A%3AEQ%5D=2022000248" +                     //공고 번호
                    "&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=%EC%84%9C%EC%9A%B8" + //공급지역명
                    "&cond%5BRCRIT_PBLANC_DE%3A%3ALTE%5D=2022-05-31" +  //모집공고일 LTE(LiTtle or Equal)
                    "&cond%5BRCRIT_PBLANC_DE%3A%3AGTE%5D=2022-01-01" +  //모집공고일 GTE(GreaTer or Equal)
                    "&serviceKey=0945tWP987oA0xsvvbJq8tRwW2TzkLukSY6a%2FiP%2Bkvn9QHH%2F45sXWwP33MnWlgoAezS%2BKZFKesfv7jvBesGP5w%3D%3D";

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
            br.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
package com.codefans.reusablecode.util;

import com.codefans.basicjava.algorithm.StringsSortByDict;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mpif
 * @date: 2018-06-07 08:50
 */
public class SignUtils {

    public static final String APP_ID = "app_id";
    public static final String APP_KEY = "app_key";
    public static final String TIMESTAMP = "timestamp";
    public static final String NONCE_STR = "nonce_str";
    public static final String IMAGE = "image";
    public static final String MODE = "mode";
    public static final String SIGN = "sign";

    public static final String defaultCharset = "UTF-8";





    /**
     * 生成签名
     */
    public static String generateSignature() {

        String signature = "";

        try {
            int appId = 1106879537;
            String appKey = "HxbQwzb3CsnMbTjU";
            int timestamp = DateUtils.getSecondTimestamp(new Date());
            String nonceStr = Md5Utils.getUUID();
            String image = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a" +
                    "HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy" +
                    "MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAE5AfQDASIA" +
                    "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA" +
                    "AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3" +
                    "ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm" +
                    "p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA" +
                    "AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx" +
                    "BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK" +
                    "U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3" +
                    "uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD2eNcC" +
                    "nsvy1OsXFDx8UmKxk3JxVHJzWjdR1n7DvoGkWISSKuxCq9vH0rRhj9qoLEiLxUgQ1IkeBUmKQEQj" +
                    "oEVS0tAEOyjy/apaKAIvKo8qpaWgLkHlUnl1YpKVh3KzR8VXkSr5WoXSs5IDKmQ4NZ00Z71uSx8V" +
                    "Qnj61zziZyRz9yuKyplOa27lPmrOljB7VCRBjSqTUYhJ5q/LEc9KVYeOlaRQzP8ALIrhvE1q32he" +
                    "vXpXo7QiuT8UwYinlUHgcVtEqJ5nJkeYzHkmqePmGatM5YdvlNVwMsc1sWOiADbyM4FJK5eQk09F" +
                    "OzI7nio5EwDnrQBEpIBPejqPqaVhhaVRzx0FAFuyby5xgc+tT3074CnpUFgpa4zj3qS9YEkHrQBT" +
                    "DbgMcCnsMED86dFH0OKJ8A5/CkMryNlto6CpIwUTIHJqNBukH1q9sGSew4FAIjCEgD8TV63izzio" +
                    "449xArQiUKtRJmkUIYzjA4qvJCXOMVoBeMmmrGTlvU8VHMW43KcVv+8AxwKtiLFPhTBY1KRSlIqM" +
                    "UQ7KaUqYikIqblWICvNNK1ORTGFNMTRXZap3MeVNX2HFV5VyKuLM5IwpVxVVhya0bqMAnFUSK2Ri" +
                    "yMdOKcWIAYdRSfdpcg5BoEOVyV611fht2FpKD0PIFch0PtXYaACbPPamhMiv161kuK3L9etYsg5o" +
                    "YkV2qI1K1RGkUNNJSmkoAt2n+qP+9RRaf6o/71FAH2yFoZeDTxQaAMu6TrWaU+eti5XOaz2T5qQ0" +
                    "SW61owrmqUC1oxDimDJKKWigQlFLRQAlFLRQAlLRRQAUlLRQAnamsvFPpDSYFWRetZ9wnWtSQcVQ" +
                    "nGc1jOJMjDuI8k1QaE5ramTrVCVcVlYzMuSFc0ioBwRUsvU1EGx1qkgGSRg9K53xFbb9NuP93iuj" +
                    "Y1ma1HvsXXGd3HFaIpM8NeLYr/Wq+371bF9bCK4nj/utis/Zwc9K1RoNRQsO7uBVTGSfWr4QtDgD" +
                    "tUDx7QoA68k0wKj5LACpOiACk2Fn4p7csB6CgC1Ygq2fbrVeZvMmx71PbkrGc1Hbrvdmx0oGSgbB" +
                    "9BVZ/nkC/iasynHHeokj4Lkc1I2RQriUn0q4y5VF7k5qK2jLMc9quLHmcDsopNjSJYY+Sauxp8op" +
                    "sEJIFXo4c1lKRvFEWzkUY5PtVvyaY0eO1Rc0sVwNoNB4p5Qim7aQDKMU/ZQVoEREYppFSsKjPFMG" +
                    "RMKgkWrLDNMK5NWmQ0Y93FnJFZciYNdHcQZGRWLcxkMeK2i7mElYoHikIqRhimEcCqIGjmu28N5O" +
                    "mkHt0ri0Xmu98PIU00oyEY5DeopoTK98vWsOUYJror5etYE4+Y0MSKbComFTuKhakUR0lONJQBat" +
                    "P9Uf96ii1H7o/wC9RQB9t0UUUAVLgcGqBHzVoz1QI5pDRNAKvR9KpwirkY4pgySiiigQUUUUAFFF" +
                    "FABRRSUALRSUUALSUUjHAoAilPFUZj1qzK1UpjWUiJMquaz5zjNWpW61QuH4NZGbZQmbk1XLUspy" +
                    "xqOmguOPrVa5AkXaegNTO+BVWRwSPSqTHc848QWG3UJyicHnpXNrH+824r1DU7VJxMQoyVHNedSx" +
                    "tDfMhHIatEzWLuV7dMo4I6A1WuRtmI6cVtwWn7541H3iR+XNY9wMzzOR0bFNbmjWhShQEk46Uipl" +
                    "mb8BUqMEhLdyc01ZAE5HNMkVxhdo9Klto9kWe9RuwVAfWrMRBQc0MaEEO5s45NPKAE4HCircUYwW" +
                    "46Uqwb8Z6E1FzRRKtpDgEnvU9rH5js3qcVaEHlwkgVZ0+04GR3rNyNIwJoYOBgVcSEVOkIQc1KqD" +
                    "FZ3NbEJjG2oXQYq252Z9KoTXKKTkiiwm7ETR80woKgm1GNOhzVF9UOeBxVKDZDmkahAxUbMo7isd" +
                    "tQdjgNUbXEjdzVKmR7VGuWBph61mJOw6mrcU4ZetDhYammTEc5puM08YIoqSyNhxWdd24ZSQK1CO" +
                    "KglTKmriyJI5aVcEjFREelW7xNszVVPFbnMOhXdIo6kmvR9LtxDpiBWJUjIB7VwumW4kuFdh8qmu" +
                    "5srrKCLGF7UuZJ2K5G1dFS9XrWBcLhzXS3q9a526Hz1TMkZ7ioWFWHFQsKRSISKQ0802gZZtf9Wf" +
                    "96iltf8AVn/eooA+2aSl7UnagCtP3qketXZqpkc0hosQiri9KqREYq2p4pgx1JRRQIKKM0ZFAC0l" +
                    "JuFJuoAfSZphkFMMlArk2QDSFqgMlNMnvRoFycvUbvUJkqJpalsnmFkeqUr0+SX3qlLLWMmZyZFI" +
                    "eTVG4bg1NLKMVnTy1jfUgrufmqMnFRyS81A9wB3rRModLJgHmqUknNK8uagOSabYCkbs8Z4xXD+I" +
                    "NOms9UV5ImRHOQSOtemaHHCb9XnwVXkA+tYfxLuY7p4TEo2xHqKcJanTSptxcjnbSzC3dxKf4VJH" +
                    "vxz/ADrjbht8czY5Lk16RLb+VYCUDh7UOT+B/wAa4YW6jTXfHztJj8BWkWayRglWEPGcHikKEEAg" +
                    "5NbjW2TFHjsXb6dqaLYNI7ADAO2q5iOUxG3ZANTKGzwTitZ7RPtEalRV6PT4eeMfUUnNDVNsyYGk" +
                    "24yTmta1RiQGB6VLFpsZkwuOtalvZBcc1EmmaRi0yv8AZ94VAO/NXoLYJgAVPDCOnv1qfaAwrJnR" +
                    "FEflgjB5prjYvy9qlY4FRPIMe9IbM26Z2XGec1j3UbknrW8+CxyKgaFG6iqTsYyVzl5LZ85PWm/Z" +
                    "JHHCmulNtGf4aXyQOigVftCPZHOx6ZMTkjFWl0vHLNW0yYFREYqXUbKVNIzBYR9CKQWSKeBitFhx" +
                    "0pmKXOyuRFZYygxnNBHNWCtRkUrjsRYpki8dKlxTWHy00Jo5nUV2znFVYojI4A5q5qnFxirej2e4" +
                    "GRl4610XsjmSvKxatbZLaIuRx6etXLa4b7QDnA9KYVaRSMcdqgtyRMFPUVlfqdCVtDevBlc1zt2M" +
                    "NXRzjMC/7ornr0cmug4upmvUDCrD1A1IaIiKaaeaYaBlm1/1Z/3qKLX/AFZ/3qKAPtmmk4FJvFRS" +
                    "yYHWgCGZ6qF+aWaXrVQy/NSFc0I5AKspJxWUktWFmpiuaPmUeZVHzvejzqYuYu7/AHpDIKpef700" +
                    "z+9AuYumUUwze9UjNTfOqWxcxdMtNMvvVMzUnm1m5hzFsy1GZaqmWonmpc6JbLTzVXe4x3qs0pNR" +
                    "HJpORDkSSXHvVOWfrzTnjyOtVZExWMmIjlmzVKWTNTS55qlLk5qLjRWmaq5PNTOKjIxVJlkZGaUC" +
                    "lIpVHNDYEkeVRiDjiuO1q9e4eS3fnB4rswv7s1x+oWZfVXwMgjNZXfMmj2sucXQnFnUTRCXwlE5G" +
                    "P9GXB/CuDWDdpowMkksf0r0hbcyeDbdccbNv9K5CW2S0tXjYY2y859N2a60zmlEzDaf8fL4I2bIh" +
                    "+VRJBBbw/vXG4sSVHJrS3/8AEnD5G+adpCTWJOkszxQoRFE3V26sfWq3J2FluLdbgSLGcL6mp01K" +
                    "zZORg57EU6/8PiPQ3lgJeTqWz2rlHsJ4QCFBDA4JqvZpke0aOwimgY7o5Bk9jV6B9q/NXD6Sk7XL" +
                    "Rh+AMkHoK6Cwvz5hgc5I7Z/lWcoW2NYzvudHHIATU28H2rPSVRyDVxTkCs2bxGStVGSTaauSggZr" +
                    "Mnfk1I2OD5PWnjBrOEvz1HeXpgi4OGbgVSVzJuxfluoYerZYdhWZP4gjSTYAM1c0W1t70lriUZxw" +
                    "KwdR0yOO6mxJht/AzW0aa6mEqj6FlvEJzt4B9CKni1becMoP0rnHtG85fm3nPWtS6jVkQIuGA5YU" +
                    "3GIozkbsU0Vx904b0NKyYPNY9o7gjePmHetQTkrg9aykrM2TbFeojTixNMzUljTTG6U9qbjINNCZ" +
                    "h38HmXyKB1Na6ultCkaAbiOfao5Ih54cjkDg1Xy287utat3RjFWZrxujLgqKpPFtvvlH3hUccpBH" +
                    "PFaVhB9pv4s8gDJqYrWxo3ZXL1wmIgPQVzt8OtdXdx8Guav4+tdRw9TEeoWq08dV3Q0iiE0w1IVq" +
                    "Mg0hli1/1R/3qKLUHyz/AL1FAH2T53vUU03FU1nGOtRyz8GgxchZZfeqhl5qKabnrVUz89aQuY01" +
                    "l96lWb3rJFyB3oN370yWzXNwB3phugO9Yz3vvUL33vRcVzca896jN371gm9yetNN4fWpbA3vtnvS" +
                    "/as96wUuCT1q3E+RWE5C1NQTk07zSaqRkmpwOK53IokLk0wmnYprChSExuaTNLTTV8whGOBVaQ1I" +
                    "5qFzUNjsVpRnNU5EAzVx6ryiouNGfIKhK5qeTqajq0yiPbT0TFPC5qZEqWwEROCKxb2ER3YYjrXQ" +
                    "qMGs7WIMxiQDpUxep24GfLPl7mzp0Sz+GYfRXP6GuR1u0aRHUD77Dkeldj4abzfDkqZ5WQj86zb2" +
                    "0DOOOFJ/lW/NqdrjujkUtiIIYtoIwcA+9S/2QtzZgMoDgnn1rRuIQkq4/h6VJaSgDY/TOatSM3Gx" +
                    "zy2d/bF4I3OwqQUPIxWPc6PPJKfNc4xgADGBXohCF93BBFZ17FGSCMcVopEuCe5wiaSLcEJuGevP" +
                    "WnxaYEkWUZDA5BzXRzonYVSdSTUOTHyojPyqvqTWjEzbRVNIjIyitJI+AMVmzSKI5gSmax7jPNbs" +
                    "6FYiKxrhOtJjMsZ8yntbxzHe4yRwKcUxJmplXIzVJmbiRxWqg/JxTn04SNukTcfXNWoxg1ZV+ORV" +
                    "qTJcUzJGlop3LEAaHstvXmtZn46VWkyw4ochJGb5G05pypirJjOeaTZUORaRFigipduKaRUjsQkU" +
                    "3FSGgCtESyF14qpc4Qr7ir0grOuCLmVVQ8L1NWtzNhF8x4FdN4eQNLM391QKwF226YPLHoK6rw1b" +
                    "lbGSVurtWlNXkTVdoD7tetc5fr1rqLwda5u/HJrZnIjAcVXcValGGNVnpDIGFREVM1RmkMnth+7P" +
                    "+9RRbf6s/wC9RQM+m1vB60yW8GOtYH2zHemNek96DlNWW6znmqzXHPWs1rrPeozcH1pDsaZugO9I" +
                    "bknoazBITUgagLF0yk96TOarhqerUx2J1RTUixKahU1OpPFQ0OxYjgU96uxQqBVOIkGrqHgVhUQW" +
                    "LKKB0qUdKgQ1JnFccgsS5pjGmF6jZyc0lcCTNNNCZNOC5rUmxAy1C61e8vNNMNRIdjNZDmoJU4rT" +
                    "eLFVpk4qLgY0iYJqIrzV6VMk1B5dWmUQgYqePpTSmKUHFU1cLD93NOmjE9qyEc4qAtzUscmDz0NC" +
                    "iaU3yyTDwhKyPf2b9sOP5VfvEVcnbzmqumw+Rrayj7sqFTV2/GD9Ks9e6bujmLpeSetUwcHNaFz9" +
                    "48cVRlXFNDaEaYjoaqzSk9TQ5OetV25qrmdiNyDQsWeTzT1TNPCkMBjNILBFCAxbFWgvNIiYqYLz" +
                    "QUiCdP3eaxLjqa6G5x5dYdyvJ4psTM4qCalQdqYeGxUi+lIkcBipBTVGaftxQA7qKjZaeMgdKKBW" +
                    "ItnNIygVISMU1jxSGV34NRHpUsnNQmhIGNPNOApAKkAGK0Rm2VLnPltjriqtralcGrN1IVbAFQI0" +
                    "jnAyBVdBLcekBnu8L3OBXoFtbC1sY4R2Xmub8PWPm3YkYfKnP4110p+WuiktLnNXlryoxr0da5q/" +
                    "710t6etczfnk1bMEYUv3jVVzViY4Y1Uc0ihjGozTiaYTSGi1bH92f96im2x/dn/eooGexGY+tN8w" +
                    "1AGp6kUjFRJdxNOUEmmripkxQUoj0XFSCmggCjd70IfKSipFqFTnpVhEJqrCsSKKsRrTI4yTVtIS" +
                    "BUyQ7D4xgVYQ4FNSM46VPHHXNMLEiHipMZFCR8VJtIFcskIhYUzHNTEUhWkkJiJUy4zUIFOU81pY" +
                    "kshRSlRio1NP6ioaKsQSqKpSpwavSVXl6VKiTYzJI/aqzgLV6WqUoya0URogJFIRzSE80o6VoolI" +
                    "NtBHFOpccU+UtIntm3hDnlGBq9qQyTjvzWSrmJsitksLqwSQdQMH8KbidtGpokzm7hfnIqjKvBrW" +
                    "uo8N0rOmXA5zWdjqMyRRntUWzOauMlM2AiqFYrqmKkC80rLtFMWTc20UxFqNOal2U63XI5p5GDUl" +
                    "JFW5TEeaw7nvW3dyZQr2rEus80yWZzHk4pUagIWJxTJUZPmoILqVIq8VVtpQ4waurjFADduaYRxU" +
                    "x61G44oGREVE+RUx61A/SgRCxphGTT+9JjvTRLYAYFA6U4enamk4VmxnArRGciuYfMkNaNjpDzuO" +
                    "ML61y1vrDpdMzLzu79q7DTdY3RhvzFbRpJ6sxlXaVkdFa2sdlCI0H1NPmfK1DFcLPGHU5p0hytbI" +
                    "5Xq7syr1s5rmr89a6O871zd/3pMaOfuCfMNVWJqzcffqs1ItDCaaacaaRSGWbU/uz/vUUltxGf8A" +
                    "eooA9UU1MpqFRUyipEkTIamBqJFqcJxSKSELU4HigrT44yTVIGixbRFsE1oxw5HSobVMcVpRKBVk" +
                    "jobbpV1bfimxEA1c3riokxEQhwKcqAGnGRcdaYJVJrmmImAoJ4pAwxTWbisXElsax5ppPFNY800m" +
                    "mkA8txTQ2DTC1M3c1aiCLivmpM8VSWSpfN4ocBj3bmq0zcUrSVFI/FSoklWQ9arSDIqaRuahZs1a" +
                    "iMrMvNLipdmacI+KqxSIQKeBViCynuDiGFn+gq0dGvo13NbPj25oSLVjKkFaOkNut5oiehDCqk0Z" +
                    "QkMCD6GptJbbf7OzqRT6GsHaVyK9TGcVkzKTXRXcXzHisaeLDdKyaO6LMxkO7timFe1W3UYPeqsp" +
                    "xkikUU7hsDFT6da+YjOeearuN7VoaZeQ2hMc5wrdD707C6lhYtgPFRyA1P8Ab7RpvLyRnv2p0saB" +
                    "dwIIoUR8xjXJ61lTck1rXIwe1Z0iryScCiwpMrxxrjpTLmMFOBTlvbdX2gM56cCrNwsXkb92BjvR" +
                    "YhNGFF8spHoa14uVFZ9sollZx0zxWgh20ASEDmon7jNSs2RUL8mkMhPSoX4qVzioCc0EjGNJ+FDC" +
                    "g9KolhnirOnosl3Gr/dJqqKS3udusQwjqF3GtYLUyqbFXxh4bbTLhb6Ff3Eh5x2NVNJuQw2Zr2a2" +
                    "0y31/RZLG4UHcuAa8b1HRbvwz4geyuVIXOY27MK6rWZyG1p2rG3maF24966FbxJYxivONTkaG5R1" +
                    "OK6Lw/dPcbQzZFC7EtGxd5IJxXN33JNdjexItqTntXBajOY5mAPFElYaMu5Hz1WYVNJMsh9DURFQ" +
                    "URmmmnmmmgZPbD92f96ilth+7P1ooA9XWOp0SpFjqVUqBiJHVhY6VEqZRigoi8rNTwxjjilwKfEQ" +
                    "DTQmW4UxiriVVjap1erbM2XI1709uBUUb8U93BFZyAjkbApiMc0yV8VEknNZNAaiN8vWlY5qsj8V" +
                    "MDmo5TNgc00mnEZqJ80KIxjvioxKM4pkxIFVd53VaRSL4lFSCUEVm+YQakWU+tVYGXsg1FIeKW2S" +
                    "W5bZEpZvQVfTQL6XgqqD1JpqJJgzHmo1610w8JTOw8y4UD2FaVt4YsYAC4aVv9o0+UdmcvYaZc37" +
                    "Yij+Xux6V09l4atoAGn/AHr+natmKFIUCRoFUdABUlUoopIjjiSJQsaBQOwFPxmlqrNc+VnIqkr6" +
                    "IZn61psFxCXKAP6iuJdGs7yN+ysDmt/V/EkaExE81y8199oY89aqVHS/USlY3NQQBywPB5rFn681" +
                    "qxSG506GQ9QNh/Cs+6QjPFcck07HpQleNzKkXGaozdDir1weT6ms+Vuagu5Ai1FdAbKlZiBxUUgL" +
                    "IRVIhyMljJGx2MVFWIdWuoUwx3r6GkZc54qMxLtJxTRHMyc6ushwUIJrKvriaZsfdT0olAWQYNOn" +
                    "AdQaAbZQE0kf3Dg0vnSS/wCsckelOMfOaaFw1Ak2i/ZsAtXc56VlxMVqysh6VLRXMXd3Y0x24poc" +
                    "kUxj1qSr3I3Oaj604nNIBimIQimkU8+1NNNCY0Djmsywk3+Kc+2BWjM4SIn0FZGjnf4jHrit6ZhU" +
                    "PaPD85heM5rW8ZeFLbxNoxlVQt1CN0bjrn0rntPby1jNdlpeoBlMTHqMV2JXRyPc+aNat3WXYww6" +
                    "NtYe4rV8PnyE8x+ABU3jaEab4rvInGEkbev41zFzq7iMxRcA96z2ZW50Wp+LEMvkocgcZrB1GXzA" +
                    "JM/erF5dwe+a07z5baMd8Um7jsUSWJ4rQs7Z5hjBqC0i3sDiut0qzVVDEURVwbOfubBohkjFUGGK" +
                    "6/VosqdormZIG3HihoSY22/1Z/3qKsW9swjP1opWHc9jCU4DmpzGMUbKzKGLTs04JSlKAG7qcr0z" +
                    "y+amSPmmhE8bEgVYUP6VHEuMVdhALAU7ksIkk9KseS7djVuGMnAArQhtScZFFriMJrFmpYrAg/dr" +
                    "plsx6U42gHYU+VC1MOO1wOlTrbe1aRt/ajyCO1JomzKS2o7ig2SntWgsZ9KcYmx0qbDsYdxYrzxW" +
                    "c1kA3SumlhcjpWfNbyjoKLDRitaHOcUw2zDtWt5UvoPypRDKxACg59qWoNmn4btljtWkI+djitzF" +
                    "U9Nt2trUK4wxOSKu1aLWwUUUUxhRRRQAVia3cLbwSMTjAraNeafEHWvs8ckStz061cN7ktnFanqh" +
                    "uL5yGzzxVyykIj3O1crZlpZjI3c1rS3Xlw4zWifck6zQtVjlu5bBmGXG5PqK1Z0ySMc14+dZlstT" +
                    "hu4mO6Jw3XrXr8F3FqFjb30JBjlUN9K5ayu7nZh5aWMa7TbkVlyKQcmt6/TknoD3rIlTIOOornZ0" +
                    "mbI2GqeKEy4xWbeStGxxVEa/qNizkQpJEfwIprUzZuS2eOuBVKWFRms9PEbXHM0Tp9DmnHU7Vzjz" +
                    "CD7iqUStCKazLtkNSCABcbuRU3nRsDtkB/GqU06ISS/60uUd0P8AK55oMPHSqT6lGoGGZvoKibVZ" +
                    "T92Nj9aORkNo0/K9qQFEPLAGseTULxhhSFz+NRRWks7+ZNIzN7mny9yL9jpYznoaRs81Wsy8YCE5" +
                    "FXcAgnHNZstFbnJpccU5hzSGkMb39qRhTuhqKRqpCZTvXwm31qp4cTfr7N6CnXMm92PYdKt+DofM" +
                    "1CWXH8WK3gc8z0mJtix1qWMreeGBrHbjYK1tN++c11wOeRwfxcsGMltqKjj7rGvLHAZc19EeNdI/" +
                    "tXwZdnbl4gWH4V86ZxlT2rOorSHHYdCB5yD3rQ1BS2wDpWdCf3oPpWzFJFKoDdRUoZJpdoSRkV0y" +
                    "zJbxBc84rFhuViTCDmneY8pyc1aJZanufMJ4zVdYw7fdp6rmrkMAxkigCGG2JQ4XjNFaEU0calc9" +
                    "DRRZAejFaAlOzSg1gaCBBSlBTqcOtAEYiHpUix47U8AU9RTQColXLZMuKrqKvWYy1BNjTtwBjitS" +
                    "BwB0rNT5eoqX7SEFNAawkHYCgtx2rEa+Y8JyfSpBPdEf6r9RVWEamR7UvBrL865H/LI/mKniuJGO" +
                    "GUihoVy+qipAvFV0kNTq2VpIYFKieP8Azips5pChboaLAVfKJ6D9Ksw24T5iBn6U9IwvXk1JRYaQ" +
                    "UUUUDCkZtopaqXcm0YBppXAsK4bpT6oQSfMDmr2eM0NWEmQ3UvlQM3fFeB+Nb5rrV5Iy2fmr3PVZ" +
                    "RHYOxPYmvnbVZvtWu3LjkbyBWkdiZbiW4CIKgvrnCkZqV3CR1iXsxJPNFxIpXMm5zXc/DzxB5TNo" +
                    "9y/yP80JJ6H0rz13y3NT2szQzJJGxV1OQR2rNq5rGTi7o93u03xlQO3FZDpwVPWl8P62ms6YrOQL" +
                    "iMYdf61Znj54rlmrHdCSaujmL6zZmJGM9s1FHZBk2OM54rdnjHeq5iwOKi9irXMF9IWGQ5X5TUUm" +
                    "mREZ2CukjdGXZKMjpVWe0AYlGGOtaJjTS3Obk02IDAyKgGmwjO4k1vSw88gVWe3U5waLjfKZMtvA" +
                    "g+ROnrVZlDcBea15LU55xiovJReeM0myW4lCO0ycsOKtRRLnAHFTfe4AxT1QAVDZIoiCgGnb+MUF" +
                    "uKjJ4pAIxpg5NKSR1phfBpiFc4FUp5MKR3NTySAAkms6V9xJNUkS2Vbl9qHFdP4Jtttv5hHLHNcl" +
                    "ckyMEHUnFekeG7XyLFBjHFdEEc8zVY5mA9K17MiPn1rHPMxNaNsxb5e9dMTBnTeWJ/Dd6pGQY2/l" +
                    "XybefJezIO0jD9a+slYxeG7kn/nk38q+TLtg1/cH1kb+dTU3CIsfQmrEU6oRk1XHEf1phrIs24Ly" +
                    "Lua0YbyD++K5UHBpdxHencVjsEuod2d4P41K+ojGFZfzrixIw/iP505ZHJHzGnzCsdrY2F3qMTzx" +
                    "I7KHK5A9h/jRXr/wu8PA+BrSaVF3Tu0nI7dP6UV0L2a3M7y6FTdShqiBpwrjOglDGnBqjFPAosBM" +
                    "hzUyioE4qyhBoFckRa0bRcDNVIxmtGBQEpiuSs5AFVZ5jjrUkzjNUJmy1FhXJ7dw83QnFaCPnsfz" +
                    "rJgHJIrQhBpibLo5HQ/nSqNp70xAcVJjis5SZHNcmEmKtwuSgqgvFaVrGwjBYY9qcWOJMqmpKKKs" +
                    "0CiiigAooooAKx9SmCE1sVy3iCUrMF9TWlNXZMjUtG3qDWizYhz7VkaO2+Fee1as3Fu30omveBbH" +
                    "IeN9YWw0SZt2DtxXz5HqeZXcnlmJrvPitrTEfZEbqcEV5MDiiWmgjoJdSDjANUJpg/eqOTjg01nY" +
                    "d6m4yRuTU0C5YVWR+eatxSovNAzf0jUZNLuUnjPsy+or020uor+0S4hYFWH5V4u14o4rf8L+KF0y" +
                    "7FvO3+jynHX7p9aipG6NaU+V2Z6LcRZIIxiq7occVccrJEHVgVI4Iqs461ySR2xZRkXFVJZGHQ1f" +
                    "mU1QlTrxSTKauUZmJPWqzsQevNTyqaqOrZ6mquZNCM7HvSBSeaVEOeam2ErSbBIYUAGab0JqXBHU" +
                    "dKjakUMzTCTzkUOcEYqNm6UEtgWqJzihm5qCSTtVJCuRzSZ47VTkbipHOTVeU8GrSIY7TIDd6rGu" +
                    "MhTk16pZRCG2A9BXEeDrEySyXBHGcCu+cbIcV0QRzyZXU/vDWnZ/64cVmR/erT087phW8TJnSapK" +
                    "IPC12f8Api38q+SJPmuZG9XJ/WvqvxTL5Hgu/fH/ACwb+VfKi8sfc1FT4hxLDDEa1HT34AFRGsyh" +
                    "aXPFNzRQA6nxjLqPU1GKtabEZ9Stoh/HIq/macdWJ7H2B4PtfsvhDSocY22yfyoqWC8SztYbdRxH" +
                    "Gq/kKK0cZNkpqx54BTgKBUiisi7gAakVaFFSqtArgq1KoxSqtTKlIVx0ZIrRjclBVJE5q9GMKKBX" +
                    "I5Bk1VeElq0CuTSiHJqkK5WgiIFXIlIxTkjAHSpkTFVYhsVRUoFIF5qdE5rGSHFE9pbDAkcfQVdp" +
                    "FGFA7U6qSNUgooopjCiiigAooooAK4/xG4+2qveuvJwM1wuvzB9WOOgFbUdyJvQ2NBb5Bk1u3H+o" +
                    "b6Vy+iy4OPeuivpfL0+WT0QmioveEnofNPxHkMniCQZyFris10/jCY3GqTyk9WNcqp+aoluUticU" +
                    "jCnL0oapAgbpUJZh0JqdqiIzQMiLsT1NSWQaXULdCeGkUfrTSnerOlJu1i0X1lX+dIHsfSLaAYdC" +
                    "t721BKbAZE9PcViSjBznIPNenaKg/sa3UjjYAR+Fch4l0JtOmNzAubWQ8j+4fT6VFWN9jejUskmc" +
                    "xJyvHWqU/Q1ekAUDnoapTAYJFc3KdakZ0i5Bqs0eRV1yCajOMHjvTEypjHUdKQ5HU1OQCM96hcYF" +
                    "BNxjHPB6VA5HapHOO/aq7HrU2FcaW61CzHNOc4FQO/GB1qkhXuI7Y471CRmlNBPFMZC4qpMD0Her" +
                    "bmpNNtDe6pBCBkFsn6VcFqZy2O48MWH2bTogRhiMmta6JGBVq2gEMIAHQVXnXc9daRzNleLOa2dK" +
                    "iBlBrPjiHFbemQ7TmtIkMb4+kMPgTUD28oivl2Llq+lfiZMU8CXoz1TFfNkXWsqnxDjsPk61HT36" +
                    "1GagoCaTNN70tADga2PDAD+JdPB6CdD+RrG71teFQT4ksvaQGqh8SFLY+nIHaePzDnk0UyyJS1Qe" +
                    "1FdljK5hBKkVKmEdSCP2riKuQqlTKtSLHUix0CuNVeKkVaeqVIEpCuIg5q5EuQKgVasx9KaC5OsW" +
                    "alWCiNs1aQcVSAhEOO1SLCanGBShwKY7DVhOasJDg0sR39OlThcVDLSFHSiiikUFFFFABRRRQAUU" +
                    "UZoAjnbbExA7V57qCyy38khB5NegzYMZFYstnGTnbzW9KSW5lMxtJZkk5FaviC98nw/cN0OzApI7" +
                    "VEkyBWP4xlK6SsIP3jVSsxdDwXxFzMx71zSnmun8SLiRq5QHmsJbmqL6YxSPUcTcUsjYpCGEZNIQ" +
                    "KaH5p/UUARNV3QI/M8QWK/8ATUVUcVreEovO8VWC/wDTShbg9j630pdumQD/AGRVi4gjuIHilUMj" +
                    "DDKe9Msl22cQ9FFWD0oluOOx4/r1g2m3siLl4c8E9R9axZJgV24612/iuL9/Jx1rybVb6706bcmH" +
                    "jzyGFZTh2OmE9NTaKk5OeKhbvWba60k6DKFT7GrqTrIcg1lY1uOPHpUTHNDvt7Gq7ScnA60hWYPj" +
                    "mq0jge1EkpHAqs7bqA5RHfNMxS4pccUhpEZqJuKlYVC5poGROa67wPphdpL51/2U/rXLW1rJe3cd" +
                    "vECWc44r1/SNPSwsYoEGAi4ropR6nPUl0JZBtjrOY5f2rRu87cCqWyt0Yj4x0rYsjwKylXAFatkD" +
                    "xVolmD8VpQngWcdyVFfPCV738X5lHhDyx/FIorwRelYz+IqOwN1qInmnt60w1IxuaXNJRQA7OK3/" +
                    "AAZG8/imxjQFmaQYFc/muh8EXBtfFthKp5EnFXT+JEy2PqyyMVpaJDtV2A+Ykd6Kz7WXdbq2etFb" +
                    "uOpncywtSqtIBUgFc5NxVWpAtNAqQCkAoFOAoHFKKAHCpFbFR5qKVyqnFNDRdW4C96uRXAZeCK5K" +
                    "e8de9WLS/Yp96rRpy6HVCYHuKPOXPWudN+y96rvqhEg+b9atRuQzubaRPLAzzVgHNc3pt4ZVHNdB" +
                    "CcqKynGxpFktFFFQUFFFFABRRUEsoUdaLCuTFh61GW5qm1yM9aVZwx61VhXLL/MKhMOakVsipRii" +
                    "9gtcoNBtPSuQ8X/N8vZRXdSAVwnik58w0cwcp4n4mX52rjf4jXX+KJPnYVxpPNJlFqJqWVqhjPNS" +
                    "P0pARg81MjVX709TigCV63/AibvF9l7NXPZzXT/D4Z8YWn1prcmWx9X2/FvH/uipT0qOD/UJ/uip" +
                    "D0oe40cR4qj/AHp4rynXbUSxuuOa9g8UISwPrXmWrQ4LHHrSkjSDPN43a2uCh6ZrZt7o4HNZ+sWx" +
                    "RzIoqvaXOVFYyj1NYStodKLkMuDUbyDtWekuak3Z71nY2uPY7jzSUg5opAGOaCKWpYLa4u32W8Ly" +
                    "N6KM00m9hN2Kj1EI3lkVEUszHAArp4fBWrTJvkEcIP8AfbmtfSvD9rok4uZ5vtE4+6AvAraFKT3M" +
                    "pVV0JvCvhn7An2idc3DjnI+6PSut2bRisOPX5LdmaaNWh9B1FbVtdW+o24ntpA6+ncfWulK2xzN3" +
                    "K06knpVfYd1aDpnOah8v5qaERKnStS2wqgVTC/N0q9CmMVaJZ5/8Z5lXQbWNerS14mBxmvXvjSWW" +
                    "309SfvOTj8K8gzxWE/iZaGN1pnWnNUZNSMKKTNGaAFBrY8NSCLxHYO3QTLn86xs1c0uUw6lbSf3Z" +
                    "FP61UXqhPY+qbaUeQu0jGKKoWU3mWkbA9QKK6m1cyLoFPFNFKK5SSQdKeDUa9KeBQA4HNKKQUtIB" +
                    "wpsyZQ04U9xuShDOdu4+TSWQ6ir11Fyaq2y7XNHU3jqh0oIFZsqsZhzWpKeKqKgMy/Wtosza1N/Q" +
                    "0YYya7GAYjFc9pEGApFdKowoFKq9RxFooorEsKKKKAGscCs65BY8VpMMiovJBNNMTMtbdjViK2Iq" +
                    "+IwO1OAAouCRAsWBUoXAp9FIZXmO1Ca878Uy4jevQ7z5YHPtXlPi+6wjjNIDx/xLLulf61zGea19" +
                    "dm3zt9axc0wJ0PNSE1WVsVKG4oAWlBpuaTdQA/dXW/Dg58YWtcdmuv8Ahrk+MrYU47ky2PrKH/Up" +
                    "9BTzTIeIk+gp9D3KRg6/amdQR2rzTXLfyy4r0XxH4hsNN2W88g81zgKK4vxDC0irKqnY4yDTew4v" +
                    "U86vLUToykc1yLxta3LRnjB4r0LyGM2AM81zvibS2icXCr9aztoaMzIpeBVhZKzomqyrVm0aRkXR" +
                    "J71NCrTyrGgJZjgAVRDV1XgOAT+IkduREjPjFKMbuxUpWVy/pvgm4k2y6i4gi6lP4jXVwyWWlxC3" +
                    "sYo07Z7moPEss0eh3k0MhEojJVvSvJfAGo3d/wCK1+13MkuFOAzZGa64xjF2RySk5bnrtxNM/wA7" +
                    "E49KoSgscmtG5/1Rqltzk1bZKMbVn2Q7R1NZVhqtxpV2s0DkD+JexFXNVffcEDotYUzYas76lWPV" +
                    "dO1K31e0E8JAcfeTuDU4Hzc15FF4nl8NzRXMfzIzYdPUV6ppOrWeuWEd3aSBlYcjPKmtE7ktF0gZ" +
                    "qxGTlcdM1Bt5ANTxrgqferRJ5T8aXJm05Sc/eNeTmvVfjTt+26cAOdrf0ryluBXPLctETGmU5qYa" +
                    "kYUUUlAC0+FtsyN6EGo6UHkU0B9GaVqUa6TaEt96JT+lFcdoN39q0O0YMfkjCHn0ore5FkesinU0" +
                    "dacKxMh61IKjBp26kA8UtNBpRQMdUqcjFQ1JG3akBBcx96oBNrk1rTDdU40hxa7yvzNyfajc1gzn" +
                    "JTUSD96p96uXVu0bEEVVUYcVSdimjs9HwY0rcrn9Fb92tdAOlEncSCiiipGFFFFABRRRQAUUUUAF" +
                    "FFFAFHVHCWbE+leIeNL4AuAa9g8R3IisZOegr538X6hvkfnvTSEcNqM3mXB5qnmlkfdITTM0hjs0" +
                    "4NUeeaM0AS7qM1HmjNAEma7T4XDd4zt/pXEA13/wiiE3jSPPZacdyZbH1RH/AKtfoKivLgW1tJKe" +
                    "AoJqYcKAPSuN+Ieux6Tokke8eZIMAZoW4zy/VLyTWPEs87MWVWwvtXf6DOl5p/2S6AcAYGa878Pw" +
                    "NMPNYfM5ya663325DRkg1qloI1G8OWccrtDICf7prmvEOitJbuhTtxWv9qkEpkBO81ZS5NwuyZQw" +
                    "qeW6LTPDLyyksrgo6kDPBpqmvV/EvhFb2yae2HzAZxXlksL28zRSKVZTgg1hOLW5rFpgGrvvhhB5" +
                    "moXsx6JFt/EmvPxXonwyyV1FR12qaKfxDqfCdD4ojUaDfBjtXym5/CvFfh3keMIQDwc1674zeR/D" +
                    "F8nOfLNeS/DVN3iyM+iGt38SOboe23IzEaz5n8qBm9q1blcW1YWov8oiH41TBGBdAtub1rDuWw5r" +
                    "oblcRmuavOJDWbLRzHiO43SRRA9OTTvDHiq88N3yyQuWgY/PGehrO1WTzL9/biqJpJ2Ez6d0LXrL" +
                    "X9PS4tpAcjkd1NaaF1kAbp2r5p8O+JLzw7erNbuTGT86HoRX0B4Y8T2PiGzjkRwH/iXPKmt4yuQ0" +
                    "eefGdt2pafkY+Vv6V5W5zXq3xqMR1HT/ACznCtn9K8nc4rGW7KRGTzSUGkqRhRRRQAZoFJQKAPQf" +
                    "Cd8qaIEY8rIw/lRXL6XcvDasoJA3k/oKK1U9CeU+mwDmngGrQt/an/Z/aszIqAGnYNWxb+1PFsfS" +
                    "gCkM0uaufZj6Un2f2oAp7jTlcgirX2Y54FW7fRpJcF/kX360gDTLU3M4dh+7Tk+5roCoK4I4pkEK" +
                    "W8SxoMKKloNYqyMDVtOBUuorlZojG/TpXossYkjKmuU1SwKOSBVbotE+iSfIK6dTlQa47SXMcm01" +
                    "1kDbkFSDViaiiigQUUUUAFFFFABRRRQAUUUjHAzQBx/jbethI0fJA5FfMfiO9Mt26Z719W6naHUY" +
                    "5ItvWvm34k+GZNG1Mz7cJIea1cfduRfU4SjNFJWRYuaKSigBc0UlFADga9M+CkYfxgSeyV5jXU+B" +
                    "vEY8N64LpjhSMGmtxNXPrfU9Sg0qwkup3Cqgzz3r5q8V+K5vFXiYxIxNujcD1qbx98TJ/EEC2ttJ" +
                    "tjx822uT8KRmS/3Yyc01vYD1zw3ZYhQY7V0b22BTNAs9lqrEdq1ZIuOlbk3MR4MUsSFWrQaKmrDl" +
                    "xxSGaVooaAAjOa8v+JGhLZ3aXsSYV+GxXqsGFQAVQ8R6Omt6XJAw+bHFKpHmQ4Ssz576V6P8KhmX" +
                    "Uif7i/zrg9S0+XTb6S2mGGU/nXoHwoXC6k2OyiuamveN6j902vGCqmgXxxx5TfyryD4YLu8WfRDX" +
                    "rXj6Ty/DF8R18sivK/hSufE7n0jNb/bOfoezXZxDg1zFxL5twx7dBXRas+yzc98Vyy8miW40RXY/" +
                    "dk1yWoNs3n0Fdfdj90fpXD69IY4JiDjioYzi5XMkzse5NRmlzxSHpUgFamha3daFfpc27kAH5lzw" +
                    "RWVS7qFoB1XjDxQviOW2dFK+Wp3Z9a5NjmhmzTDQAGikooAKKKKACgUUUAaFkT5J/wB7/Ciksv8A" +
                    "Un/e/oKKAPruaG4N3EkcrpEytuIRTtPy46j/AHqrL9vAhP70lslv3Q+X519v7ob862x1p46VRkjE" +
                    "he/BiLwzMu8lwUGduOn8ue5zjirGL0Ek+ccOd4Ea4I+bAX2+7z/9etYdalWkMxIP7QkuYVmhZV3E" +
                    "uQo2/dIx9MjP/AvateOxL8twKnHWrQ7UDSTIoraOL7qjPqetTUdqKRSQUUUUDCq11arOh45qzSGm" +
                    "gOb/ALPaKfIFa0BZFGakm+9T06UMdyRZM8Yp5BIpqU+kIB0ooooAKKKKACiiigAqvcXcVvjzGxU5" +
                    "rm9c/wBc1XCPM7CZrDVLLBPmqK8T+M+oWt9AIocMwOciuuuP4q8s8d/x1bjyrQnc8ypDTqaaxLEp" +
                    "aSigAzRmjtSDvQAvSjNFJQA8GvSvhto63Uqyt3Oa81r2H4Wf6pauG5Mj1y2gWKFUA4Ap8qcVIn3a" +
                    "JPu1syTPdMVJbQ7jkilk71Pb/dpDEI2NVmMgioJvv0+LtTA8d+JkXl+IFYLgMtb3woT/AIl+ov8A" +
                    "7QFZnxU/5CkH0rZ+FP8AyB7/AP66D+Vc8V+8NpfAL8R2C+FL0k/w4rzj4Rx7tduH/ux16F8Tv+RT" +
                    "u/wrgvhB/wAha7/3K0+2ZdD1DWj/AKG1c5HXQ65/x5t9RXPRU3uNCXQ/dGvO/FTbYWHqa9Fuv9Sa" +
                    "818XdF/3qzkM5U8U3NKaaakAozRSHrQAhooNHagBKKKKACiig0AFFJS0AX7L/Un/AHv8KKWx/wBS" +
                    "3+9/QUUAf//Z";
            int mode = 0;

            String encodeAppId = urlEncode(appId);
            String encodeTimestamp = urlEncode(timestamp);
            String encodeNonce = urlEncode(nonceStr);
            String encodeImage = urlEncode(image);
            String encodeMode = urlEncode(mode);
            System.out.println(appId + ", encode:" + encodeAppId);
            System.out.println(timestamp + ", encode:" + encodeTimestamp);
            System.out.println(nonceStr + ", encode:" + encodeNonce);
            System.out.println("image:");
            System.out.println(image);
            System.out.println("encodeImage:");
            System.out.println(encodeImage);
            System.out.println(mode + ", encode:" + encodeMode);

            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put(APP_ID, encodeAppId);
            paramsMap.put(TIMESTAMP, encodeTimestamp);
            paramsMap.put(NONCE_STR, encodeNonce);
            paramsMap.put(IMAGE, encodeImage);
            paramsMap.put(MODE, encodeMode);

            String sortedDictStr = new StringsSortByDict().sortedDictStr(paramsMap);
            sortedDictStr = sortedDictStr + "&" + APP_KEY + "=" + appKey;
            System.out.println("sortedDictStr:");
            System.out.println(sortedDictStr);

//            String signatureStr = new String(Md5Utils.getBytes(new ByteArrayInputStream(sortedDictStr.getBytes())), defaultCharset).toUpperCase();
            signature = DigestUtils.md5Hex(sortedDictStr).toUpperCase();
            System.out.println("signatureStr:");
            System.out.println(signature);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


        return signature;

    }

    public static String urlEncode(Object obj) throws UnsupportedEncodingException {
        return URLEncoder.encode(String.valueOf(obj), defaultCharset);//.toUpperCase();
    }

    /**
     * 验证签名
     */
    public static boolean validateSignature(Map<String, Object> paramMap) {
        boolean validateSuccess = false;

        int appId = Integer.parseInt(String.valueOf(paramMap.get(APP_ID)));
        int timestamp = Integer.parseInt(String.valueOf(paramMap.get(TIMESTAMP)));
        String nonceStr = String.valueOf(paramMap.get(NONCE_STR));
        String imageBase64Str = String.valueOf(paramMap.get(IMAGE));
        int mode = Integer.parseInt(String.valueOf(paramMap.get(MODE)));

        String clientSignature = String.valueOf(paramMap.get(SIGN));

        //服务端再生成一个签名
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put(APP_ID, appId);
        paramsMap.put(TIMESTAMP, timestamp);
        paramsMap.put(NONCE_STR, nonceStr);
        paramsMap.put(IMAGE, imageBase64Str);
        paramsMap.put(MODE, mode);

        String appKey = "HxbQwzb3CsnMbTjU";

        String sortedDictStr = new StringsSortByDict().sortedDictStr(paramsMap);
        sortedDictStr = sortedDictStr + "&" + APP_KEY + "=" + appKey;
        System.out.println("sortedDictStr:");
        System.out.println(sortedDictStr);

        String serverNewSignature = DigestUtils.md5Hex(sortedDictStr).toUpperCase();
        System.out.println("serverNewSignature:");
        System.out.println(serverNewSignature);

        //然后和传过来的签名做比较
        if(clientSignature.equalsIgnoreCase(serverNewSignature)) {
            validateSuccess = true;
        }

        return validateSuccess;
    }


}

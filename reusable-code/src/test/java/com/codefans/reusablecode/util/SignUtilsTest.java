package com.codefans.reusablecode.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mpif
 * @date: 2018-06-07 10:08
 */
public class SignUtilsTest {


    @Test
    public void generateSignatureTest() {

//        SignUtils.generateSignature();

        try {
            String encodeStr = URLEncoder.encode("腾讯", "UTF-8");
            System.out.println("[腾讯]的UTF-8格式的url编码为：");
            System.out.println(encodeStr);
//            UTF-8字符编码下的url编码为：
//            %E8%85%BE%E8%AE%AF

            encodeStr = URLEncoder.encode("腾讯", "GBK");
            System.out.println("[腾讯]的GBK格式的url编码为：");
            System.out.println(encodeStr);
//            GBK字符编码下的url编码为：
//            %CC%DA%D1%B6

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void validateSignatureTest() {

        int appId = 1106879537;
        int timestamp = 1528339851;
        String nonceStr = "910d91191ad945f28d8dd860a6979ccd";
        String imageBase64Str =
"%2F9j%2F4AAQSkZJRgABAQAAAQABAAD%2F2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL%2F2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL%2FwAARCAE5AfQDASIAAhEBAxEB%2F8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL%2F8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4%2BTl5ufo6erx8vP09fb3%2BPn6%2F8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL%2F8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3%2BPn6%2F9oADAMBAAIRAxEAPwD2eNcCnsvy1OsXFDx8UmKxk3JxVHJzWjdR1n7DvoGkWISSKuxCq9vH0rRhj9qoLEiLxUgQ1IkeBUmKQEQjoEVS0tAEOyjy%2FapaKAIvKo8qpaWgLkHlUnl1YpKVh3KzR8VXkSr5WoXSs5IDKmQ4NZ00Z71uSx8VQnj61zziZyRz9yuKyplOa27lPmrOljB7VCRBjSqTUYhJ5q%2FLEc9KVYeOlaRQzP8ALIrhvE1q32hevXpXo7QiuT8UwYinlUHgcVtEqJ5nJkeYzHkmqePmGatM5YdvlNVwMsc1sWOiADbyM4FJK5eQk09FOzI7nio5EwDnrQBEpIBPejqPqaVhhaVRzx0FAFuyby5xgc%2BtT3074CnpUFgpa4zj3qS9YEkHrQBTDbgMcCnsMED86dFH0OKJ8A5%2FCkMryNlto6CpIwUTIHJqNBukH1q9sGSew4FAIjCEgD8TV63izzio449xArQiUKtRJmkUIYzjA4qvJCXOMVoBeMmmrGTlvU8VHMW43KcVv%2B8AxwKtiLFPhTBY1KRSlIqMUQ7KaUqYikIqblWICvNNK1ORTGFNMTRXZap3MeVNX2HFV5VyKuLM5IwpVxVVhya0bqMAnFUSK2RiyMdOKcWIAYdRSfdpcg5BoEOVyV611fht2FpKD0PIFch0PtXYaACbPPamhMiv161kuK3L9etYsg5oYkV2qI1K1RGkUNNJSmkoAt2n%2BqP%2B9RRaf6o%2F71FAH2yFoZeDTxQaAMu6TrWaU%2Beti5XOaz2T5qQ0SW61owrmqUC1oxDimDJKKWigQlFLRQAlFLRQAlLRRQAUlLRQAnamsvFPpDSYFWRetZ9wnWtSQcVQnGc1jOJMjDuI8k1QaE5ramTrVCVcVlYzMuSFc0ioBwRUsvU1EGx1qkgGSRg9K53xFbb9NuP93iujY1ma1HvsXXGd3HFaIpM8NeLYr%2FWq%2B371bF9bCK4nj%2Futis%2FZwc9K1RoNRQsO7uBVTGSfWr4QtDgDtUDx7QoA68k0wKj5LACpOiACk2Fn4p7csB6CgC1Ygq2fbrVeZvMmx71PbkrGc1Hbrvdmx0oGSgbB9BVZ%2FnkC%2FiasynHHeokj4Lkc1I2RQriUn0q4y5VF7k5qK2jLMc9quLHmcDsopNjSJYY%2BSauxp8opsEJIFXo4c1lKRvFEWzkUY5PtVvyaY0eO1Rc0sVwNoNB4p5Qim7aQDKMU%2FZQVoEREYppFSsKjPFMGRMKgkWrLDNMK5NWmQ0Y93FnJFZciYNdHcQZGRWLcxkMeK2i7mElYoHikIqRhimEcCqIGjmu28N5OmkHt0ri0Xmu98PIU00oyEY5DeopoTK98vWsOUYJror5etYE4%2BY0MSKbComFTuKhakUR0lONJQBatP9Uf96ii1H7o%2FwC9RQB9t0UUUAVLgcGqBHzVoz1QI5pDRNAKvR9KpwirkY4pgySiiigQUUUUAFFFFABRRSUALRSUUALSUUjHAoAilPFUZj1qzK1UpjWUiJMquaz5zjNWpW61QuH4NZGbZQmbk1XLUspyxqOmguOPrVa5AkXaegNTO%2BBVWRwSPSqTHc848QWG3UJyicHnpXNrH%2B824r1DU7VJxMQoyVHNedSxtDfMhHIatEzWLuV7dMo4I6A1WuRtmI6cVtwWn7541H3iR%2BXNY9wMzzOR0bFNbmjWhShQEk46Uiplmb8BUqMEhLdyc01ZAE5HNMkVxhdo9Klto9kWe9RuwVAfWrMRBQc0MaEEO5s45NPKAE4HCircUYwW46Uqwb8Z6E1FzRRKtpDgEnvU9rH5js3qcVaEHlwkgVZ0%2B04GR3rNyNIwJoYOBgVcSEVOkIQc1KqDFZ3NbEJjG2oXQYq252Z9KoTXKKTkiiwm7ETR80woKgm1GNOhzVF9UOeBxVKDZDmkahAxUbMo7isdtQdjgNUbXEjdzVKmR7VGuWBph61mJOw6mrcU4ZetDhYammTEc5puM08YIoqSyNhxWdd24ZSQK1COKglTKmriyJI5aVcEjFREelW7xNszVVPFbnMOhXdIo6kmvR9LtxDpiBWJUjIB7VwumW4kuFdh8qmu5srrKCLGF7UuZJ2K5G1dFS9XrWBcLhzXS3q9a526Hz1TMkZ7ioWFWHFQsKRSISKQ0802gZZtf9Wf96iltf8AVn%2FeooA%2B2aSl7UnagCtP3qketXZqpkc0hosQiri9KqREYq2p4pgx1JRRQIKKM0ZFAC0lJuFJuoAfSZphkFMMlArk2QDSFqgMlNMnvRoFycvUbvUJkqJpalsnmFkeqUr0%2BSX3qlLLWMmZyZFIeTVG4bg1NLKMVnTy1jfUgrufmqMnFRyS81A9wB3rRModLJgHmqUknNK8uagOSabYCkbs8Z4xXD%2BINOms9UV5ImRHOQSOtemaHHCb9XnwVXkA%2BtYfxLuY7p4TEo2xHqKcJanTSptxcjnbSzC3dxKf4VJHvxz%2FADrjbht8czY5Lk16RLb%2BVYCUDh7UOT%2BB%2FwAa4YW6jTXfHztJj8BWkWayRglWEPGcHikKEEAg5NbjW2TFHjsXb6dqaLYNI7ADAO2q5iOUxG3ZANTKGzwTitZ7RPtEalRV6PT4eeMfUUnNDVNsyYGk24yTmta1RiQGB6VLFpsZkwuOtalvZBcc1EmmaRi0yv8AZ94VAO%2FNXoLYJgAVPDCOnv1qfaAwrJnRFEflgjB5prjYvy9qlY4FRPIMe9IbM26Z2XGec1j3UbknrW8%2BCxyKgaFG6iqTsYyVzl5LZ85PWm%2FZJHHCmulNtGf4aXyQOigVftCPZHOx6ZMTkjFWl0vHLNW0yYFREYqXUbKVNIzBYR9CKQWSKeBitFhx0pmKXOyuRFZYygxnNBHNWCtRkUrjsRYpki8dKlxTWHy00Jo5nUV2znFVYojI4A5q5qnFxirej2e4GRl4610XsjmSvKxatbZLaIuRx6etXLa4b7QDnA9KYVaRSMcdqgtyRMFPUVlfqdCVtDevBlc1zt2MNXRzjMC%2F7ornr0cmug4upmvUDCrD1A1IaIiKaaeaYaBlm1%2F1Z%2F3qKLX%2FAFZ%2F3qKAPtmmk4FJvFRSyYHWgCGZ6qF%2BaWaXrVQy%2FNSFc0I5AKspJxWUktWFmpiuaPmUeZVHzvejzqYuYu7%2FAHpDIKpef700z%2B9AuYumUUwze9UjNTfOqWxcxdMtNMvvVMzUnm1m5hzFsy1GZaqmWonmpc6JbLTzVXe4x3qs0pNRHJpORDkSSXHvVOWfrzTnjyOtVZExWMmIjlmzVKWTNTS55qlLk5qLjRWmaq5PNTOKjIxVJlkZGaUClIpVHNDYEkeVRiDjiuO1q9e4eS3fnB4rswv7s1x%2BoWZfVXwMgjNZXfMmj2sucXQnFnUTRCXwlE5GP9GXB%2FCuDWDdpowMkksf0r0hbcyeDbdccbNv9K5CW2S0tXjYY2y859N2a60zmlEzDaf8fL4I2bIh%2BVRJBBbw%2FvXG4sSVHJrS3%2F8AEnD5G%2BadpCTWJOkszxQoRFE3V26sfWq3J2FluLdbgSLGcL6mp01KzZORg57EU6%2F8PiPQ3lgJeTqWz2rlHsJ4QCFBDA4JqvZpke0aOwimgY7o5Bk9jV6B9q%2FNXD6Sk7XLRh%2BAMkHoK6Cwvz5hgc5I7Z%2FlWcoW2NYzvudHHIATU28H2rPSVRyDVxTkCs2bxGStVGSTaauSggZrMnfk1I2OD5PWnjBrOEvz1HeXpgi4OGbgVSVzJuxfluoYerZYdhWZP4gjSTYAM1c0W1t70lriUZxwKwdR0yOO6mxJht%2FAzW0aa6mEqj6FlvEJzt4B9CKni1becMoP0rnHtG85fm3nPWtS6jVkQIuGA5YU3GIozkbsU0Vx904b0NKyYPNY9o7gjePmHetQTkrg9aykrM2TbFeojTixNMzUljTTG6U9qbjINNCZh38HmXyKB1Na6ultCkaAbiOfao5Ih54cjkDg1Xy287utat3RjFWZrxujLgqKpPFtvvlH3hUccpBHPFaVhB9pv4s8gDJqYrWxo3ZXL1wmIgPQVzt8OtdXdx8Guav4%2BtdRw9TEeoWq08dV3Q0iiE0w1IVqMg0hli1%2F1R%2F3qKLUHyz%2FAL1FAH2T53vUU03FU1nGOtRyz8GgxchZZfeqhl5qKabnrVUz89aQuY01l96lWb3rJFyB3oN370yWzXNwB3phugO9Yz3vvUL33vRcVzca896jN371gm9yetNN4fWpbA3vtnvS%2Fas96wUuCT1q3E%2BRWE5C1NQTk07zSaqRkmpwOK53IokLk0wmnYprChSExuaTNLTTV8whGOBVaQ1I5qFzUNjsVpRnNU5EAzVx6ryiouNGfIKhK5qeTqajq0yiPbT0TFPC5qZEqWwEROCKxb2ER3YYjrXQqMGs7WIMxiQDpUxep24GfLPl7mzp0Sz%2BGYfRXP6GuR1u0aRHUD77Dkeldj4abzfDkqZ5WQj86zb20DOOOFJ%2FlW%2FNqdrjujkUtiIIYtoIwcA%2B9S%2F2QtzZgMoDgnn1rRuIQkq4%2Fh6VJaSgDY%2FTOatSM3Gxzy2d%2FbF4I3OwqQUPIxWPc6PPJKfNc4xgADGBXohCF93BBFZ17FGSCMcVopEuCe5wiaSLcEJuGevPWnxaYEkWUZDA5BzXRzonYVSdSTUOTHyojPyqvqTWjEzbRVNIjIyitJI%2BAMVmzSKI5gSmax7jPNbs6FYiKxrhOtJjMsZ8yntbxzHe4yRwKcUxJmplXIzVJmbiRxWqg%2FJxTn04SNukTcfXNWoxg1ZV%2BORVqTJcUzJGlop3LEAaHstvXmtZn46VWkyw4ochJGb5G05pypirJjOeaTZUORaRFigipduKaRUjsQkU3FSGgCtESyF14qpc4Qr7ir0grOuCLmVVQ8L1NWtzNhF8x4FdN4eQNLM391QKwF226YPLHoK6rw1blbGSVurtWlNXkTVdoD7tetc5fr1rqLwda5u%2FHJrZnIjAcVXcValGGNVnpDIGFREVM1RmkMnth%2B7P%2B9RRbf6s%2FwC9RQM%2Bm1vB60yW8GOtYH2zHemNek96DlNWW6znmqzXHPWs1rrPeozcH1pDsaZugO9IbknoazBITUgagLF0yk96TOarhqerUx2J1RTUixKahU1OpPFQ0OxYjgU96uxQqBVOIkGrqHgVhUQWLKKB0qUdKgQ1JnFccgsS5pjGmF6jZyc0lcCTNNNCZNOC5rUmxAy1C61e8vNNMNRIdjNZDmoJU4rTeLFVpk4qLgY0iYJqIrzV6VMk1B5dWmUQgYqePpTSmKUHFU1cLD93NOmjE9qyEc4qAtzUscmDz0NCiaU3yyTDwhKyPf2b9sOP5VfvEVcnbzmqumw%2BRrayj7sqFTV2%2FGD9Ks9e6bujmLpeSetUwcHNaFz948cVRlXFNDaEaYjoaqzSk9TQ5OetV25qrmdiNyDQsWeTzT1TNPCkMBjNILBFCAxbFWgvNIiYqYLzQUiCdP3eaxLjqa6G5x5dYdyvJ4psTM4qCalQdqYeGxUi%2BlIkcBipBTVGaftxQA7qKjZaeMgdKKBWItnNIygVISMU1jxSGV34NRHpUsnNQmhIGNPNOApAKkAGK0Rm2VLnPltjriqtralcGrN1IVbAFQI0jnAyBVdBLcekBnu8L3OBXoFtbC1sY4R2Xmub8PWPm3YkYfKnP4110p%2BWuiktLnNXlryoxr0da5q%2F710t6etczfnk1bMEYUv3jVVzViY4Y1Uc0ihjGozTiaYTSGi1bH92f96im2x%2Fdn%2FeooGexGY%2BtN8w1AGp6kUjFRJdxNOUEmmripkxQUoj0XFSCmggCjd70IfKSipFqFTnpVhEJqrCsSKKsRrTI4yTVtISBUyQ7D4xgVYQ4FNSM46VPHHXNMLEiHipMZFCR8VJtIFcskIhYUzHNTEUhWkkJiJUy4zUIFOU81pYkshRSlRio1NP6ioaKsQSqKpSpwavSVXl6VKiTYzJI%2FaqzgLV6WqUoya0URogJFIRzSE80o6VoolINtBHFOpccU%2BUtIntm3hDnlGBq9qQyTjvzWSrmJsitksLqwSQdQMH8KbidtGpokzm7hfnIqjKvBrWuo8N0rOmXA5zWdjqMyRRntUWzOauMlM2AiqFYrqmKkC80rLtFMWTc20UxFqNOal2U63XI5p5GDUlJFW5TEeaw7nvW3dyZQr2rEus80yWZzHk4pUagIWJxTJUZPmoILqVIq8VVtpQ4waurjFADduaYRxUx61G44oGREVE%2BRUx61A%2FSgRCxphGTT%2B9JjvTRLYAYFA6U4enamk4VmxnArRGciuYfMkNaNjpDzuOML61y1vrDpdMzLzu79q7DTdY3RhvzFbRpJ6sxlXaVkdFa2sdlCI0H1NPmfK1DFcLPGHU5p0hytbI5Xq7syr1s5rmr89a6O871zd%2F3pMaOfuCfMNVWJqzcffqs1ItDCaaacaaRSGWbU%2Fuz%2FvUUltxGf8AeooA9UU1MpqFRUyipEkTIamBqJFqcJxSKSELU4HigrT44yTVIGixbRFsE1oxw5HSobVMcVpRKBVkjobbpV1bfimxEA1c3riokxEQhwKcqAGnGRcdaYJVJrmmImAoJ4pAwxTWbisXElsax5ppPFNY800mmkA8txTQ2DTC1M3c1aiCLivmpM8VSWSpfN4ocBj3bmq0zcUrSVFI%2FFSoklWQ9arSDIqaRuahZs1aiMrMvNLipdmacI%2BKqxSIQKeBViCynuDiGFn%2Bgq0dGvo13NbPj25oSLVjKkFaOkNut5oiehDCqk0ZQkMCD6GptJbbf7OzqRT6GsHaVyK9TGcVkzKTXRXcXzHisaeLDdKyaO6LMxkO7timFe1W3UYPeqspxkikUU7hsDFT6da%2BYjOeearuN7VoaZeQ2hMc5wrdD707C6lhYtgPFRyA1P8Ab7RpvLyRnv2p0saBdwIIoUR8xjXJ61lTck1rXIwe1Z0iryScCiwpMrxxrjpTLmMFOBTlvbdX2gM56cCrNwsXkb92BjvRYhNGFF8spHoa14uVFZ9sollZx0zxWgh20ASEDmon7jNSs2RUL8mkMhPSoX4qVzioCc0EjGNJ%2BFDCg9KolhnirOnosl3Gr%2FdJqqKS3udusQwjqF3GtYLUyqbFXxh4bbTLhb6Ff3Eh5x2NVNJuQw2Zr2a20y31%2FRZLG4UHcuAa8b1HRbvwz4geyuVIXOY27MK6rWZyG1p2rG3maF24966FbxJYxivONTkaG5R1OK6Lw%2FdPcbQzZFC7EtGxd5IJxXN33JNdjexItqTntXBajOY5mAPFElYaMu5Hz1WYVNJMsh9DURFQURmmmnmmmgZPbD92f96ilth%2B7P1ooA9XWOp0SpFjqVUqBiJHVhY6VEqZRigoi8rNTwxjjilwKfEQDTQmW4UxiriVVjap1erbM2XI1709uBUUb8U93BFZyAjkbApiMc0yV8VEknNZNAaiN8vWlY5qsj8VMDmo5TNgc00mnEZqJ80KIxjvioxKM4pkxIFVd53VaRSL4lFSCUEVm%2BYQakWU%2BtVYGXsg1FIeKW2SW5bZEpZvQVfTQL6XgqqD1JpqJJgzHmo1610w8JTOw8y4UD2FaVt4YsYAC4aVv9o0%2BUdmcvYaZc37Yij%2BXux6V09l4atoAGn%2FAHr%2BnatmKFIUCRoFUdABUlUoopIjjiSJQsaBQOwFPxmlqrNc%2BVnIqkr6IZn61psFxCXKAP6iuJdGs7yN%2BysDmt%2FV%2FEkaExE81y8199oY89aqVHS%2FUSlY3NQQBywPB5rFn681qxSG506GQ9QNh%2FCs%2B6QjPFcck07HpQleNzKkXGaozdDir1weT6ms%2BVuagu5Ai1FdAbKlZiBxUUgLIRVIhyMljJGx2MVFWIdWuoUwx3r6GkZc54qMxLtJxTRHMyc6ushwUIJrKvriaZsfdT0olAWQYNOnAdQaAbZQE0kf3Dg0vnSS%2FwCsckelOMfOaaFw1Ak2i%2FZsAtXc56VlxMVqysh6VLRXMXd3Y0x24pockUxj1qSr3I3Oaj604nNIBimIQimkU8%2B1NNNCY0Djmsywk3%2BKc%2B2BWjM4SIn0FZGjnf4jHrit6ZhUPaPD85heM5rW8ZeFLbxNoxlVQt1CN0bjrn0rntPby1jNdlpeoBlMTHqMV2JXRyPc%2BaNat3WXYww6NtYe4rV8PnyE8x%2BABU3jaEab4rvInGEkbev41zFzq7iMxRcA96z2ZW50Wp%2BLEMvkocgcZrB1GXzAJM%2FerF5dwe%2Ba07z5baMd8Um7jsUSWJ4rQs7Z5hjBqC0i3sDiut0qzVVDEURVwbOfubBohkjFUGGK6%2FVosqdormZIG3HihoSY22%2F1Z%2F3qKsW9swjP1opWHc9jCU4DmpzGMUbKzKGLTs04JSlKAG7qcr0zy%2BamSPmmhE8bEgVYUP6VHEuMVdhALAU7ksIkk9KseS7djVuGMnAArQhtScZFFriMJrFmpYrAg%2Fdrplsx6U42gHYU%2BVC1MOO1wOlTrbe1aRt%2FajyCO1JomzKS2o7ig2SntWgsZ9KcYmx0qbDsYdxYrzxWc1kA3SumlhcjpWfNbyjoKLDRitaHOcUw2zDtWt5UvoPypRDKxACg59qWoNmn4btljtWkI%2BdjitzFU9Nt2trUK4wxOSKu1aLWwUUUUxhRRRQAVia3cLbwSMTjAraNeafEHWvs8ckStz061cN7ktnFanqhuL5yGzzxVyykIj3O1crZlpZjI3c1rS3Xlw4zWifck6zQtVjlu5bBmGXG5PqK1Z0ySMc14%2BdZlstThu4mO6Jw3XrXr8F3FqFjb30JBjlUN9K5ayu7nZh5aWMa7TbkVlyKQcmt6%2FTknoD3rIlTIOOornZ0mbI2GqeKEy4xWbeStGxxVEa%2FqNizkQpJEfwIprUzZuS2eOuBVKWFRms9PEbXHM0Tp9DmnHU7VzjzCD7iqUStCKazLtkNSCABcbuRU3nRsDtkB%2FGqU06ISS%2F60uUd0P8AK55oMPHSqT6lGoGGZvoKibVZT92Nj9aORkNo0%2FK9qQFEPLAGseTULxhhSFz%2BNRRWks7%2BZNIzN7mny9yL9jpYznoaRs81Wsy8YCE5FXcAgnHNZstFbnJpccU5hzSGkMb39qRhTuhqKRqpCZTvXwm31qp4cTfr7N6CnXMm92PYdKt%2BDofM1CWXH8WK3gc8z0mJtix1qWMreeGBrHbjYK1tN%2B%2Bc11wOeRwfxcsGMltqKjj7rGvLHAZc19EeNdI%2FtXwZdnbl4gWH4V86ZxlT2rOorSHHYdCB5yD3rQ1BS2wDpWdCf3oPpWzFJFKoDdRUoZJpdoSRkV0yzJbxBc84rFhuViTCDmneY8pyc1aJZanufMJ4zVdYw7fdp6rmrkMAxkigCGG2JQ4XjNFaEU0calc9DRRZAejFaAlOzSg1gaCBBSlBTqcOtAEYiHpUix47U8AU9RTQColXLZMuKrqKvWYy1BNjTtwBjitSBwB0rNT5eoqX7SEFNAawkHYCgtx2rEa%2BY8JyfSpBPdEf6r9RVWEamR7UvBrL865H%2FLI%2FmKniuJGOGUihoVy%2BqipAvFV0kNTq2VpIYFKieP8Azips5pChboaLAVfKJ6D9Ksw24T5iBn6U9IwvXk1JRYaQUUUUDCkZtopaqXcm0YBppXAsK4bpT6oQSfMDmr2eM0NWEmQ3UvlQM3fFeB%2BNb5rrV5Iy2fmr3PVZRHYOxPYmvnbVZvtWu3LjkbyBWkdiZbiW4CIKgvrnCkZqV3CR1iXsxJPNFxIpXMm5zXc%2FDzxB5TNo9y%2FyP80JJ6H0rz13y3NT2szQzJJGxV1OQR2rNq5rGTi7o93u03xlQO3FZDpwVPWl8P62ms6YrOQLiMYdf61Znj54rlmrHdCSaujmL6zZmJGM9s1FHZBk2OM54rdnjHeq5iwOKi9irXMF9IWGQ5X5TUUmmREZ2CukjdGXZKMjpVWe0AYlGGOtaJjTS3Obk02IDAyKgGmwjO4k1vSw88gVWe3U5waLjfKZMtvAg%2BROnrVZlDcBea15LU55xiovJReeM0myW4lCO0ycsOKtRRLnAHFTfe4AxT1QAVDZIoiCgGnb%2BMUFuKjJ4pAIxpg5NKSR1phfBpiFc4FUp5MKR3NTySAAkms6V9xJNUkS2Vbl9qHFdP4Jtttv5hHLHNclckyMEHUnFekeG7XyLFBjHFdEEc8zVY5mA9K17MiPn1rHPMxNaNsxb5e9dMTBnTeWJ%2FDd6pGQY2%2FlXybefJezIO0jD9a%2BslYxeG7kn%2Fnk38q%2BTLtg1%2FcH1kb%2BdTU3CIsfQmrEU6oRk1XHEf1phrIs24LyLua0YbyD%2B%2BK5UHBpdxHencVjsEuod2d4P41K%2BojGFZfzrixIw%2FiP505ZHJHzGnzCsdrY2F3qMTzxI7KHK5A9h%2FjRXr%2Fwu8PA%2BBrSaVF3Tu0nI7dP6UV0L2a3M7y6FTdShqiBpwrjOglDGnBqjFPAosBMhzUyioE4qyhBoFckRa0bRcDNVIxmtGBQEpiuSs5AFVZ5jjrUkzjNUJmy1FhXJ7dw83QnFaCPnsfzrJgHJIrQhBpibLo5HQ%2FnSqNp70xAcVJjis5SZHNcmEmKtwuSgqgvFaVrGwjBYY9qcWOJMqmpKKKs0CiiigAooooAKx9SmCE1sVy3iCUrMF9TWlNXZMjUtG3qDWizYhz7VkaO2%2BFee1as3Fu30omveBbHIeN9YWw0SZt2DtxXz5HqeZXcnlmJrvPitrTEfZEbqcEV5MDiiWmgjoJdSDjANUJpg%2FeqOTjg01nYd6m4yRuTU0C5YVWR%2BeatxSovNAzf0jUZNLuUnjPsy%2Bor020uor%2B0S4hYFWH5V4u14o4rf8L%2BKF0y7FvO3%2BjynHX7p9aipG6NaU%2BV2Z6LcRZIIxiq7occVccrJEHVgVI4Iqs461ySR2xZRkXFVJZGHQ1fmU1QlTrxSTKauUZmJPWqzsQevNTyqaqOrZ6mquZNCM7HvSBSeaVEOeam2ErSbBIYUAGab0JqXBHUdKjakUMzTCTzkUOcEYqNm6UEtgWqJzihm5qCSTtVJCuRzSZ47VTkbipHOTVeU8GrSIY7TIDd6rGuMhTk16pZRCG2A9BXEeDrEySyXBHGcCu%2BcbIcV0QRzyZXU%2FvDWnZ%2F64cVmR%2FerT087phW8TJnSapKIPC12f8Api38q%2BSJPmuZG9XJ%2FWvqvxTL5Hgu%2FfH%2FACwb%2BVfKi8sfc1FT4hxLDDEa1HT34AFRGsyhaXPFNzRQA6nxjLqPU1GKtabEZ9Stoh%2FHIq%2FmacdWJ7H2B4PtfsvhDSocY22yfyoqWC8SztYbdRxHGq%2FkKK0cZNkpqx54BTgKBUiisi7gAakVaFFSqtArgq1KoxSqtTKlIVx0ZIrRjclBVJE5q9GMKKBXI5Bk1VeElq0CuTSiHJqkK5WgiIFXIlIxTkjAHSpkTFVYhsVRUoFIF5qdE5rGSHFE9pbDAkcfQVdpFGFA7U6qSNUgooopjCiiigAooooAK4%2FxG4%2B2qveuvJwM1wuvzB9WOOgFbUdyJvQ2NBb5Bk1u3H%2Bob6Vy%2Biy4OPeuivpfL0%2BWT0QmioveEnofNPxHkMniCQZyFris10%2FjCY3GqTyk9WNcqp%2BaoluUticUjCnL0oapAgbpUJZh0JqdqiIzQMiLsT1NSWQaXULdCeGkUfrTSnerOlJu1i0X1lX%2BdIHsfSLaAYdCt721BKbAZE9PcViSjBznIPNenaKg%2Fsa3UjjYAR%2BFch4l0JtOmNzAubWQ8j%2B4fT6VFWN9jejUskmcxJyvHWqU%2FQ1ekAUDnoapTAYJFc3KdakZ0i5Bqs0eRV1yCajOMHjvTEypjHUdKQ5HU1OQCM96hcYFBNxjHPB6VA5HapHOO%2Faq7HrU2FcaW61CzHNOc4FQO%2FGB1qkhXuI7Y471CRmlNBPFMZC4qpMD0HerbmpNNtDe6pBCBkFsn6VcFqZy2O48MWH2bTogRhiMmta6JGBVq2gEMIAHQVXnXc9daRzNleLOa2dKiBlBrPjiHFbemQ7TmtIkMb4%2BkMPgTUD28oivl2Llq%2BlfiZMU8CXoz1TFfNkXWsqnxDjsPk61HT361GagoCaTNN70tADga2PDAD%2BJdPB6CdD%2BRrG71teFQT4ksvaQGqh8SFLY%2BnIHaePzDnk0UyyJS1Qe1FdljK5hBKkVKmEdSCP2riKuQqlTKtSLHUix0CuNVeKkVaeqVIEpCuIg5q5EuQKgVasx9KaC5OsWalWCiNs1aQcVSAhEOO1SLCanGBShwKY7DVhOasJDg0sR39OlThcVDLSFHSiiikUFFFFABRRRQAUUUZoAjnbbExA7V57qCyy38khB5NegzYMZFYstnGTnbzW9KSW5lMxtJZkk5FaviC98nw%2FcN0OzApI7VEkyBWP4xlK6SsIP3jVSsxdDwXxFzMx71zSnmun8SLiRq5QHmsJbmqL6YxSPUcTcUsjYpCGEZNIQKaH5p%2FUUARNV3QI%2FM8QWK%2F8ATUVUcVreEovO8VWC%2FwDTShbg9j630pdumQD%2FAGRVi4gjuIHilUMjDDKe9Msl22cQ9FFWD0oluOOx4%2Fr1g2m3siLl4c8E9R9axZJgV24612%2FiuL9%2FJx1rybVb6706bcmHjzyGFZTh2OmE9NTaKk5OeKhbvWba60k6DKFT7GrqTrIcg1lY1uOPHpUTHNDvt7Gq7ScnA60hWYPjmq0jge1EkpHAqs7bqA5RHfNMxS4pccUhpEZqJuKlYVC5poGROa67wPphdpL51%2F2U%2FrXLW1rJe3cdvECWc44r1%2FSNPSwsYoEGAi4ropR6nPUl0JZBtjrOY5f2rRu87cCqWyt0Yj4x0rYsjwKylXAFatkDxVolmD8VpQngWcdyVFfPCV738X5lHhDyx%2FFIorwRelYz%2BIqOwN1qInmnt60w1IxuaXNJRQA7OK3%2FAAZG8%2FimxjQFmaQYFc%2Fmuh8EXBtfFthKp5EnFXT%2BJEy2PqyyMVpaJDtV2A%2BYkd6Kz7WXdbq2etFbuOpncywtSqtIBUgFc5NxVWpAtNAqQCkAoFOAoHFKKAHCpFbFR5qKVyqnFNDRdW4C96uRXAZeCK5Ke8de9WLS%2FYp96rRpy6HVCYHuKPOXPWudN%2By96rvqhEg%2Bb9atRuQzubaRPLAzzVgHNc3pt4ZVHNdBCcqKynGxpFktFFFQUFFFFABRRUEsoUdaLCuTFh61GW5qm1yM9aVZwx61VhXLL%2FMKhMOakVsipRii9gtcoNBtPSuQ8X%2FN8vZRXdSAVwnik58w0cwcp4n4mX52rjf4jXX%2BKJPnYVxpPNJlFqJqWVqhjPNSP0pARg81MjVX709TigCV63%2FAibvF9l7NXPZzXT%2FD4Z8YWn1prcmWx9X2%2FFvH%2FuipT0qOD%2FUJ%2FuipD0oe40cR4qj%2FAHp4rynXbUSxuuOa9g8UISwPrXmWrQ4LHHrSkjSDPN43a2uCh6ZrZt7o4HNZ%2BsWxRzIoqvaXOVFYyj1NYStodKLkMuDUbyDtWekuak3Z71nY2uPY7jzSUg5opAGOaCKWpYLa4u32W8LyN6KM00m9hN2Kj1EI3lkVEUszHAArp4fBWrTJvkEcIP8AfbmtfSvD9rok4uZ5vtE4%2B6AvAraFKT3MpVV0JvCvhn7An2idc3DjnI%2B6PSut2bRisOPX5LdmaaNWh9B1FbVtdW%2Bo24ntpA6%2BncfWulK2xzN3K06knpVfYd1aDpnOah8v5qaERKnStS2wqgVTC%2FN0q9CmMVaJZ5%2F8Z5lXQbWNerS14mBxmvXvjSWW309SfvOTj8K8gzxWE%2FiZaGN1pnWnNUZNSMKKTNGaAFBrY8NSCLxHYO3QTLn86xs1c0uUw6lbSf3ZFP61UXqhPY%2BqbaUeQu0jGKKoWU3mWkbA9QKK6m1cyLoFPFNFKK5SSQdKeDUa9KeBQA4HNKKQUtIBwpsyZQ04U9xuShDOdu4%2BTSWQ6ir11Fyaq2y7XNHU3jqh0oIFZsqsZhzWpKeKqKgMy%2FWtosza1N%2FQ0YYya7GAYjFc9pEGApFdKowoFKq9RxFooorEsKKKKAGscCs65BY8VpMMiovJBNNMTMtbdjViK2Iq%2BIwO1OAAouCRAsWBUoXAp9FIZXmO1Ca878Uy4jevQ7z5YHPtXlPi%2B6wjjNIDx%2FxLLulf61zGea19dm3zt9axc0wJ0PNSE1WVsVKG4oAWlBpuaTdQA%2FdXW%2FDg58YWtcdmuv8Ahrk%2BMrYU47ky2PrKH%2FUp9BTzTIeIk%2Bgp9D3KRg6%2FamdQR2rzTXLfyy4r0XxH4hsNN2W88g81zgKK4vxDC0irKqnY4yDTew4vU86vLUToykc1yLxta3LRnjB4r0LyGM2AM81zvibS2icXCr9aztoaMzIpeBVhZKzomqyrVm0aRkXRJ71NCrTyrGgJZjgAVRDV1XgOAT%2BIkduREjPjFKMbuxUpWVy%2Fpvgm4k2y6i4gi6lP4jXVwyWWlxC3sYo07Z7moPEss0eh3k0MhEojJVvSvJfAGo3d%2FwCK1%2B13MkuFOAzZGa64xjF2RySk5bnrtxNM%2FwA7E49KoSgscmtG5%2F1Rqltzk1bZKMbVn2Q7R1NZVhqtxpV2s0DkD%2BJexFXNVffcEDotYUzYas76lWPVdO1K31e0E8JAcfeTuDU4Hzc15FF4nl8NzRXMfzIzYdPUV6ppOrWeuWEd3aSBlYcjPKmtE7ktF0gZqxGTlcdM1Bt5ANTxrgqferRJ5T8aXJm05Sc%2FeNeTmvVfjTt%2B26cAOdrf0ryluBXPLctETGmU5qYakYUUUlAC0%2BFtsyN6EGo6UHkU0B9GaVqUa6TaEt96JT%2BlFcdoN39q0O0YMfkjCHn0ore5FkesinU0dacKxMh61IKjBp26kA8UtNBpRQMdUqcjFQ1JG3akBBcx96oBNrk1rTDdU40hxa7yvzNyfajc1gznJTUSD96p96uXVu0bEEVVUYcVSdimjs9HwY0rcrn9Fb92tdAOlEncSCiiipGFFFFABRRRQAUUUUAFFFFAFHVHCWbE%2BleIeNL4AuAa9g8R3IisZOegr538X6hvkfnvTSEcNqM3mXB5qnmlkfdITTM0hjs04NUeeaM0AS7qM1HmjNAEma7T4XDd4zt%2FpXEA13%2FwiiE3jSPPZacdyZbH1RH%2FAKtfoKivLgW1tJKeAoJqYcKAPSuN%2BIeux6Tokke8eZIMAZoW4zy%2FVLyTWPEs87MWVWwvtXf6DOl5p%2F2S6AcAYGa878PwNMPNYfM5ya663325DRkg1qloI1G8OWccrtDICf7prmvEOitJbuhTtxWv9qkEpkBO81ZS5NwuyZQwqeW6LTPDLyyksrgo6kDPBpqmvV%2FEvhFb2yae2HzAZxXlksL28zRSKVZTgg1hOLW5rFpgGrvvhhB5moXsx6JFt%2FEmvPxXonwyyV1FR12qaKfxDqfCdD4ojUaDfBjtXym5%2FCvFfh3keMIQDwc1674zeR%2FDF8nOfLNeS%2FDVN3iyM%2BiGt38SOboe23IzEaz5n8qBm9q1blcW1YWov8oiH41TBGBdAtub1rDuWw5roblcRmuavOJDWbLRzHiO43SRRA9OTTvDHiq88N3yyQuWgY%2FPGehrO1WTzL9%2FbiqJpJ2Ez6d0LXrLX9PS4tpAcjkd1NaaF1kAbp2r5p8O%2BJLzw7erNbuTGT86HoRX0B4Y8T2PiGzjkRwH%2FiXPKmt4yuQ0eefGdt2pafkY%2BVv6V5W5zXq3xqMR1HT%2FACznCtn9K8nc4rGW7KRGTzSUGkqRhRRRQAZoFJQKAPQfCd8qaIEY8rIw%2FlRXL6XcvDasoJA3k%2FoKK1U9CeU%2BmwDmngGrQt%2Fan%2FZ%2FaszIqAGnYNWxb%2B1PFsfSgCkM0uaufZj6Un2f2oAp7jTlcgirX2Y54FW7fRpJcF%2FkX360gDTLU3M4dh%2B7Tk%2B5roCoK4I4pkEKW8SxoMKKloNYqyMDVtOBUuorlZojG%2FTpXossYkjKmuU1SwKOSBVbotE%2BiSfIK6dTlQa47SXMcm011kDbkFSDViaiiigQUUUUAFFFFABRRRQAUUUjHAzQBx%2FjbethI0fJA5FfMfiO9Mt26Z719W6naHUY5ItvWvm34k%2BGZNG1Mz7cJIea1cfduRfU4SjNFJWRYuaKSigBc0UlFADga9M%2BCkYfxgSeyV5jXU%2BBvEY8N64LpjhSMGmtxNXPrfU9Sg0qwkup3Cqgzz3r5q8V%2BK5vFXiYxIxNujcD1qbx98TJ%2FEEC2ttJtjx822uT8KRmS%2F3Yyc01vYD1zw3ZYhQY7V0b22BTNAs9lqrEdq1ZIuOlbk3MR4MUsSFWrQaKmrDlxxSGaVooaAAjOa8v%2BJGhLZ3aXsSYV%2BGxXqsGFQAVQ8R6Omt6XJAw%2BbHFKpHmQ4Ssz576V6P8KhmXUif7i%2Fzrg9S0%2BXTb6S2mGGU%2FnXoHwoXC6k2OyiuamveN6j902vGCqmgXxxx5TfyryD4YLu8WfRDXrXj6Ty%2FDF8R18sivK%2FhSufE7n0jNb%2FbOfoezXZxDg1zFxL5twx7dBXRas%2Byzc98Vyy8miW40RXY%2Fdk1yWoNs3n0Fdfdj90fpXD69IY4JiDjioYzi5XMkzse5NRmlzxSHpUgFamha3daFfpc27kAH5lzwRWVS7qFoB1XjDxQviOW2dFK%2BWp3Z9a5NjmhmzTDQAGikooAKKKKACgUUUAaFkT5J%2FwB7%2FCiksv8AUn%2Fe%2FoKKAPruaG4N3EkcrpEytuIRTtPy46j%2FAHqrL9vAhP70lslv3Q%2BX519v7ob862x1p46VRkjEhe%2FBiLwzMu8lwUGduOn8ue5zjirGL0Ek%2BccOd4Ea4I%2BbAX2%2B7z%2F9etYdalWkMxIP7QkuYVmhZV3EuQo2%2FdIx9MjP%2FAvateOxL8twKnHWrQ7UDSTIoraOL7qjPqetTUdqKRSQUUUUDCq11arOh45qzSGmgOb%2FALPaKfIFa0BZFGakm%2B9T06UMdyRZM8Yp5BIpqU%2BkIB0ooooAKKKKACiiigAqvcXcVvjzGxU5rm9c%2FwBc1XCPM7CZrDVLLBPmqK8T%2BM%2BoWt9AIocMwOciuuuP4q8s8d%2Fx1bjyrQnc8ypDTqaaxLEpaSigAzRmjtSDvQAvSjNFJQA8GvSvhto63Uqyt3Oa81r2H4Wf6pauG5Mj1y2gWKFUA4Ap8qcVIn3aJPu1syTPdMVJbQ7jkilk71Pb%2FdpDEI2NVmMgioJvv0%2BLtTA8d%2BJkXl%2BIFYLgMtb3woT%2FAIl%2Bov8A7QFZnxU%2F5CkH0rZ%2BFP8AyB7%2FAP66D%2BVc8V%2B8NpfAL8R2C%2BFL0k%2Fw4rzj4Rx7tduH%2Fux16F8Tv%2BRTu%2FwrgvhB%2FwAha7%2F3K0%2B2ZdD1DWj%2FAKG1c5HXQ65%2Fx5t9RXPRU3uNCXQ%2FdGvO%2FFTbYWHqa9Fuv9Sa818XdF%2F3qzkM5U8U3NKaaakAozRSHrQAhooNHagBKKKKACiig0AFFJS0AX7L%2FUn%2FAHv8KKWx%2FwBS3%2B9%2FQUUAf%2F%2FZ";
        int mode = 0;

        String signature = "F631A947DFC018208E190DAF2B813933";

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put(SignUtils.APP_ID, appId);
        paramsMap.put(SignUtils.TIMESTAMP, timestamp);
        paramsMap.put(SignUtils.NONCE_STR, nonceStr);
        paramsMap.put(SignUtils.IMAGE, imageBase64Str);
        paramsMap.put(SignUtils.MODE, mode);
        paramsMap.put(SignUtils.SIGN, signature);

        boolean validataSuccess = SignUtils.validateSignature(paramsMap);
        if(validataSuccess) {
            System.out.println("签名验证成功!");
        } else {
            System.out.println("验证失败！！！");
        }

    }



}

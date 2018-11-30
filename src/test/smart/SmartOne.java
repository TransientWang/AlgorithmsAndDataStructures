package test.smart;

/**
 * @author wangfy
 * @Description 脑筋急转弯
 * @date 2018/11/2
 */

import org.junit.Test;

import java.math.BigInteger;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

/**
 * @Description 你和你的朋友，两个人一起玩 Nim游戏：桌子上有一堆石头，
 * 每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
 * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
 * <p>
 * 巴什博奕，n%(m+1)!=0时，先手总是会赢的
 * 第一次先抓3个 ，剩下对手抓几个 自己抓 3 - n个
 * 这样就能整除了  下次 抓就是你赢了
 * @Param
 * @return
 * @Line 13
 **/
public class SmartOne {
    public boolean canWinNim(int n) {
        if (n % 4 == 0)
            return false;
        return true;
    }




    @Test
    public void https() throws Exception {


        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.baidu.com/"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }


}

package cn.vcinema.partner.demo;

import cn.vcinema.partner.HttpClientUtil;
import cn.vcinema.partner.PartnerInfo;
import cn.vcinema.partner.PartnersApiSignature;
import cn.vcinema.partner.Random;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HTDemo {
    // 请求域名
    private static final String prefix_url = "https://dev.partner.vcinema.cn:3505";

    // 初始化线程
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 200, 360, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100000), new NameTreadFactory(), new IgnorePolicy());

    private static class NameTreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ht-thread-" + mThreadNum.getAndIncrement());
        }
    }

    private static class IgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
        }
    }

    /**
     * 通过手机号获取用户及订单信息
     *
     * @param phone 查询的手机号码，多个逗号分隔
     * @throws Exception
     */

    public String getHTOrders(String phone) throws Exception {

        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER); // 获取10位随机数
        String action = "/pay/get_order_ht"; // 请求路径
        String pid = PartnerInfo.pid; // pid，第三方标识，分配获得
        String accessSecret = PartnerInfo.accessSecret; // 加密秘钥，分配获得
        String httpMethod = PartnerInfo.httpPostMethod; //请求方式
        long timestamp = System.currentTimeMillis(); // 时间戳
        String format = PartnerInfo.format; // 返回格式


        LinkedMap signParams = new LinkedMap();
        signParams.put("phone", phone);
        signParams.put("timestamp", timestamp + "");
        signParams.put("signature_nonce", signatureNonce);
        signParams.put("format", format);

        // 获取加密信息
        String signature = PartnersApiSignature.partnersApiSignature(httpMethod, action, format, pid, signatureNonce, accessSecret, timestamp, signParams);

        /**
         * 封装post参数
         */
        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("phone", phone));
        parameter.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", format));
        parameter.add(new BasicNameValuePair("sign", signature));

        /**
         * 获取信息
         */
        String url = prefix_url + "/" + action; // 请求地址
        String result = HttpClientUtil.doPost(url, parameter);
        return result;
    }


    /**
     * 根据手机号查询订单信息，多线程
     */
    public List<String> getHTOrdersExecutor() {
        List<String> list = new ArrayList<>();

        List<Future<String>> resultList = new ArrayList<>();

        // 并发获取数据
        for (int i = 0; i < 5000; i++) {
            String phone = "131xxxxxxx" + i / 10;
            Future<String> future = executor.submit(() -> getHTOrders(phone));
            resultList.add(future);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                list.add(fs.get());     //添加各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                executor.shutdown();
            }
        }

        return list;
    }


    public static void main(String[] args) throws Exception {
        HTDemo htDemo = new HTDemo();
        String phone = "131xxxxxxx1,131xxxxxxx2";
        String result = htDemo.getHTOrders(phone);
        System.out.println(result);

        // 多线程
        List<String> list = htDemo.getHTOrdersExecutor();
        System.out.println("集合长度：---> " + list.size());
    }
}

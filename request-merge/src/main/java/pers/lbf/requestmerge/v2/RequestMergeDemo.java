package pers.lbf.requestmerge.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ferryman
 * @since 2022/6/2 14:19
 */
public class RequestMergeDemo {

    private static int stock = 800;

    private static final String lock = "lock";

    private static BlockingQueue<RequestPromise> queue = new LinkedBlockingQueue<>(200);

    public static void main(String[] args) {
        System.out.println("总库存" + stock);
        mergeJob();
        mergeJob();
        for (int i = 0; i < 2000; i++) {
            int finalI = i;
            new Thread(() -> {
                Result result = deducting(new UserReq(20220L + finalI, Long.parseLong("460034" + System.currentTimeMillis()), 1));
                System.out.println(result);
                while (!result.success) {
                    result = deducting(new UserReq(20220L + finalI, Long.parseLong("460034" + System.currentTimeMillis()), 1));

                }
                System.out.println(result);
            }).start();

        }


    }

    public static Result deducting(UserReq req) {
        RequestPromise requestPromise = new RequestPromise(req);
        boolean flag = false;
        try {
            flag = queue.offer(requestPromise, 10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
//            e.printStackTrace();
            return Result.failed("系统繁忙");
        }
        if (!flag) {
            return Result.failed("系统繁忙");
        }

        synchronized (requestPromise) {
            try {
                requestPromise.wait(200);
            } catch (InterruptedException e) {
                System.out.println(e);

            }

        }

        return requestPromise.result;

    }

    public static void mergeJob() {
        new Thread(() -> {
            List<RequestPromise> requestPromiseList = new ArrayList<>();

            while (true) {
//                System.out.println("stock:" + stock);
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (queue.peek() != null) {
                    synchronized (lock) {
                        if (stock > 0) {
                            for (int i = 0; i < stock; i++) {
                                if (queue.peek() != null) {
                                    requestPromiseList.add(queue.poll());
                                } else {
                                    break;
                                }
                            }
                            stock = stock - requestPromiseList.size();
                            System.out.println("本批次扣减：" + requestPromiseList.size() + "剩余库存" + stock);
                            requestPromiseList.forEach(requestPromise -> {

                                synchronized (requestPromise) {
                                    Result ok = Result.ok();
                                    requestPromise.setResult(ok);
                                    requestPromise.notify();
                                }
                            });

                            requestPromiseList.clear();

                        } else {
                            while (queue.peek() != null) {
                                requestPromiseList.add(queue.poll());
                            }

                            requestPromiseList.forEach(requestPromise -> {
                                synchronized (requestPromise) {
                                    requestPromise.setResult(Result.ok("没有抢到"));
                                    requestPromise.notify();
                                }
                            });

                            requestPromiseList.clear();
                        }

                    }
                }


            }


        }).

                start();
    }
}


class RequestPromise {
    UserReq req;
    Result result;

    public RequestPromise(UserReq req) {
        this.req = req;
    }

    public UserReq getReq() {
        return req;
    }

    public void setReq(UserReq req) {
        this.req = req;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

class UserReq {
    private Long uid;
    private Long orderNo;
    private Integer count;

    public UserReq(Long uid, Long orderNo, Integer count) {
        this.uid = uid;
        this.orderNo = orderNo;
        this.count = count;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

class Result {
    Boolean success;
    String msg;

    public static Result ok(String msg) {
        return new Result(true, msg);
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static Result failed(String msg) {

        return new Result(false, msg);
    }

    public static Result ok() {
        return new Result(true, "操作成功！");
    }

    public Result(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

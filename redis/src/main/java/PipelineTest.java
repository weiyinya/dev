import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author Dwen
 * @date 2021-02-22 15:48
 */
public class PipelineTest {

    private Jedis jedis;

    public PipelineTest(Jedis jedis) {
        this.jedis = jedis;
    }

    public void test() {
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.set("aaaa", "1111");
        pipe.set("bbbb", "2222");
        pipe.expire("aaaa", 60);
        pipe.expire("bbbb", 60);
        pipe.exec();
        pipe.close();
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        PipelineTest pipelineTest = new PipelineTest(jedis);
        pipelineTest.test();
    }
}

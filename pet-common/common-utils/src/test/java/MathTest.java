import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathTest {


    Logger log = LoggerFactory.getLogger(MathTest.class);

    @Test
    public void test(){
        System.out.println(2.0f / 0);
        double sqrt = Math.sqrt(4.0);
        log.info(String.valueOf(sqrt));
        double pow = Math.pow(2, 2);
        log.info(String.valueOf(pow));

    }
}

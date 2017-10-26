package st.digitru.producer;

import java.util.Random;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SingleThreadIdentityProducerTestCase extends BaseIdentityProducerTestCase {

	public SingleThreadIdentityProducerTestCase() {
		super(new SingleThreadIdentityProducer(new Random(), BaseIdentityProducerTestCase.TEST_PRODUCER));
	}
}

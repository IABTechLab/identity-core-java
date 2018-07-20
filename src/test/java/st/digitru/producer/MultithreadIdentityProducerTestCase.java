package st.digitru.producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import st.digitru.model.Identity;

@RunWith(JUnit4.class)
public class MultithreadIdentityProducerTestCase extends BaseIdentityProducerTestCase {

	public MultithreadIdentityProducerTestCase() {
		super(new MultithreadIdentityProducer(BaseIdentityProducerTestCase.TEST_PRODUCER));
	}

	@Test
	public void uniqueness() throws Exception {
		int taskCount = 1000000;
		Map<String, Integer> uniqueIds = new HashMap<String, Integer>();
		List<ForkJoinTask<String>> tasks = new ArrayList<ForkJoinTask<String>>(taskCount);
		for (int i = 0; i < taskCount; ++i) {
			ForkJoinTask<String> task = ForkJoinPool.commonPool().submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Identity i = producer.create();
					return i.getId();
				}
			});
			tasks.add(task);
		}
		for (ForkJoinTask<String> task : tasks) {
			uniqueIds.put(task.get(), 1);
		}
		Assert.assertEquals(taskCount, uniqueIds.size());
	}
}

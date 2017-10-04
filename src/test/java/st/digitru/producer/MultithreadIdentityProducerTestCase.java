package st.digitru.producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		super(new MultithreadIdentityProducer());
	}

	@Test
	public void uniqueness() throws Exception {
		int taskCount = 1000000;
		ForkJoinPool pool = new ForkJoinPool();
		List<ForkJoinTask<String>> tasks = new ArrayList<ForkJoinTask<String>>(taskCount);
		for (int i = 0; i < taskCount; ++i) {
			ForkJoinTask<String> task = new ForkJoinTask<String>() {
				private String id;

				@Override
				public String getRawResult() {
					return id;
				}

				@Override
				protected void setRawResult(String value) {
				}

				@Override
				protected boolean exec() {
					Identity i = producer.create();
					this.id = i.getId();
					return true;
				}
			};
			tasks.add(task);
			pool.execute(task);
		}
		Map<String, Integer> uniqueIds = new HashMap<String, Integer>();
		for (ForkJoinTask<String> task : tasks) {
			String id = task.get();
			uniqueIds.put(id, new Integer(1));
		}
		Assert.assertEquals(taskCount, uniqueIds.size());
	}
}

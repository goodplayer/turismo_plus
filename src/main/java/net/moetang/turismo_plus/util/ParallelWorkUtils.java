package net.moetang.turismo_plus.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ParallelWorkUtils {
	public static interface Task<V>{
		public V work();
	}
	public static final class TaskImpl<V> extends RecursiveTask<V>{
		private static final long serialVersionUID = 1L;
		private Task<V> task;
		public TaskImpl(Task<V> task) {
			if(task == null){
				throw new RuntimeException("task should not be null");
			}
			this.task = task;
		}
		@Override
		protected final V compute() {
			return task.work();
		}
	}
	
	public static final class TaskResult<V>{
		private ForkJoinTask<V> task;
		public TaskResult(ForkJoinTask<V> task){
			if(task == null){
				throw new RuntimeException("task should not be null");
			}
			this.task = task;
		}
		public ForkJoinTask<V> rawTask(){
			return task;
		}
		public V get(){
			return task.join();
		}
		public V get(long timeout, TimeUnit unit){
			try {
				return this.task.get(timeout, unit);
			} catch (InterruptedException | ExecutionException
					| TimeoutException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}

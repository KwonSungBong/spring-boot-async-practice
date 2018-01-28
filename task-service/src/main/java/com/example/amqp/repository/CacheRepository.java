package com.example.amqp.repository;

import com.example.amqp.entity.TaskTest;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Repository
public class CacheRepository {

    private final String TASK_TEST_LIST_KEY = "entity.task.test.list";
    private final String TASK_TEST_MAP_KEY = "entity.task.test.map";

    @Autowired
    protected HazelcastInstance hazelcastInstance;

    public boolean isExist(String id) {
        return hazelcastInstance.getSet(TASK_TEST_MAP_KEY).contains(id);
    }

    public List<TaskTest> findAll() {
        return hazelcastInstance.getList(TASK_TEST_LIST_KEY);
    }

    public void saveList(List<TaskTest> taskTests) {
        IList<TaskTest> taskTestIList = hazelcastInstance.getList(TASK_TEST_LIST_KEY);
        taskTests.forEach(taskTest -> taskTestIList.add(taskTest));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Set<TaskTest> getISet() {
        return hazelcastInstance.getSet(TASK_TEST_MAP_KEY);
    }

    public Map<Long, TaskTest> getIMap() {
        return hazelcastInstance.getMap(TASK_TEST_MAP_KEY);
    }

    public MultiMap<Long, TaskTest> getMultiMap() {
        return hazelcastInstance.getMultiMap(TASK_TEST_MAP_KEY);
    }

    public void forceUnLock() {
        hazelcastInstance.getLock(TASK_TEST_MAP_KEY).forceUnlock();
    }

    public boolean isLocked() {
        return hazelcastInstance.getLock(TASK_TEST_MAP_KEY).isLocked();
    }

    public void destroy() {
        hazelcastInstance.getSet(TASK_TEST_MAP_KEY).destroy();
    }

    public void delete(long id) {
        hazelcastInstance.getSet(TASK_TEST_MAP_KEY).remove(id);
    }

    public void saveMap(List<TaskTest> taskTests) {
        IMap<Long, TaskTest> taskTestMap = hazelcastInstance.getMap(TASK_TEST_MAP_KEY);
        taskTests.forEach(taskTest -> taskTestMap.put(taskTest.getId(), taskTest));
    }

    public void put(long id, TaskTest taskTest) {
        hazelcastInstance.getMap(TASK_TEST_MAP_KEY).put(id, taskTest);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    public TaskTest getTaskTest(Long id) {
        ConcurrentMap<Long, TaskTest> taskTests = hazelcastInstance.getMap(TASK_TEST_MAP_KEY);
        TaskTest taskTest = taskTests.get(id);
        if (taskTest == null) {
            taskTest = new TaskTest();
            taskTest.setId(id);
            taskTest = taskTests.putIfAbsent(id, taskTest);
        }
        return taskTest;
    }

    public boolean updateTaskTest(TaskTest taskTest) {
        ConcurrentMap<Long, TaskTest> taskTests = hazelcastInstance.getMap(TASK_TEST_MAP_KEY);
        return (taskTests.replace(taskTest.getId(), taskTest) != null);
    }

    public boolean removeTaskTest(TaskTest taskTest) {
        ConcurrentMap<Long, TaskTest> taskTests = hazelcastInstance.getMap(TASK_TEST_MAP_KEY);
        return taskTests.remove(taskTest.getId(), taskTests);
    }

}

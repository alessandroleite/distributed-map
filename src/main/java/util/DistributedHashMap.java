package util;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedHashMap<K extends Serializable, V extends Serializable>
		extends AbstractMap<K, V> implements Watcher, ConcurrentMap<K, V> {

	private static final Logger LOG = LoggerFactory.getLogger(DistributedHashMap.class);

	private Map<K, V> repository;
	
	private final ZooKeeper zooKeeper;

	private final String rootDir, dataDir, leaderDir;
	
	
	public DistributedHashMap(String address, String rootDir, int initialCapacity) throws IOException {
				
		LOG.debug("Starting ZooKeeper ...");
		zooKeeper = new ZooKeeper(checkNotNull(address), 3000, this);
		LOG.debug("Finished starting ZooKeeper: {}", zooKeeper);
		
		checkArgument(initialCapacity > 0);
		repository = new ConcurrentHashMap<K, V>(initialCapacity);
		
		checkArgument(isNullOrEmpty(rootDir));
		
		this.rootDir = rootDir;
		this.dataDir = this.rootDir + "/data";
		this.leaderDir = this.rootDir + "/leader";
	}


	/* ************************************************ */
	/* 				 ZooKeeper methods 				    */
	/* ************************************************ */
	@Override
	public void process(WatchedEvent event) {
	}
	
	/* ************************************************ */
	/* 			java.util.Method methods 				*/
	/* ************************************************ */
	
	@Override
	public void clear() {
		super.clear();
	}


	
	@Override
	public boolean containsKey(Object key) {	
		return super.containsKey(key);
	}

	
	@Override
	public boolean containsValue(Object value) {
		return super.containsValue(value);
	}
	
	@Override
	public V get(Object key) {
		return this.repository.get(key);
	}

	
	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	
	@Override
	public Set<K> keySet() {
		return super.keySet();
	}

	
	@Override
	public V put(K key, V value) {		
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		super.putAll(m);
	}

	
	@Override
	public V remove(Object key) {
		return super.remove(key);
	}


	@Override
	public int size() {
		return super.size();
	}

	@Override
	public Collection<V> values() {
		return super.values();
	}
	

	/* ************************************************ */
	/* 				ConcurrentMap methods 				*/
	/* ************************************************ */


	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return null;
	}

	@Override
	public V putIfAbsent(K key, V value) {
		return null;
	}

	@Override
	public boolean remove(Object key, Object value) {
		return false;
	}

	@Override
	public V replace(K key, V value) {
		return null;
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		return false;
	}
	
	/* ************************************************ */
	/* 				   Callback methods 				*/
	/* ************************************************ */
}
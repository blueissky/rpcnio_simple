package qjm.zookpeer;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class zookpeerTest{

 
    private static ZooKeeper zk;
	private static String serverPath="/server";
	private static Stat stat;

	public static void main(String[] args) throws Exception {
    	
    	Watcher watcher = new Watcher(){
			public void process(WatchedEvent event) {
				System.out.println(event.toString());
			}
		};
		//连接服务
		zk = new ZooKeeper("127.0.0.1:2181", 30000, watcher);
		//当前节点是否存在
		stat = zk.exists(serverPath, watcher);
		setData();
    }
	//增
    public static void create() throws Exception{
		zk.create(serverPath,"192.168.10.223:8021".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    //删
    public static void delete() throws Exception{
    	zk.delete(serverPath, stat.getVersion());
    }
    //改
    public static void setData() throws Exception{
		zk.setData(serverPath, "127.0.0.1:9998".getBytes(), stat.getVersion());
	}
    //查
    public static void getData() throws Exception{
    	byte[] data = zk.getData(serverPath,false, stat);
    	String val = new String(data,"UTF-8");
    	System.out.println(val);
    }
}

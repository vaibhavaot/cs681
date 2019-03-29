
package edu.umb.cs.cs681.hw15;

import java.awt.Point;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java. util. Iterator;


public class AccessCounter{
private AccessCounter(){
	
	
	
	
	
};
private static AccessCounter instance = null;
private static ReentrantLock lock = new ReentrantLock();
private ReentrantLock lock1 = new ReentrantLock();
ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


// Factory method to create or return the singleton instance
private HashMap<Path, Integer> map=new HashMap<Path, Integer>();




public static AccessCounter getInstance(){
     lock.lock();
try{
if(instance==null){ instance = new AccessCounter(); } 
return instance;

}finally{
        lock.unlock();
}


}

public void increment(Path path) {
	
	rwLock.writeLock().lock();
	
	try {System.out.println("write locked");
	System.out.println("increment path="+path);
	if (this.map.containsKey(path)) {
		Integer value = (this.map.get(path))+1;
		
		map.put(path,value);
	}
	else
	{
		map.put(path,1);
	}
	
	}finally {System.out.println("write unlocked");
		rwLock.writeLock().unlock();
	}
	
}

public Integer getCount(Path path) {
	
	rwLock.readLock().lock();
	
	try {
		System.out.println("read locked");
	
	if (this.map.containsKey(path)) {
		Integer value = (this.map.get(path));
		
		return value;
	}
	else
	{
		return 0;	
	}
	
	
}finally {System.out.println("read unlocked ");

	rwLock.readLock().unlock();
}
}



}
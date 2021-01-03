package com;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EntityManagerClass {

	private static final EntityManagerFactory emFactoryObj;
	
	
	static {
        emFactoryObj = Persistence.createEntityManagerFactory("DemoJpaMaven");
        emFactoryObj.createEntityManager();
    }
 
    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }
    
    public static Object persistData(Object obj) {
    	EntityManager entityMgr = getEntityManager();
    	entityMgr.getTransaction().begin();
    	entityMgr.persist(obj);
    	entityMgr.getTransaction().commit();
    	System.out.println("Data Persisted........");
    	return obj;
    }
    
    
    public static Object findData(int stId) {
    	EntityManager entityMgr = getEntityManager();
    	entityMgr.getTransaction().begin();
    	Student st =entityMgr.find(Student.class, stId);
    	entityMgr.getTransaction().commit();
    	System.out.println("Data Found for student........"+st.getsId());
    	return st;
    }
    
    
    public static int removeData(int stId) {
    	EntityManager entityMgr = getEntityManager();
    	entityMgr.getTransaction().begin();
    	Student st = entityMgr.find(Student.class, stId);
    	entityMgr.remove(st);
    	entityMgr.getTransaction().commit();
    	System.out.println("Data removed for stdent Id........"+stId);
    	return stId;
    }
    
    
    public static int updateData(int stId) {
    	EntityManager entityMgr = getEntityManager();
    	entityMgr.getTransaction().begin();
    	Student st = entityMgr.find(Student.class, stId);
    	st.setName("Aditya");
    	entityMgr.getTransaction().commit();
    	System.out.println("Data updated for stdent Id........"+stId);
    	return stId;
    }
    
    public static void createNativeQuery() {
    	EntityManager entityMgr = getEntityManager();
    	entityMgr.getTransaction().begin();
    	Query qry = entityMgr.createNativeQuery("select * from student");
    	List<Student> stList = qry.getResultList();
    	System.out.println("native query result :"+stList.size());
    	entityMgr.getTransaction().commit();
    	
    }
    
    
    public static void createQuery(int sId) {
    	EntityManager entityMgr = getEntityManager();
    	entityMgr.getTransaction().begin();
    	Query qry = entityMgr.createQuery("select name from Student where sId="+sId);
    	String name = qry.getSingleResult().toString();
    	System.out.println("HQ select query result :"+name);
    	entityMgr.getTransaction().commit();
    	
    }
    
    
	public static void main(String args[]) {
		
        Student stObj = new Student();
        stObj.setsId(1);
        stObj.setName("manali");
        stObj.setPhn("123");
        
      // persistData(stObj);
       // findData(1);
      //  updateData(1);
       // removeData(1);
       // createNativeQuery();
        createQuery(1);
        
        		
	}
}

package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    
    
	public static void main(String args[]) {
		
        Student stObj = new Student();
        stObj.setsId(1);
        stObj.setName("Manali");
        stObj.setPhn("123");
        
        persistData(stObj);
        findData(1);
        updateData(1);
        removeData(1);
        
        		
	}
}

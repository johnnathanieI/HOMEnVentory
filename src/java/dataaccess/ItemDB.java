/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author johnn
 */
public class ItemDB {
    
    public Item get(int itemID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Item item = em.find(Item.class, itemID);
            return item;
        } finally {
            em.close();
        }
    }
    
    public List<Item> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Item> items = em.createNamedQuery("Item.findAll", Item.class)
                    .getResultList();
            
            return items;
        } finally {
            em.close();
        }
    }
    
    public void insert(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            User user = item.getOwner();
            user.getItemList().add(item);
            
            Category category = item.getCategory();
            category.getItemList().add(item);
            
            trans.begin();
            em.persist(item);
            em.merge(user);
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            User user = item.getOwner();
            user.getItemList().remove(item);
            
            Category category = item.getCategory();
            category.getItemList().remove(item);
            
            trans.begin();
            em.remove(em.merge(item));
            em.merge(user);
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}

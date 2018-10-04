package org.lyc.manager.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by admin on 2018/9/11.
 */
public class HibernateApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            Transaction t = session.getTransaction();
            t.begin();

            User user = new User();

            user.setId(2);
            user.setUserName("超级管理员");
            user.setUserPwd(toMd5("12345"));
            user.setRegisterTime(new Date());

            session.delete(user);

            User user2 = session.load(User.class,new Integer(1));

            System.out.println(user2.getUserName());

            t.commit();
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            if (sessionFactory!=null){
                sessionFactory.close();
            }
        }

    }


    public static String toMd5(String str){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}

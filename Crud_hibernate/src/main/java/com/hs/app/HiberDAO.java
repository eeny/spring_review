package com.hs.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HiberDAO {
	@Autowired
	private SessionFactory sessionFactory;


	public void insertData(HiberDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		session.save(dto);
		trans.commit();
	}
	
	public void updateData(HiberDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();

		session.update(dto);
		trans.commit();
	}
	
	public void deleteData(HiberDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();

		session.delete(dto);
		trans.commit();
	}

	public HiberDTO selectData(HiberDTO dto) {		
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		HiberDTO d=(HiberDTO) session.get(HiberDTO.class, dto.getIdx());
		
		trans.commit();
		return d;
	}

	public List<HiberDTO> selectData() {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List<HiberDTO> list=session.createCriteria(HiberDTO.class).list();		
		
		trans.commit();
		return list;
	}
}




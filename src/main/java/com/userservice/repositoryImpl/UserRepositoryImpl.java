package com.userservice.repositoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.userservice.entity.User;
import com.userservice.repository.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	EntityManager em;

	@SuppressWarnings("deprecation")
	@Override
	public List<User> searchUserByFilter(String firstName, String lastName, String pinCode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> user = cq.from(User.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(firstName)) {
			predicates.add(cb.like(user.get("firstName"), "%" + firstName + "%"));
		}
		if (!StringUtils.isEmpty(lastName)) {
			predicates.add(cb.like(user.get("lastName"), "%" + lastName + "%"));
		}
		if (!StringUtils.isEmpty(pinCode)) {
			predicates.add(cb.like(user.get("pinCode"), "%" + pinCode + "%"));
		}
		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<User> query = em.createQuery(cq);
		return query.getResultList();
	}

}

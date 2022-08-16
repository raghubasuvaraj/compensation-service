package com.eatco.compensationservice.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.eatco.compensationservice.model.Compensation;
import com.eatco.compensationservice.repository.CompensationCustomRepo;

@Repository
public class CompensationCustomRepoImpl implements CompensationCustomRepo {

    @Autowired
    private EntityManager entityManager;
    @Override
    public Page<Compensation> getCompensationByDate(Date fromDate, Date toDate, String userId,PageRequest pageRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Compensation> cQuery = cb.createQuery(Compensation.class);
        Root<Compensation> root = cQuery.from(Compensation.class);
        List<Predicate> predicates = getPredicates(fromDate, toDate, userId,cb, root);

        cQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Compensation> query = entityManager.createQuery(cQuery);
        int total = query.getResultList().size();
        query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        query.setMaxResults(pageRequest.getPageSize());

        return new PageImpl<>(query.getResultList(), pageRequest, total);
    }

    private List<Predicate> getPredicates(Date fromDate, Date toDate, String userId,CriteriaBuilder cb, Root<Compensation> root) {
        List<Predicate> pd = new ArrayList<>();
        pd.add(cb.equal(root.get("createdBy"), userId));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (null != fromDate && fromDate.equals(toDate)){
            pd.add(cb.greaterThanOrEqualTo(root.get("createdAt"), fromDate));
        }else if (null != fromDate && null != toDate){
            pd.add(cb.greaterThanOrEqualTo(root.get("createdAt"), fromDate));
            pd.add(cb.lessThanOrEqualTo(root.get("createdAt"), toDate));
        }
        return pd;
    }
}

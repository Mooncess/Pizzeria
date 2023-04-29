package ru.mooncess.Pizzeria.repositories.snack;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Snack;

import java.util.List;

@Repository
@AllArgsConstructor
public class SortSnackByPriceImpl implements SortSnackByPrice {
    private final EntityManager em;

    @Override
    public List<Snack> findByOrderByPriceAsc() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Snack> queryCriteriaQuery = builder.createQuery(Snack.class);
        Root<Snack> root = queryCriteriaQuery.from(Snack.class);
        queryCriteriaQuery.select(root).orderBy(builder.asc(root.get("price")));
        return em.createQuery(queryCriteriaQuery).getResultList();
    }

    @Override
    public List<Snack> findByOrderByPriceDesc() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Snack> queryCriteriaQuery = builder.createQuery(Snack.class);
        Root<Snack> root = queryCriteriaQuery.from(Snack.class);
        queryCriteriaQuery.select(root).orderBy(builder.desc(root.get("price")));
        return em.createQuery(queryCriteriaQuery).getResultList();
    }
}

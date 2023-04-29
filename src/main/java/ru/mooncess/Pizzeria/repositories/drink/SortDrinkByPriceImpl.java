package ru.mooncess.Pizzeria.repositories.drink;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Drink;

import java.util.List;

@Repository
@AllArgsConstructor
public class SortDrinkByPriceImpl implements SortDrinkByPrice {
    private final EntityManager em;

    @Override
    public List<Drink> findByOrderByPriceAsc() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Drink> queryCriteriaQuery = builder.createQuery(Drink.class);
        Root<Drink> root = queryCriteriaQuery.from(Drink.class);
        queryCriteriaQuery.select(root).orderBy(builder.asc(root.get("price")));
        return em.createQuery(queryCriteriaQuery).getResultList();
    }

    @Override
    public List<Drink> findByOrderByPriceDesc() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Drink> queryCriteriaQuery = builder.createQuery(Drink.class);
        Root<Drink> root = queryCriteriaQuery.from(Drink.class);
        queryCriteriaQuery.select(root).orderBy(builder.desc(root.get("price")));
        return em.createQuery(queryCriteriaQuery).getResultList();
    }
}

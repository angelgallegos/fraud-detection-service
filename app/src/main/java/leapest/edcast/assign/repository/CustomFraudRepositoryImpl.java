package leapest.edcast.assign.repository;


import org.springframework.stereotype.Repository;

@Repository
public class CustomFraudRepositoryImpl {

    /*@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Fraud> findAllByCriteria(FraudCriteria fraudCriteria) {

        var jpql = new StringBuilder();
        jpql.append("from Fraud where 1=1 ");

        var parameters = new HashMap<String, Object>();

        if (fraudCriteria.getFrom() != null) {
            jpql.append("and created_on >= :from ");
            parameters.put("from", fraudCriteria.getFrom());
        }

        if (fraudCriteria.getTo() != null) {
            jpql.append("and created_on <= :to ");
            parameters.put("to", fraudCriteria.getTo());
        }

        *//*if (StringUtils.hasLength(fraudCriteria.getCuisine())) {
            jpql.append("and cuisine.name like :cuisine ");
            parameters.put("cuisine", "%" + fraudCriteria.getCuisine() + "%");
        }

        if (StringUtils.hasLength(fraudCriteria.getCity())) {
            jpql.append("and city like :city ");
            parameters.put("city", "%" + fraudCriteria.getCity() + "%");
        }*//*

        TypedQuery<Fraud> query = entityManager.createQuery(jpql.toString(), Fraud.class);

        parameters.forEach(query::setParameter);

        return query.getResultList();
    }*/
}


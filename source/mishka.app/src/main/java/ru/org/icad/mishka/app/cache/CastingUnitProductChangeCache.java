package ru.org.icad.mishka.app.cache;

import ru.org.icad.mishka.app.cache.key.ProductChangeKey;
import ru.org.icad.mishka.app.model.CastingUnitProductChange;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CastingUnitProductChangeCache {

    private static volatile CastingUnitProductChangeCache instance;

    private Map<ProductChangeKey, CastingUnitProductChange> changeKeyCastingUnitProductChangeMap;

    private CastingUnitProductChangeCache() {
        changeKeyCastingUnitProductChangeMap = new ConcurrentHashMap<>();

        loadCastingUnitProductChange();
    }

    public static CastingUnitProductChangeCache getInstance() {
        CastingUnitProductChangeCache localInstance = instance;
        if (localInstance == null) {
            synchronized (CastingUnitProductChangeCache.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CastingUnitProductChangeCache();
                }
            }
        }
        return localInstance;
    }

    private void loadCastingUnitProductChange() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MishkaService");
        EntityManager em = emf.createEntityManager();

        List<CastingUnitProductChange> castingUnitProductChanges
                = em.createNamedQuery("CastingUnitProductChange.findAll", CastingUnitProductChange.class).getResultList();


        for (CastingUnitProductChange castingUnitProductChange : castingUnitProductChanges) {
            changeKeyCastingUnitProductChangeMap.put(
                    new ProductChangeKey(
                            castingUnitProductChange.getCastingUnit().getId(),
                            castingUnitProductChange.getMarkId1(),
                            castingUnitProductChange.getMarkId2()
                    ),
                    castingUnitProductChange);
        }
    }

    public CastingUnitProductChange getCastingUnitProduct(ProductChangeKey productChangeKey) {
        return changeKeyCastingUnitProductChangeMap.get(productChangeKey);
    }
}

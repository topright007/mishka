package ru.org.icad.mishka.app.process.casting.schema9.operation.prepare;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.org.icad.mishka.app.constant.OperationName;
import ru.org.icad.mishka.app.model.PrepareTimeConst;
import ru.org.icad.mishka.app.process.casting.Operation;
import ru.org.icad.mishka.app.process.casting.Schema;
import ru.org.icad.mishka.app.util.TimeUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;

public class PrepareCmOneOperation extends Operation {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrepareCmOneOperation.class);
    private static final int ONE_OPERATION_ID = 1;

    private final Schema schema;

    public PrepareCmOneOperation(Schema schema) {
        this.schema = schema;
    }

    @Override
    public void activate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MishkaService");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("select * from PREPARE_TIME_CONST ptc where ptc.CAST_MACH_ID = " + schema.getSchemaConfiguration().getCastingUnitCastingMachineIds()[0]
                + " and ROWNUM = 1", PrepareTimeConst.class);

        PrepareTimeConst prepareTimeConst = (PrepareTimeConst) query.getSingleResult();
        long durationTime = prepareTimeConst.getDurationTime() * 60 * 1000;

        final Date activationDate = new Date(getActivationDate().getTime() + durationTime);

        Operation operation = schema.getOperationMap().get(OperationName.CAST_CM_ONE_COLLECTOR_ONE);
        if (ObjectUtils.compare(activationDate, operation.getActivationDate()) == 1) {
            operation.setActivationDate(activationDate);
        }

        operation.setActivationCount(operation.getActivationCount() - 1);

        if (operation.getActivationCount() == 0) {
            schema.getOperations().add(operation);
        }

        LOGGER.debug("Result - castingUnitId: " + schema.getSchemaConfiguration().getCastingUnitId()
                + ", Operation type: PrepareCmOneOperation startDate: "
                + TimeUtil.convertTimeToString(getActivationDate().getTime()));
    }
}

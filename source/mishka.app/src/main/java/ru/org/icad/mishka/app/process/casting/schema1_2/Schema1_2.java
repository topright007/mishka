package ru.org.icad.mishka.app.process.casting.schema1_2;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import ru.org.icad.mishka.app.process.casting.CastWrapper;
import ru.org.icad.mishka.app.process.casting.Operation;
import ru.org.icad.mishka.app.process.casting.Schema;
import ru.org.icad.mishka.app.process.casting.schema1_2.operation.PrepareDistrOperation;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class Schema1_2 implements Schema {

    private static final Deque<CastWrapper> CAST_WRAPPERS = Queues.newLinkedBlockingDeque();
    private static final Map<String, Operation> OPERATION_MAP = Maps.newConcurrentMap();

    public Schema1_2() {
    }

    @Override
    public List<? extends Operation> getInitOperations() {
        return Arrays.asList(new PrepareDistrOperation());
    }

    @Override
    public void addToSchemeCasts(CastWrapper castWrapper) {
        CAST_WRAPPERS.add(castWrapper);

    }

    @Override
    public Deque<CastWrapper> getDequeCastWrapper() {
        return CAST_WRAPPERS;
    }

    @Override
    public Map<String, Operation> getOperationMap() {
        return null;
    }

    @Override
    public Deque<CastWrapper> getCastingResult() {
        return null;
    }

}

package ru.org.icad.mishka.app.process.casting.schema4.operation;

import ru.org.icad.mishka.app.process.casting.CastWrapper;
import ru.org.icad.mishka.app.process.casting.Operation;
import ru.org.icad.mishka.app.process.casting.Schema;

public class CastCmOperation extends Operation {

    private Schema schema;

    public CastCmOperation(Schema schema) {
        this.schema = schema;
    }

    @Override
    public boolean activate() {
        CastWrapper castWrapper = schema.getDequeCastWrapper().poll();

        schema.getCastingResult().addLast(castWrapper);
        return false;
    }
}

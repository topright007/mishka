package ru.org.icad.mishka.app.process.casting.schema1_2.operation;

import ru.org.icad.mishka.app.process.casting.CastWrapper;
import ru.org.icad.mishka.app.process.casting.Operation;

import java.sql.Date;

public class CleanDistrOperation extends Operation {
    @Override
    public void init(CastWrapper castWrapper) {

    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public Date getProcessTime() {
        return null;
    }
}

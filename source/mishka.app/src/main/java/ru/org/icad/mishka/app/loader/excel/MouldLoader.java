package ru.org.icad.mishka.app.loader.excel;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.org.icad.mishka.app.model.Mould;
import ru.org.icad.mishka.app.util.ExcelUtil;

import java.util.Collections;
import java.util.List;

public class MouldLoader implements ExcelLoader<Mould> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarkLoader.class);

    @NotNull
    @Override
    public List<Mould> load(String filePath, String sheetName) {
        XSSFSheet sheet = ExcelUtil.getSheet(filePath, sheetName);

        if (sheet == null) {
            LOGGER.error("Not found sheet name: " + sheetName);

            return Collections.emptyList();
        }

        List<Mould> moulds = Lists.newArrayList();

        final int lastRowNum = sheet.getLastRowNum();
        for (int rowCounter = 0; rowCounter <= lastRowNum; rowCounter++) {
            Row row = sheet.getRow(rowCounter);

            if (row == null) {
                continue;
            }

            Cell mouldIdCell = row.getCell(0);
            if (mouldIdCell == null) {
                continue;
            }

            if (Cell.CELL_TYPE_NUMERIC != mouldIdCell.getCellType()) {
                continue;
            }

            final int mouldId = Double.valueOf(mouldIdCell.getNumericCellValue()).intValue();
            final int castHouseId = ExcelUtil.getIntegerCellValue(row, 1);
            final int startCastMachId = ExcelUtil.getIntegerCellValue(row, 2);
            final int formId = ExcelUtil.getIntegerCellValue(row, 3);
            final int width = ExcelUtil.getIntegerCellValue(row, 4);
            final int height = ExcelUtil.getIntegerCellValue(row, 5);
            final int diameter = ExcelUtil.getIntegerCellValue(row, 6);
            final int resource = ExcelUtil.getIntegerCellValue(row, 7);
            final int resourceMax = ExcelUtil.getIntegerCellValue(row, 8);
            final int prepareTime = ExcelUtil.getIntegerCellValue(row, 9);
            final int numBlanksMax = ExcelUtil.getIntegerCellValue(row, 10);

            Mould mould = new Mould();
            mould.setId(mouldId);
            mould.setCastHouseId(castHouseId);
            mould.setStartCastMachId(startCastMachId);
            mould.setFormId(formId);
            mould.setWidth(width);
            mould.setHeight(height);
            mould.setDiameter(diameter);
            mould.setResource(resource);
            mould.setResourceMax(resourceMax);
            mould.setPrepareTime(prepareTime);
            mould.setNumBlanksMax(numBlanksMax);

            moulds.add(mould);
        }

        return moulds;
    }
}

package ru.org.icad.mishka.app.loader;

import com.google.common.collect.ImmutableMap;
import ru.org.icad.mishka.app.TableName;
import ru.org.icad.mishka.app.loader.excel.*;
import ru.org.icad.mishka.app.util.ExcelUtil;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.io.File;
import java.util.List;
import java.util.Map;

public class DBLoader {

    private static final Map<String, ExcelLoader> EXCEL_LOADER_MAP = ImmutableMap.<String, ExcelLoader>builder()
            .put(TableName.CAST_MACH_CLEAN, new CastingMachineCleanLoader())
            .put(TableName.CM_OPERATION, new CastingMachineOperationLoader())
            .put(TableName.CASTING_SPEED, new CastingSpeedLoader())
            .put(TableName.CASTING_UNIT, new CastingUnitLoader())
            .put(TableName.CU_CASTING_MACHINE, new CastingUnitCastingMachineLoader())
            .put(TableName.CU_COLLECTOR, new CastingUnitCollectorLoader())
            .put(TableName.CU_DISTRIBUTOR, new CastingUnitDistributorLoader())
            .put(TableName.CU_FILTERS, new CastingUnitFiltersLoader())
            .put(TableName.CU_HOMOGEN_CUTTING_LINE, new CastingUnitHomogenCuttingLineLoader())
            .put(TableName.CU_CH_LINES, new CastingUnitHomogenCuttingLinesLoader())
            .put(TableName.CU_MARKS, new CastingUnitMarksLoader())
            .put(TableName.CU_PRODUCT_CHANGE, new CastingUnitProductChangeLoader())
            .put(TableName.CU_REPAIR, new CastingUnitRepairLoader())
            .put(TableName.CAST_MACH_MOULDS, new CastMachMouldsLoader())
            .put(TableName.CONTAINER_TYPE, new ContainerTypeLoader())
            .put(TableName.CUTTING_LINE, new CuttingLineLoader())
            .put(TableName.ELECTROLIZER_PROGNOSIS, new ElectrolizerPrognosisLoader())
            .put(TableName.CU_FILTER, new CastingUnitFiltersLoader())
            .put(TableName.FILTER_CHANGE_MARK, new FilterChangeMarkLoader())
            .put(TableName.FILTER_CONS, new FilterConsLoader())
            .put(TableName.FILTRATION, new FiltrationLoader())
            .put(TableName.FORM, new FormLoader())
            .put(TableName.HEATER, new HeaterLoader())
            .put(TableName.HOMOGENIZATION_LINE, new HomogenizationLineLoader())
            .put(TableName.MARK, new MarkLoader())
            .put(TableName.MIXER_CLEAN, new MixerCleanLoader())
            .put(TableName.MOULD, new MouldLoader())
            .put(TableName.MOULD_BLANKS, new MouldBlanksLoader())
            .put(TableName.CUSTOMER_ORDER, new OrderLoader())
            .put(TableName.ORDER_CU_DIRECTIVE, new OrderCastingUnitDirectiveLoader())
            .put(TableName.PLANT_CONTAINERS, new PlantContainersLoader())
            .put(TableName.PREPARE_TIME_CONST, new PrepareTimeConstLoader())
            .put(TableName.PRODUCED_PRODUCT, new ProducedProductLoader())
            .put(TableName.PRODUCT, new ProductLoader())
            .put(TableName.PRODUCT_COST, new ProductCostLoader())
            .put(TableName.TRANSPORT_COST, new TransportCostLoader())
            .put(TableName.TRANSPORT_DESTINATION, new TransportDestinationLoader())
            .put(TableName.PLANT, new PlantLoader())
            .put(TableName.CAST_HOUSE, new CastHouseLoader())
            .put(TableName.CAST, new CastLoader())
            .build();

    public static void load(String filePath) {
        List<String> sheetNames = ExcelUtil.getSheetNames(filePath);

        for (String sheetName : sheetNames) {
            ExcelLoader excelLoader = EXCEL_LOADER_MAP.get(sheetName);
            if (excelLoader != null) {
                persist(excelLoader.load(filePath, sheetName));
            }
        }

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    private static <T> void persist(List<T> data) {
        try {
            UserTransaction transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MishkaService");
            EntityManager em = emf.createEntityManager();
            if (data != null)
                for (T plant : data) {
                    em.persist(plant);
                }
            em.flush();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

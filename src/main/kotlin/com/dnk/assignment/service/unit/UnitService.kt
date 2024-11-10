package com.dnk.assignment.service.unit

import com.dnk.assignment.domain.property.PropertyRepository
import com.dnk.assignment.domain.unit.Unit
import com.dnk.assignment.domain.unit.UnitRepository
import com.dnk.assignment.model.response.UnitWithPropertyResponse
import com.dnk.assignment.helper.CommonException
import com.dnk.assignment.helper.CustomException
import com.dnk.assignment.model.request.CreateUnitRequest
import com.dnk.assignment.model.response.PropertyWithUnitResponse
import jakarta.persistence.EntityManager
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.FileInputStream

@Service
class UnitService(
    private val propertyRepository: PropertyRepository,
    private val unitRepository: UnitRepository,
    private val entityManager: EntityManager,
) {
    @Transactional
    fun create(request: CreateUnitRequest): PropertyWithUnitResponse {
        val property = propertyRepository.findOneById(request.propertyId)
            ?: throw CustomException(CommonException.PROPERTY_NOT_EXIST)

        val unitsFromXlsx = readXlsxFile("src/main/resources/DNK_village_units.xlsx")
        unitsFromXlsx.map {
            property.addUnit(
                Unit(block = it[0], name = it[1])
            )
        }
        entityManager.flush()
        return PropertyWithUnitResponse.of(property)
    }

    @Transactional(readOnly = true)
    fun getUnit(unitId: Long): UnitWithPropertyResponse =
        unitRepository.findOneById(unitId)?.let(UnitWithPropertyResponse::of)
            ?: throw CustomException(CommonException.UNIT_NOT_EXIST)

    fun readXlsxFile(filePath: String): List<List<String>> {
        val data = mutableListOf<List<String>>()
        FileInputStream(filePath).use { fis ->
            val workbook = XSSFWorkbook(fis)
            val sheet = workbook.getSheetAt(0)
            for (row in sheet) {
                if (row.rowNum == 0) {
                    continue
                }
                val rowData = mutableListOf<String>()
                for (cell in row) {
                    val cellValue = when (cell.cellType) {
                        CellType.STRING -> cell.stringCellValue
                        CellType.NUMERIC -> cell.numericCellValue.toString()
                        CellType.BOOLEAN -> cell.booleanCellValue.toString()
                        else -> "Unknown"
                    }
                    rowData.add(cellValue)
                }
                data.add(rowData)
            }
            workbook.close()
        }
        return data
    }
}

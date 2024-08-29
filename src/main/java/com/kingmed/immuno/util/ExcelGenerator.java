package com.kingmed.immuno.util;

import cn.hutool.poi.excel.RowUtil;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.ExcelHeaderInfo;
import com.kingmed.immuno.model.dataModel.dto.SampleBase;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.dataModel.dto.VirtualSlide;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 打印清单的工具类，用要打印的设备VirtualMachine进行初始化
 */
public class ExcelGenerator {

    // 先写死，后续再考虑扩展问题
    private VirtualMachine virtualMachine;
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final String[] tableHeaders = {"玻片编号", "实验区", "实验号", "项目", "结果", "批号"};

    private final int COLUMN_LENGTH = tableHeaders.length;

    //测试用空机器设备
    public ExcelGenerator() {
        workbook = new XSSFWorkbook();
    }

    public void writeToFile(String fileName) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream("output.xlsx")) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }
    }

    /**
     * 参考图片根据一个设备打印一张表
     *
     * @param virtualMachine
     */
    public ExcelGenerator(VirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
        workbook = new XSSFWorkbook();
    }

    public static XSSFCellStyle setDefaultStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeightInPoints((short) 9);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        //自动换行
        cellStyle.setWrapText(true);
        // 边框
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static void setRegionStyle(XSSFSheet sheet, CellRangeAddress region, XSSFCellStyle cs) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (null == row)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                XSSFCell cell = row.getCell(j);
                if (null == cell)
                    cell = row.createCell(j);
                cell.setCellStyle(cs);
            }
        }
    }

    private Cell initCell(XSSFSheet sheet, int row, int column) {
        return sheet.createRow(row).createCell(column);
    }

    /**
     * 写excel表头，封装信息ExcelHeaderInfo作为参数
     *
     * @param excelHeaderInfo
     */
    public void writeHeader(ExcelHeaderInfo excelHeaderInfo) {
        sheet = workbook.createSheet("template");

        List<CellRangeAddress> regions = new ArrayList<>();
        CellRangeAddress region = new CellRangeAddress(0, 1, 0, 5);
        CellRangeAddress region1 = new CellRangeAddress(2, 4, 0, 2);
        CellRangeAddress region2 = new CellRangeAddress(2, 4, 3, 5);
        regions.add(region);
        regions.add(region1);
        regions.add(region2);
        // 合并之后为合并的单元格设置样式
        Cell cell_0_0 = initCell(sheet, 0, 0);
        cell_0_0.setCellValue(String.format("   间接免疫荧光检测法记录表-1\n" +
                "表号: %s       日期:%s",excelHeaderInfo.getTableNo(),DateTimeUtil.getStNow()));
        Row row1 = sheet.createRow(2);
        Cell secondCell0 = row1.createCell(0);
        Cell secondCell1 = row1.createCell(3);

        //缺少的数据构造
//        List<SampleBase> virtualLabTasks = virtualMachine.getVirtualSlides().get(0).getVirtualLabTasks();
//        HeliosLabTaskWithPostion heliosLabTaskWithPostion = (HeliosLabTaskWithPostion) virtualLabTasks.get(0);
//        HeliosReagent heliosReagent = heliosReagentMapper.selectById(heliosLabTaskWithPostion.getReagentId());
//        LabOrder labOrder = labOrderMapper.selectById(heliosLabTaskWithPostion.getLabOrderId());
        /**
         * 同一个设备用的试剂相同 - HeliosReagent
         * 打印一个设备中一个批次LabOrder的清单
         * 在分配逻辑中确保
         */
        secondCell0.setCellValue(String.format("试剂厂家：%s\n检测方法：%s\n是否按操作说明书操作：是    否",
                excelHeaderInfo.getReagentManufacturer(), excelHeaderInfo.getDetectionMethod()
        ));
        secondCell1.setCellValue(String.format("室内温度：%s\n室内湿度：%s\n设备编号：%s",
                excelHeaderInfo.getTemperature(),excelHeaderInfo.getWet(),virtualMachine.getDeviceCode()
        ));

        XSSFCellStyle style = setDefaultStyle(workbook);
        //补全合并单元格的边框齐问题
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
        //设置合并单元格的样式并且进行合并
        for (CellRangeAddress cellAddresses : regions) {
            setRegionStyle(sheet, cellAddresses, style);
            sheet.addMergedRegion(cellAddresses);
        }

        Row headRow = sheet.createRow(5);
        for (int i = 0; i < this.tableHeaders.length; i++) {
            createCell(headRow, i, this.tableHeaders[i], style);
        }
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    public void write() {
        XSSFCellStyle style = setDefaultStyle(workbook);
        List<VirtualSlide> virtualSlides = virtualMachine.getVirtualSlides();

        /**
         * 添加玻片编号区域，方便后续格式调整
         */
        int rowCount = sheet.getLastRowNum();
        List<CellRangeAddress> slideRegions = new ArrayList<>();
        for (int slideNum = 1; slideNum <= virtualSlides.size(); slideNum++) {
            VirtualSlide virtualSlide = virtualSlides.get(slideNum - 1);
            CellRangeAddress region = new CellRangeAddress(rowCount, rowCount + virtualSlide.getNextIndex() - 1 , 0, 0);
            rowCount += virtualSlide.getNextIndex();
            slideRegions.add(region);
        }

        addTasksToRows(virtualSlides);
        setRegionStyle(sheet,new CellRangeAddress(0,sheet.getLastRowNum(),0,COLUMN_LENGTH-1),style);
        /**
         * 添加完任务后进行格式的布置
         * 额外对表头和玻片编号单元格进行格式修整
         */
        style.setAlignment(HorizontalAlignment.CENTER); //水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        setRegionStyle(sheet,new CellRangeAddress(0,1,0,COLUMN_LENGTH-1),style);
        for(CellRangeAddress region : slideRegions){
            setRegionStyle(sheet,region,style);
        }
    }

    public void addTasksToRows(List<VirtualSlide> virtualSlides) {
        int rowCount = sheet.getLastRowNum();

        for (int slideNum = 1; slideNum <= virtualSlides.size(); slideNum++) {
            VirtualSlide virtualSlide = virtualSlides.get(slideNum - 1);
            List<SampleBase> labTasks = virtualSlide.getVirtualLabTasks();
            //玻片编号合并单元格
            Row row = RowUtil.getOrCreateRow(sheet, ++rowCount);
            int cellNum = row.getLastCellNum()+1;
            CellUtil.createCell(row, cellNum++, Integer.toString(slideNum));
            CellRangeAddress region = new CellRangeAddress(rowCount, rowCount + virtualSlide.getNextIndex() - 1 , 0, 0);
            sheet.addMergedRegion(region);

            //nextIndex还是heliosreagent的numWells???
            //???nextIndex和labTasks的大小是否对齐
            // 前面的Slide应该一致，分配时是尽量填满的，但是最后一个slide可能没有满

            for(int rowAddCnt = 0 ; rowAddCnt < virtualSlide.getNextIndex() ; ) {
                /**
                 * 添加一个玻片编号Slide所有实验区well的数据
                 * 分割到下一个slide的时侯重置virtualSlide跳出本层循环
                 */
                Row slideRow = rowAddCnt > 0 ? RowUtil.getOrCreateRow(sheet, ++rowCount) : row ;
                SampleBase sampleBase = labTasks.get(rowAddCnt);
                HeliosLabTaskWithPostion helioLabTask;
                String wellIndex = "";
                /**
                 * 创建位置结果等信息的cell
                 * 对于QC任务是LabTask结构体，常规任务是HeliosLabTaskWithPostion分开讨论
                 */
                if ( sampleBase instanceof HeliosLabTaskWithPostion && sampleBase.getTaskType() == 0 ) {
                    helioLabTask = (HeliosLabTaskWithPostion) sampleBase;
                    wellIndex = Integer.toString(helioLabTask.getWellIndex());
                    CellUtil.createCell(slideRow, cellNum++, wellIndex);
                    CellUtil.createCell(slideRow, cellNum++, helioLabTask.getExperimentNo());
                    CellUtil.createCell(slideRow, cellNum++, helioLabTask.getLabTestItemName());
                    CellUtil.createCell(slideRow, cellNum++, helioLabTask.getResult());
                    //试剂的生产批号batchNo == task中的reagentLot ???
                    CellUtil.createCell(slideRow, cellNum++, helioLabTask.getReagentLot());
                } else if ( sampleBase instanceof LabTask && sampleBase.getTaskType() == 1){
                    wellIndex = sampleBase.getDevicePosition().split("-")[2];
                    LabTask labTask  = (LabTask) sampleBase;
                    CellUtil.createCell(slideRow, cellNum++, wellIndex);
                    CellUtil.createCell(slideRow, cellNum++, labTask.getExperimentNo());
                    CellUtil.createCell(slideRow, cellNum++, labTask.getLabTestItemName());
                    CellUtil.createCell(slideRow, cellNum++, labTask.getResult());
                    //试剂的生产批号batchNo == task中的reagentLot ???
                    CellUtil.createCell(slideRow, cellNum++, labTask.getReagentLot());
                }else{
                    throw new RuntimeException(String.format("任务插入有误，缺少必要参数无法转成带位置的任务\n" +
                            " 详细信息: " + labTasks.get(rowAddCnt)));
                }

                if (cellNum == COLUMN_LENGTH) {
                    if (rowAddCnt == virtualSlide.getNextIndex()) {
                        break;
                    }
                    rowAddCnt++;//增加一行
                    cellNum = 1;//重置列数
                }
            }


        }
    }

    public void generateExcelFile(ExcelHeaderInfo excelHeaderInfo,HttpServletResponse response) throws IOException {
        writeHeader(excelHeaderInfo);
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }


}

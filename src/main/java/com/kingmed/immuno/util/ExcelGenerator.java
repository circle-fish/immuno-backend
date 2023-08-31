//package com.kingmed.immuno.util;
//
//import com.kingmed.kmhrg.domain.vo.BlankVo;
//import com.kingmed.kmhrg.domain.vo.QuestionVo;
//import com.kingmed.kmhrg.domain.vo.RangeVo;
//import com.kingmed.kmhrg.domain.vo.SelectionVo;
//import com.kingmed.kmhrg.domain.vo.resp.TemplateRespVo;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class ExcelGenerator {
//
//    // 先写死，后续再考虑扩展问题
//    private final List<TemplateRespVo> templateExcelList;
//    private final XSSFWorkbook workbook;
//    private XSSFSheet sheet;
//
//    public ExcelGenerator(List<TemplateRespVo> templateExcelList) {
//        this.templateExcelList = templateExcelList;
//        workbook = new XSSFWorkbook();
//    }
//
//    private void writeHeader() {
//        sheet = workbook.createSheet("template");
//        Row row = sheet.createRow(0);
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(16);
//        style.setFont(font);
//        createCell(row, 0, "templateId", style);
//        createCell(row, 1, "templateName", style);
//        createCell(row, 2, "questionId", style);
//        createCell(row, 3, "questionNo", style);
//        createCell(row, 4, "questionBody", style);
//        createCell(row, 5, "selectionId", style);
//        createCell(row, 6, "selectionNo", style);
//        createCell(row, 7, "selectionBody", style);
//        createCell(row, 8, "blankId", style);
//        createCell(row, 9, "rangeId", style);
//        createCell(row, 10, "rangeValue", style);
//    }
//
//    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
//        if (valueOfCell instanceof Integer) {
//            cell.setCellValue((Integer) valueOfCell);
//        } else if (valueOfCell instanceof Long) {
//            cell.setCellValue((Long) valueOfCell);
//        } else if (valueOfCell instanceof String) {
//            cell.setCellValue((String) valueOfCell);
//        } else {
//            cell.setCellValue((Boolean) valueOfCell);
//        }
//        cell.setCellStyle(style);
//    }
//
//    private void write() {
//        int rowCount = 1;
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(14);
//        style.setFont(font);
//        // 针对每个模板进行处理
//        for (TemplateRespVo template : templateExcelList) {
//            int columnCount = 0;
//            Row row = sheet.createRow(rowCount++);
//            createCell(row, columnCount++, template.getId(), style);
//            createCell(row, columnCount++, template.getNameZh(), style);
//            // 针对模板中的每道题进行处理
//            for (QuestionVo question : template.getQuestionVos()) {
//                int columnCountSnapShotQuestion = columnCount;
//                createCell(row, columnCount++, question.getId(), style);
//                createCell(row, columnCount++, question.getNo(), style);
//                createCell(row, columnCount++, question.getBody(), style);
//                // 针对题目中的每个选项进行处理
//                for (SelectionVo selection : question.getContent()) {
//                    int columnCountSnapShotSelection = columnCount;
//                    createCell(row, columnCount++, selection.getId(), style);
//                    createCell(row, columnCount++, selection.getNo(), style);
//                    createCell(row, columnCount++, selection.getBody(), style);
//                    // 处理该选项下存在填空的情况
//                    if (selection.getBlankList() != null && !selection.getBlankList().isEmpty()) {
//                        // 针对选项中的每个填空进行处理
//                        for (BlankVo blank : selection.getBlankList()) {
//                            int columnCountSnapShotBlank = columnCount;
//                            createCell(row, columnCount++, blank.getId(), style);
//                            // 处理该填空中存在值域的情况
//                            if (blank.getRange() != null && !blank.getRange().isEmpty()) {
//                                // 针对填空中的每个值域进行处理
//                                for (RangeVo range : blank.getRange()) {
//                                    int columnCountSnapShotRange = columnCount;
//                                    createCell(row, columnCount++, range.getId(), style);
//                                    createCell(row, columnCount + 1, range.getContent(), style);
//                                    row = sheet.createRow(rowCount++);
//                                    columnCount = columnCountSnapShotRange;
//                                }
//                                rowCount--;
//                            }
//                            row = sheet.createRow(rowCount++);
//                            columnCount = columnCountSnapShotBlank;
//                        }
//                        rowCount--;
//                    }
//                    row = sheet.createRow(rowCount++);
//                    columnCount = columnCountSnapShotSelection;
//                }
//                rowCount--;
//                row = sheet.createRow(rowCount++);
//                columnCount = columnCountSnapShotQuestion;
//            }
//        }
//    }
//
//    public void generateExcelFile(HttpServletResponse response) throws IOException {
//        writeHeader();
//        write();
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//        outputStream.close();
//    }
//
//}

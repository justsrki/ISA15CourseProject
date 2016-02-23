package model.dto.restaurant;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class TablesDto {
    public static final String TABLE = "table";
    public static final String NO_TABLE = "no_table";
    public static final String RESERVED = "reserved";

    public static class TableDto {
        private Integer id;
        private String label;
        private int row;
        private int column;
        private String type;

        public TableDto() {
        }

        public TableDto(int row, int column, String type) {
            this.row = row;
            this.column = column;
            this.type = type;
        }

        public TableDto(Integer id, String label, int row, int column, String type) {
            this.id = id;
            this.label = label;
            this.row = row;
            this.column = column;
            this.type = type;
        }

        public void init(Integer id, String label, String type) {
            this.id = id;
            this.label = label;
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @NotEmpty(message = "Tables cannot be empty.")
    private List<List<TableDto>> tables;
    @Min(value = 1, message = "Number of rows must be positive integer.")
    private int rows;
    @Min(value = 1, message = "Number of columns must be positive integer.")
    private int columns;

    public TablesDto() {
        this(0, 0);
    }

    public TablesDto(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        tables = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<TableDto> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new TableDto(i, j, NO_TABLE));
            }
            tables.add(row);
        }
    }

    public void setTable(Integer id, int row, int column, String label, String type) {
        if (label == null) {
            label = (row + 1) + " " + (column + 1);
        }
        tables.get(row).get(column).init(id, label, type);
    }

    public List<List<TableDto>> getTables() {
        return tables;
    }

    public void setTables(List<List<TableDto>> tables) {
        this.tables = tables;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}

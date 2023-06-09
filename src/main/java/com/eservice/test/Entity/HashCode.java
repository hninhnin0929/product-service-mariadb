package com.eservice.test.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

@Entity
@Table(name = "HashCode")
@Getter
@Setter
public class HashCode extends AbstractEntity {

    @JsonView(Views.Thin.class)
    private String hashcodeNo;

    @JsonView(Views.Thin.class)
    private String description;

    @JsonView(Views.Thin.class)
    private String parent;

    @JsonView(Views.Thin.class)
    private String level;

    public HashCode() {

    }
    public HashCode(Row row) {
        setHashcodeNo(row.getCell(1) + "");
        setDescription(row.getCell(2) + "");
        setParent(row.getCell(3) + "");
        setLevel(row.getCell(4) + "");
    }
}

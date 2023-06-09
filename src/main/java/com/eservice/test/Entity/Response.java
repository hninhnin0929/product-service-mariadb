package com.eservice.test.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 8992463810222512826L;

    @JsonView(Views.Thin.class)
    private List<T> dataList = new ArrayList<T>();

    @JsonView(Views.Thin.class)
    private boolean status;

    @JsonView(Views.Thin.class)
    private String message;

    public Response() {
    }

    public Response(boolean status, String message) {
        setStatus(status);
        setMessage(message);
    }
}
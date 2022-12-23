package com.vladik.rest.api.dto;

import org.springframework.stereotype.Component;

@Component
public class DeleteDto {

    private Boolean deleteId;

    public Boolean getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Boolean deleteId) {
        this.deleteId = deleteId;
    }
}

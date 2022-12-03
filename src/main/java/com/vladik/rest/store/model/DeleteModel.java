package com.vladik.rest.store.model;

public class DeleteModel {

    private Boolean deleteId;

    public static DeleteModel deleteModel(boolean deleteId){
        DeleteModel delete = new DeleteModel();
        delete.setDeleteId(deleteId);
        return delete;
    }

    public Boolean getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Boolean deleteId) {
        this.deleteId = deleteId;
    }
}

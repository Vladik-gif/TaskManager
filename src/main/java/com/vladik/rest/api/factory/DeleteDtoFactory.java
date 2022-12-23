package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.DeleteDto;
import org.springframework.stereotype.Component;

@Component
public class DeleteDtoFactory {

    public DeleteDto makeDeleteDto(boolean deleteId){
        DeleteDto delete = new DeleteDto();
        delete.setDeleteId(deleteId);
        return delete;
    }

}

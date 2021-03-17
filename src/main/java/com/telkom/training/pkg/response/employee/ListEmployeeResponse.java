package com.telkom.training.pkg.response.employee;

import com.telkom.training.model.EmployeeModel;
import com.telkom.training.pkg.response.BaseResponse;

public class ListEmployeeResponse extends BaseResponse{
    private Iterable<EmployeeModel> listEmployee;

    public Iterable<EmployeeModel> getListEmployee() {
        return this.listEmployee;
    }

    public void setListEmployee(Iterable<EmployeeModel> listEmployee) {
        this.listEmployee = listEmployee;
    }

}

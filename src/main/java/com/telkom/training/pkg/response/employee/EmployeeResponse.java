package com.telkom.training.pkg.response.employee;

import com.telkom.training.model.EmployeeModel;
import com.telkom.training.pkg.response.BaseResponse;

public class EmployeeResponse extends BaseResponse{
    private EmployeeModel employee;

    public EmployeeModel getEmployee() {
        return this.employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

}

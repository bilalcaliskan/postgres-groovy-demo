package com.bcaliskan.postgresgroovydemo.model.response

class FailureResponse extends BaseResponse {

    String errorDescription


    FailureResponse(tag, status, errorDescription) {
        this.tag = tag
        this.status = status
        this.errorDescription = errorDescription
    }

}

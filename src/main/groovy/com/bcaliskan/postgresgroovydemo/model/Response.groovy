package com.bcaliskan.postgresgroovydemo.model

class Response {

    String tag

    Boolean status

    String errorDescription


    Response(tag, status) {
        this.tag = tag
        this.status = status
    }

    Response(tag, status, errorDescription) {
        this.tag = tag
        this.status = status
        this.errorDescription = errorDescription
    }

}

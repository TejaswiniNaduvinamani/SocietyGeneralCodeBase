export class EmployeeDetail {

    firstName: string;
    lastName: string;
    gender: string;
    dateOfBirth: Date;
    department: string ;

    constructor() {}

}

export class EmployeeDetailWrapper {

    statusCode: string;
    statusMsg: string;
    empDTO: EmployeeDetail[];

    constructor() {}

}

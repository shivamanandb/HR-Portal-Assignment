async function fetchEmployeesAPI() {

    const response = await fetch('http://localhost:8080/api/v1/employee/all',{
        method: 'GET',
        headers:{
            // 'Content-Type': 'application/json'
        }
    })
    .then(res => res.json())
    .catch(error => console.log(error))

    return response;
}
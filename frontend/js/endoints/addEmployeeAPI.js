async function addEmployeeAPI(empData) {
    const response = await fetch('http://localhost:8080/api/v1/employee/add-employee', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(empData)
    })
    .then(res => res.json())
    .catch(err => console.log(err))

    return response;
}
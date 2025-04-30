async function updateEmployeeDetailsAPI(empId, updatedDetails) {
    const response = await fetch(`http://localhost:8080/api/v1/employee/update/${empId}`, {
        method: 'PUT',
        headers: {
            'content-Type': 'application/json'
            },
        body: JSON.stringify(updatedDetails)
    })
    .then(res => res.json())
    .catch(error => console.log(error))

    return response;
}